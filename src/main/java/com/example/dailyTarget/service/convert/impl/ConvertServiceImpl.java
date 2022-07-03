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
        //记录时间，00:00:00
        Date recordDate = null;
        try {
            recordDate = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //添加当天记录时间
        dateList.add(recordDate);

        while(true){
            //当天记录时间不断前推一天，直到小于等于最后记录天
            instance= Calendar.getInstance();
            instance.setTime(recordDate);
            instance.add(Calendar.DATE, -1);
            Date lastTimeRecordDate = instance.getTime();
            if(lastTimeRecordDate.compareTo(lastRecordDate) <= 0){
                break;
            } else {
                //计算出前推时间在数据库上次记录之后，还得继续前推
                dateList.add(lastTimeRecordDate);
                recordDate = lastTimeRecordDate;
            }

        }
        return dateList;

    }
}
