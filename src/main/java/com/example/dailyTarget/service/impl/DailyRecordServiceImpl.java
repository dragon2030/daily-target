package com.example.dailyTarget.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dailyTarget.dto.EditDailyRecordDto;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.enums.StatusEnum;
import com.example.dailyTarget.mapper.DailyRecordMapper;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.mapper.PlanTargetMapper;
import com.example.dailyTarget.service.IDailyRecordService;
import com.example.dailyTarget.service.convert.ConvertService;
import com.example.dailyTarget.vo.DailyRecordVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(){
        //获取指定目标表的生效记录
        PlanTarget planTargetEntity =
                new LambdaQueryChainWrapper<>(planTargetMapper).eq(PlanTarget::getStatus, StatusEnum.ENABLE.getCode()).one();

        //生成当日目标日记记录
        //解析获取当天的计划任务
        String planTargetDes = planTargetEntity.getPlanTargetDes();
        String[] planTargets = planTargetDes.split("\\|\\|");

        Map<String, String> planTargetMap = new HashMap<>(planTargets.length);
        String weekStr;
        String detailStr;
        for(String planTarget:planTargets){
            //例如[1]1、运动60分钟。2、12点前睡觉。3、学习一小时。||[2]1、运动60分钟。2、12点前睡觉。3、学习一小时。
            weekStr = planTarget.substring(1,4);
            detailStr = planTarget.substring(5);
            planTargetMap.put(weekStr, detailStr);
        }

        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.setPlanTargetId(planTargetEntity.getId());
        dailyRecord.setStatisticsDate(convertService.getTodayZeroTime());
        String weekDayName = convertService.getWeekDayName(null);
        String dailyPlanTarget = planTargetMap.get(weekDayName);
        dailyRecord.setPlanTargetDes(dailyPlanTarget);
        dailyRecord.setPlanTargetAchievement(dailyPlanTarget);
        dailyRecord.setCreateTime(new Date());
        this.save(dailyRecord);
    }

    //编辑每日记录
    @Override
    public void editRecord(EditDailyRecordDto dto){
        DailyRecord dailyRecord = new DailyRecord();
        BeanUtil.copyProperties(dto, dailyRecord);
        this.updateById(dailyRecord);
    }

    //获取日记及目标全部记录
    @Override
    public List<DailyRecord> getList(){
        //获取日记表
        List<DailyRecord> dailyRecords =
                new LambdaQueryChainWrapper<>(dailyRecordMapper).orderByDesc(DailyRecord::getCreateTime).list();
        return dailyRecords;
    }
}
