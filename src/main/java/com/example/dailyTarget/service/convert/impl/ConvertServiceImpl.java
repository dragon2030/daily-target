package com.example.dailyTarget.service.convert.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.dailyTarget.common.CommonValue;
import com.example.dailyTarget.dto.DailyRecordDto;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.entity.TargetItemDto;
import com.example.dailyTarget.enums.StatusEnum;
import com.example.dailyTarget.enums.TargetEnum;
import com.example.dailyTarget.mapper.PlanTargetMapper;
import com.example.dailyTarget.service.convert.ConvertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
@Service
@Slf4j
public class ConvertServiceImpl implements ConvertService {

    @Override
    public String getWeekDayName(Date date){
        Calendar instance = Calendar.getInstance();
        if(date!=null){
            instance.setTime(date);
        }
        int weekInt = instance.get(Calendar.DAY_OF_WEEK);

//        Map<Integer, String> weekMap = new HashMap<>(7);
//        weekMap.put(1, "星期天");
//        weekMap.put(2, "星期一");
//        weekMap.put(3, "星期二");
//        weekMap.put(4, "星期三");
//        weekMap.put(5, "星期四");
//        weekMap.put(6, "星期五");
//        weekMap.put(7, "星期六");

        return CommonValue.WEEK_MAP.get(weekInt);
    }

    @Override
    public Date getTodayZeroTime(){
        Date todayZeroTime = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String format = simpleDateFormat.format(date);
            todayZeroTime = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayZeroTime;
    }

