package com.example.dailyTarget.service.convert;

import java.util.Date;
import java.util.List;

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
}
