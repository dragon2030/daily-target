package com.example.dailyTarget.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dailyTarget.dto.DailyRecordDto;
import com.example.dailyTarget.enums.TargetEnum;
import com.example.dailyTarget.mapper.DailyRecordMapper;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.mapper.PlanTargetMapper;
import com.example.dailyTarget.service.IDailyRecordService;
import com.example.dailyTarget.service.convert.ConvertService;
import com.example.dailyTarget.vo.DailyRecordVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: bigDragon
 * @create: 2022/6/26
 * @Description:
 */
@Service
public class DailyRecordServiceImpl extends ServiceImpl<DailyRecordMapper, DailyRecord> implements IDailyRecordService {

    @Resource
    DailyRecordMapper dailyRecordMapper;
    @Resource
    PlanTargetMapper planTargetMapper;
    @Resource
    ConvertService convertService;


    //生成每日记录
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void addRecord(){
//        //获取指定目标表的生效记录
//        PlanTarget planTargetEntity =
//                new LambdaQueryChainWrapper<>(planTargetMapper).eq(PlanTarget::getStatus, StatusEnum.ENABLE.getCode()).one();
//
//        //生成当日目标日记记录
//        //解析获取当天的计划任务
//        String planTargetDes = planTargetEntity.getPlanTargetDes();
////        String[] planTargets = planTargetDes.split("\\|\\|");
////
////        Map<String, String> planTargetMap = new HashMap<>(planTargets.length);
////        String weekStr;
////        String detailStr;
////        for(String planTarget : planTargets){
////            //例如[星期一]1、运动60分钟。2、12点前睡觉。3、学习一小时。||[星期二]1、运动60分钟。2、12点前睡觉。3、学习一小时。
////            weekStr = planTarget.substring(1,4);
////            detailStr = planTarget.substring(5);
////            planTargetMap.put(weekStr, detailStr);
////        }
//        Map<String, String> planTargetMap = convertService.parsePlanTargetDesToMap(planTargetDes);
//
//        // 获取上一次日记记录时间到今天，所有需要生成记录的日期。通常情况下为1天，如果之前没有生产就带上没生成的记录
//        DailyRecord lastDailyRecord =
//                new LambdaQueryChainWrapper<>(dailyRecordMapper).orderByDesc(DailyRecord::getStatisticsDate).list().get(0);
//        List<Date> daysTodayUntilLastRecordDate = convertService.getDaysTodayUntilLastRecordDay(lastDailyRecord.getStatisticsDate());
//
//        //所有需要产生日记的date
//        for(Date recordDate : daysTodayUntilLastRecordDate){
//            //插入日记记录表
//            DailyRecord dailyRecord = new DailyRecord();
//            dailyRecord.setPlanTargetId(planTargetEntity.getId());
//            dailyRecord.setStatisticsDate(recordDate);
//
//            //获取当天是周几
//            String weekDayName = convertService.getWeekDayName(recordDate);
//            String dailyPlanTarget = planTargetMap.get(weekDayName);
//            dailyRecord.setPlanTargetDes(dailyPlanTarget);
//            dailyRecord.setDayOfWeek(weekDayName);
//
//            dailyRecord.setCreateTime(new Date());
//            this.save(dailyRecord);
//        }
//    }

    //编辑每日记录
//    @Override
//    public void editRecord(EditDailyRecordDto dto){
//        DailyRecord dailyRecord = new DailyRecord();
//        BeanUtil.copyProperties(dto, dailyRecord);
//        this.updateById(dailyRecord);
//
//    }

    //获取日记及目标全部记录
    @Override
    public List<DailyRecordVo> getList(){
        //获取日记表
        List<DailyRecord> dailyRecords =
                new LambdaQueryChainWrapper<>(dailyRecordMapper).orderByDesc(DailyRecord::getStatisticsDate).list();
        List<DailyRecordVo> vos = new ArrayList<>();
        dailyRecords.forEach(entity -> {
            DailyRecordVo vo = new DailyRecordVo();
            BeanUtil.copyProperties(entity, vo);
            vos.add(vo);
        });

        return vos;
    }

    //获取某天日记及目标全部记录
    private DailyRecord getByStatisticsDate(String statisticsDate){
        if(statisticsDate==null){
            return null;
        }
        //获取日记表
        List<DailyRecord> dailyRecords =
                new LambdaQueryChainWrapper<>(dailyRecordMapper)
                        .eq(DailyRecord::getStatisticsDate,statisticsDate)
                        .orderByDesc(DailyRecord::getStatisticsDate).list();
        if(CollUtil.isEmpty(dailyRecords)){
            return null;
        }else{
            return dailyRecords.get(0);
        }
    }


    //todo 将目标达成独立一张表存储，因为后面要进行查询统计
    //目标的一张字典表记录一下code
    @Override
    public void dailyRecord(DailyRecordDto dailyRecordDto){
        Date date = null;
        String statisticsDate = dailyRecordDto.getAStatisticsDate();
        if(StringUtils.isBlank(statisticsDate)){//当天记录
            date = new Date();
            //获取统计时间 yyyy-MM-dd
            statisticsDate = convertService.getStatisticsDate(date);
        }else{//非当天的修改记录
            try {
                date = new SimpleDateFormat("yyyyMMdd").parse(statisticsDate);
                statisticsDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            } catch (ParseException e) {
                throw new RuntimeException("日期格式解析有误");
            }
        }
        //获取输入目标日的数据
        DailyRecord originDateRecord = this.getByStatisticsDate(statisticsDate);
        //目标完成情况 字段
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(dailyRecordDto.getSportTarget())) {
            stringBuilder.append("1、").append(dailyRecordDto.getSportTarget()).append("。");
        }else if(Objects.isNull(originDateRecord)){//如果为null 把原本1、的内容添加进来
            stringBuilder.append(convertService.getPartTargetAchievement(originDateRecord,TargetEnum.SPORT_TARGET));
        }
        if (StringUtils.isNotBlank(dailyRecordDto.getStudyTarget())) {
            stringBuilder.append("2、").append(dailyRecordDto.getStudyTarget()).append("。");
        }else if(Objects.isNull(originDateRecord)){//如果为null 把原本2、的内容添加进来
            stringBuilder.append(convertService.getPartTargetAchievement(originDateRecord,TargetEnum.STUDY_TARGET));
        }
        if (StringUtils.isNotBlank(dailyRecordDto.getSleepTarget())) {
            stringBuilder.append("3、").append(dailyRecordDto.getSleepTarget()).append("。");
        }else if(Objects.isNull(originDateRecord)){//如果为null 把原本3、的内容添加进来
            stringBuilder.append(convertService.getPartTargetAchievement(originDateRecord,TargetEnum.SLEEP_TARGET));
        }

        String planTargetAchievement = stringBuilder.toString();

        //新增/修改操作
        List<DailyRecord> dailyRecords = new LambdaQueryChainWrapper<>(dailyRecordMapper).list();
        Map<String,DailyRecord> statisticsDateMap =
                dailyRecords.stream().collect(Collectors.toMap(i->i.getStatisticsDate(), Function.identity(),(v1, v2)->v1));
        if(statisticsDateMap.containsKey(statisticsDate)){//编辑
            DailyRecord dailyRecord = statisticsDateMap.get(statisticsDate);
            LambdaUpdateWrapper<DailyRecord> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(DailyRecord::getId,dailyRecord.getId());
            updateWrapper.set(DailyRecord::getPlanTargetAchievement,planTargetAchievement);
            updateWrapper.set(DailyRecord::getLifeFeeling,dailyRecordDto.getLifeFeeling());
            updateWrapper.set(DailyRecord::getDiaryRecordDetail,dailyRecordDto.getDiaryRecordDetail());
            updateWrapper.set(DailyRecord::getUpdateTime,new Date());
            dailyRecordMapper.update(null, updateWrapper);
        }else{
            DailyRecord dailyRecord = new DailyRecord();
            BeanUtil.copyProperties(dailyRecordDto,dailyRecord);
            if(stringBuilder!=null && stringBuilder.length()>0){
                dailyRecord.setPlanTargetAchievement(planTargetAchievement);
            }
            dailyRecord.setStatisticsDate(statisticsDate);
            String weekDayName = convertService.getWeekDayName(date);
            dailyRecord.setDayOfWeek(weekDayName);
            dailyRecord.setCreateTime(date);
            this.save(dailyRecord);
        }
    }
}