    //获取最后记录天到今天所有的中间天数（不含最后记录天，含今天）
    @Override
    public List<Date> getDaysTodayUntilLastRecordDay(Date lastRecordDate){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        List<Date> dateList = new ArrayList<>();
        //计算得出的每个上一天的00:00:00的时间，也是要录入的操作日期时间
        Date lastTimeShouldRecordDate = null;
        try {
            lastTimeShouldRecordDate = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //添加当天记录时间
//        dateList.add(recordDate);
//        if(recordDate.compareTo(lastRecordDate) == 0){
//            throw new RuntimeException("已经生成过当日记录");
//        }

        while(true){
            //上次应该记录时间小于等于数据的最后记录时间，代表计算的上次应该记录天已记录，中断循环，否则继续判断上一天
            if(lastTimeShouldRecordDate.compareTo(lastRecordDate) <= 0){
                break;
            } else {
                //计算出前推时间在数据库上次记录之后，还得继续前推
                dateList.add(lastTimeShouldRecordDate);
            }
            //当天记录时间不断前推一天，直到小于等于最后记录天
            instance= Calendar.getInstance();
            instance.setTime(lastTimeShouldRecordDate);
            instance.add(Calendar.DATE, -1);
            lastTimeShouldRecordDate = instance.getTime();
//            if(lastTimeRecordDate.compareTo(lastRecordDate) <= 0){
//                break;
//            } else {
//                //计算出前推时间在数据库上次记录之后，还得继续前推
//                dateList.add(lastTimeRecordDate);
//                recordDate = lastTimeRecordDate;
//            }

        }
        return dateList;

    }

    /**
     * 解析目标计划内容返回map
     * 目标计划内容格式样例：[星期一]1、运动60分钟。2、学习一小时。3、12点前睡觉。4、饮食、七分饱，一素一半荤半碗饭。||[星期二]1、运动60分钟。2、学习一小时。3、12点前睡觉。4、饮食、七分饱，一素一半荤半碗饭。
     * 返回每天内容map key为星期几 value为内容
     */
    @Override
    public Map<String,String> parsePlanTargetDesToMap(String planTargetDes){
        Map<String, String> planTargetMap = new HashMap<>();

        if(StringUtils.isBlank(planTargetDes)){
            return planTargetMap;
        }

        //解析获取每日目标
        String[] planTargets = planTargetDes.split("\\|\\|");

        String weekStr;
        String detailStr;
        for(String planTarget : planTargets){
            //例如[星期一]1、运动60分钟。2、12点前睡觉。3、学习一小时。||[星期二]1、运动60分钟。2、12点前睡觉。3、学习一小时。
            weekStr = planTarget.substring(1,4);
            detailStr = planTarget.substring(5);
            planTargetMap.put(weekStr, detailStr);
        }
        return planTargetMap;
    }

    /**
     * 解析目标计划内容返回List
     * 目标计划内容格式样例：[星期一]1、运动60分钟。2、学习一小时。3、12点前睡觉。4、饮食、七分饱，一素一半荤半碗饭。||[星期二]1、运动60分钟。2、学习一小时。3、12点前睡觉。4、饮食、七分饱，一素一半荤半碗饭。
     * 返回每天内容list,按照星期一到星期天排序
     */
    @Override
    public List<String> parsePlanTargetDesToList(String planTargetDes){
//        Map<String, String> planTargetMap = this.parsePlanTargetDesToMap(planTargetDes);
//
//        ArrayList<String> planTargetList = new ArrayList<>();
//        for(String dayOfWeek : CommonValue.WEEK_LIST){
//            planTargetList
//        }
        List<String> planTargetList = new ArrayList<>();

        if(StringUtils.isBlank(planTargetDes)){
            return planTargetList;
        }
        //解析获取每日目标
        String[] planTargets = planTargetDes.split("\\|\\|");

        for(String planTarget : planTargets){
            planTargetList.add(planTarget);
        }
        return planTargetList;
    }

    //获取统计时间 yyyy-MM-dd
    @Override
    public String getStatisticsDate(Date date){
        if(Objects.isNull(date)){
            date = new Date();
        }
//        Date parse=date;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String statisticsDate = simpleDateFormat.format(date);
//            parse = simpleDateFormat.parse(format);

        return statisticsDate;
    }

//    @Override
    //获取第一快(运动目标)/第二块(学习目标)/第三块(作息目标)
    public String getPartTargetAchievement_old(DailyRecord originDateRecord, TargetEnum targetEnum){
        if(Objects.isNull(originDateRecord)){
            return "";
        }
        if(StringUtils.isBlank(originDateRecord.getPlanTargetAchievement())){
            return "";
        }
        String returnTargetAchievement = "";
        String planTargetAchievement = originDateRecord.getPlanTargetAchievement();
        int oneIndexOf = planTargetAchievement.indexOf("1、");
        int twoIndexOf = planTargetAchievement.indexOf("2、");
        int threeIndexOf = planTargetAchievement.indexOf("3、");
        switch (targetEnum){
            case SPORT_TARGET:
                if (oneIndexOf==-1){
                    return "";
                }else{
                    returnTargetAchievement = planTargetAchievement.substring(0,twoIndexOf);
                }
                break;
            case STUDY_TARGET:
                if (twoIndexOf==-1){
                    return "";
                }else{
                    returnTargetAchievement = planTargetAchievement.substring(twoIndexOf,threeIndexOf);
                }
                break;
            case SLEEP_TARGET:
                if (twoIndexOf==-1){
                    return "";
                }else{
                    returnTargetAchievement = planTargetAchievement.substring(threeIndexOf);
                }
                break;
        }
        return returnTargetAchievement;
    }
    
    
    //配合已经生成的json串，生成新的json串
    @Override
    public String generateNewPlanTargetAchievement(DailyRecordDto queryDto,DailyRecord originDateRecord){
        String planTargetAchievement = "";
        if(Objects.nonNull(originDateRecord)){
            planTargetAchievement = originDateRecord.getPlanTargetAchievement();
        }
        TargetItemDto targetItemDto = JSONObject.parseObject(planTargetAchievement, TargetItemDto.class);
        //目标完成情况 字段
        JSONObject jsonObject = new JSONObject();
        String sportTarget = "";
        if (StringUtils.isNotBlank(queryDto.getSportTarget())) {
            sportTarget = queryDto.getSportTarget();
        }else if(targetItemDto!=null && StringUtils.isNotBlank(targetItemDto.getSportTarget())){//如果为null 把原本1、的内容添加进来
            sportTarget = targetItemDto.getSportTarget(); 
        }
        jsonObject.put("sportTarget",sportTarget);
    
        String studyTarget = "";
        if (StringUtils.isNotBlank(queryDto.getStudyTarget())) {
            studyTarget = queryDto.getStudyTarget();
        }else if(targetItemDto!=null && StringUtils.isNotBlank(targetItemDto.getStudyTarget())){//如果为null 把原本1、的内容添加进来
            studyTarget = targetItemDto.getStudyTarget();
        }
        jsonObject.put("studyTarget",studyTarget);
        
        String sleepTarget = "";
        if (StringUtils.isNotBlank(queryDto.getSleepTarget())) {
            sleepTarget = queryDto.getSleepTarget();
        }else if(targetItemDto!=null && StringUtils.isNotBlank(targetItemDto.getSleepTarget())){//如果为null 把原本1、的内容添加进来
            sleepTarget = targetItemDto.getSleepTarget();
        }
        jsonObject.put("sleepTarget",sleepTarget);
        
        return JSON.toJSONString(jsonObject);
    }
    
    
}
