package com.example.dailyTarget.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: bigDragon
 * @create: 2022/7/10
 * @Description:
 */
public class CommonValue {
    //一周内星期map
    public static Map<Integer, String> WEEK_MAP = new HashMap<>(7);
    //一周内星期list
    public static List<String> WEEK_LIST = new ArrayList<>(7);

    static {
        //一周内星期map
        WEEK_MAP.put(1, "星期天");
        WEEK_MAP.put(2, "星期一");
        WEEK_MAP.put(3, "星期二");
        WEEK_MAP.put(4, "星期三");
        WEEK_MAP.put(5, "星期四");
        WEEK_MAP.put(6, "星期五");
        WEEK_MAP.put(7, "星期六");

        //一周内星期list
        WEEK_LIST.add("星期一");
        WEEK_LIST.add("星期二");
        WEEK_LIST.add("星期三");
        WEEK_LIST.add("星期四");
        WEEK_LIST.add("星期五");
        WEEK_LIST.add("星期六");
        WEEK_LIST.add("星期天");
    }
    
    public static final String SUNDAY = "星期天";
    
    
}
