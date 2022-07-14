package com.example.dailyTarget.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
@Data
public class EditDailyRecordDto {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("目标完成情况（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计。数字对应计划项。“99、原因”表示额外项和特殊原因）")
    private String planTargetAchievement;

    @ApiModelProperty("人生感想")
    private String lifeFeeling;

    @ApiModelProperty("日记")
    private String diaryRecordDetail;

    @ApiModelProperty("日目标完成百分比")
    private Integer dayPercentComplete;

    @ApiModelProperty("周目标完成百分比")
    private Integer weekPercentComplete;
}
