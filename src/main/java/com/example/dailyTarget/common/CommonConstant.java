package com.example.dailyTarget.common;

/**
 * @author: bigDragon
 * @create: 2022/1/13
 * @Description:
 */
public class CommonConstant {
    //报表导出中的错误信息
    public static final String REPORT_DATE_NULL = "数据为空";
    public static final String HTTP_IO_ERROR = "HTTP相应IO流错误";

    // excel相关
    public static final String CONENTTYPE_EXCEL = "application/vnd.ms-excel";
    public static final String RESHEADER_CONTENTDISPOSITION = "Content-Disposition";
    public static final String CONTENTDISPOSITION_PREFIX = "attachment;filename=";

    // 是否对返回数据进行拦截（前端需要）
    public static final String RES_INTERCEPTOR = "Res-Interceptor";

    // 字符编码
    public static final String ENCODE_8859_1 = "ISO-8859-1";
    public static final String ENCODE_UTF_8 = "UTF-8";

    public static final Integer NUMBER_0 = 0;
    public static final Integer NUMBER_1 = 1;
    public static final Integer NUMBER_2 = 2;
    public static final Integer NUMBER_3 = 3;
    
    public static final String EXPORT_CENTRE_EXPORT_PATH = "/data/exportCentre/";
//    public static final String EXPORT_CENTRE_EXPORT_PATH = "D:\\documentOperation\\excel\\";
    
    public static final String THREAD_NAME_PREFIX="statistics-executor-";
}
