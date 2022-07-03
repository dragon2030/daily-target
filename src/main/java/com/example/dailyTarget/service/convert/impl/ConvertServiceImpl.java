package com.example.dailyTarget.service.convert.impl;

import com.example.dailyTarget.service.convert.ConvertService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
