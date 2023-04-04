package com.example.dailyTarget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: bigDragon
 * @create: 2022/6/26
 * @Description:
 */
@Data
@TableName("daily_record")//@TableName中的值对应着表名
@ApiModel("每日日记及目标完成记录表entity")
public class DailyRecord {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("统计日期")
    private String statisticsDate;

    @ApiModelProperty("目标计划id")
    private String planTargetId;

//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")

    @ApiModelProperty("目标计划内容")
    private String planTargetDes;

    @ApiModelProperty("目标完成情况")
    private String planTargetAchievement;

    @ApiModelProperty("人生感想")
    private String lifeFeeling;

    @ApiModelProperty("日记")
    private String diaryRecordDetail;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("周几")
    private String dayOfWeek;

    @ApiModelProperty("日目标完成百分比")
    private String dayPercentComplete;

    @ApiModelProperty("周目标完成百分比")
    private String weekPercentComplete;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("总积蓄金额")
    private BigDecimal deposit;
}
