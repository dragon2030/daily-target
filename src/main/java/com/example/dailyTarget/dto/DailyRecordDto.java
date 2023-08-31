package com.example.dailyTarget.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: bigDragon
 * @create: 2023/3/6
 * @Description:
 */
@Data
public class DailyRecordDto {

    @ApiModelProperty("人生感想")
    private String lifeFeeling;

    @ApiModelProperty("日记")
    private String diaryRecordDetail;

    @ApiModelProperty("运动目标")
    private String sportTarget;

    @ApiModelProperty("学习目标")
    private String studyTarget;

    @ApiModelProperty("作息目标")
    private String sleepTarget;

    @ApiModelProperty("统计日期 为空时即为当天")
    private String aStatisticsDate;

}
