package com.example.dailyTarget.util;

import com.example.dailyTarget.common.CommonConstant;
import com.example.dailyTarget.config.JeecgBootException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/1/13
 * @Description:
 *
 *      Excel导出工具类
 *      不断更新版，物资那个后来不更新了，这个会持续更新，最近解释权————大恐龙
 */
public class ExcelExportUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);

    //创建Excel 写入输出流-调用简化
    public static void checkAndExport(String reportName, String sheetName, Class<?> dtoClass, List<?> list){
        ExcelExportUtil.checkAndExport(reportName, sheetName, dtoClass, list, null, null,null);
    }
    //创建Excel 写入输出流-调用简化
    public static void checkAndExport(String reportName, String sheetName, Class<?> dtoClass, List<?> list,
                                      String[] excludeColumn){
        ExcelExportUtil.checkAndExport(reportName, sheetName, dtoClass, list, excludeColumn, null,null);
    }

    //创建Excel 写入输出流-调用简化
    public static void checkAndExport(String reportName, String sheetName, Class<?> dtoClass, List<?> list,
                                      String[] excludeColumn,Workbook titleWorkbook){
        ExcelExportUtil.checkAndExport(reportName, sheetName, dtoClass, list, excludeColumn, titleWorkbook,null);
    }
    /**
     * 创建Excel 写入输出流
     * @param reportName 导出报表名称
     * @param sheetName 导出报表sheet名称
     * @param dtoClass 导出对象Class类
     * @param list  导出对象数据Collection
     * @param excludeColumn 需要动态排除的列
     * @param titleWorkbook 设置表头，主要针对于需要合并同类项的多行表头
     * @param specialMergeValue 特殊合并值，当此值出现，循环查找同行下一个元素值，直到不是此值为止，所有和此值一样的值做合并操作，用于小计/合计横行合并
     * @describe
     *      excel导出工具类
     *      主要解决问题：
     *          合并单元格问题，包含行合并（如小计合计）列合并（根据上一列进行合并）和表头合并（定制化特殊表头）
     *          定制化数据格式，可以指定每列的输出格式（主要用于保留小数和时间输出）
     */
    public static void checkAndExport(String reportName, String sheetName, Class<?> dtoClass, List<?> list ,
                                      String[] excludeColumn, Workbook titleWorkbook, String[] specialMergeValue){
        logger.info("开始excel导出，名称："+reportName+" 共需导出数量数："+list.size());
        //文件名转义
        String fileName = null;
        try {
            fileName = reportName + ".xlsx";
            fileName = URLEncoder.encode(fileName, CommonConstant.ENCODE_UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            fileName = "report.xlsx";
        }

        //获取输出流
        OutputStream outputStream = null;
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setContentType(CommonConstant.CONENTTYPE_EXCEL);
            response.setHeader(CommonConstant.RESHEADER_CONTENTDISPOSITION,
                    CommonConstant.CONTENTDISPOSITION_PREFIX + fileName);
            response.setHeader(CommonConstant.RES_INTERCEPTOR, Boolean.FALSE.toString());
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new JeecgBootException("HTTP相应IO流错误");
        }
        //Excel文件导出
        try {
           ExcelUtil.exportExcel(sheetName, dtoClass, list, outputStream, excludeColumn, titleWorkbook,specialMergeValue);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new JeecgBootException(reportName+"导出失败！", e);
        }finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }finally {
                    outputStream = null;
                }
            }
        }

    }
}
