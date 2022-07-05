package com.example.dailyTarget.service.convert.impl;

import com.example.dailyTarget.service.convert.ConvertService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
@Service
public class ConvertServiceImpl implements ConvertService {

    @Override
    public String getWeekDayName(Date date){
        Calendar instance = Calendar.getInstance();
        if(date!=null){
            instance.setTime(date);
        }
        int weekInt = instance.get(Calendar.DAY_OF_WEEK);

        Map<Integer, String> weekMap = new HashMap<>(7);
        weekMap.put(1, "星期天");
        weekMap.put(2, "星期一");
        weekMap.put(3, "星期二");
        weekMap.put(4, "星期三");
        weekMap.put(5, "星期四");
        weekMap.put(6, "星期五");
        weekMap.put(7, "星期六");

        return weekMap.get(weekInt);
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
}
