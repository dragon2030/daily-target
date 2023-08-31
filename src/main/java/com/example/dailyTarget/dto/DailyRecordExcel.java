package com.example.dailyTarget.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.dailyTarget.util.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: bigDragon
 * @create: 2023/6/3
 * @Description:
 */
@Data
public class DailyRecordExcel {
    @Excel(exportName = "统计日期")
    private String statisticsDate;
    @Excel(exportName ="周几")
    private String dayOfWeek;
    
//    @Excel("目标计划内容")
//    private String planTargetDes;
//    
//    @Excel("目标完成情况")
//    private String planTargetAchievement;
    @Excel(exportName = "日记")
    private String diaryRecordDetail;
    
    @Excel(exportName ="人生感想")
    private String lifeFeeling;
    
    @Excel(exportName ="健身目标")
    private String sportTarget;
    @Excel(exportName ="学习目标")
    private String studyTarget;
    @Excel(exportName ="作息目标")
    private String sleepTarget;
    
//    @Excel(exportName ="备注")
//    private String remark;
    

    
    @Excel(exportName ="总积蓄金额")
    private BigDecimal deposit;
    
}
