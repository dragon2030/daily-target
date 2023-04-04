package com.example.dailyTarget.service.convert;

import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.enums.TargetEnum;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
public interface ConvertService {
    //获取星期几，返回例如：星期一
    String getWeekDayName(Date date);

    //获取当天0点的时间
    Date getTodayZeroTime();

    //获取最后记录天到今天所有的中间天数（不含最后记录天，含今天）
    List<Date> getDaysTodayUntilLastRecordDay(Date lastRecordDate);

//    解析目标计划内容返回map
    Map<String,String> parsePlanTargetDesToMap(String planTargetDes);
//    解析目标计划内容返回List
    List<String> parsePlanTargetDesToList(String planTargetDes);

    //获取统计时间 yyyy-MM-dd
    String getStatisticsDate(Date date);

    ////获取第一快(运动目标)/第二块(学习目标)/第三块(作息目标)
    String getPartTargetAchievement(DailyRecord originDateRecord, TargetEnum targetEnum);
}
