package com.example.dailyTarget.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author: bigDragon
 * @create: 2022/6/26
 * @Description:
 */
@Data
@TableName("plan_target")
@ApiModel("计划目标表entity")
public class PlanTarget {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("目标计划内容（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计）")
    private String planTargetDes;

    @ApiModelProperty("远期目标对照")
    private String longTermGoal;

    @ApiModelProperty("计划感想")
    private String targetThoughts;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态(1->启用;0->停用)")
    private String status;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private String updateTime;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("改变习惯")
    private String changeHabit;

    @ApiModelProperty("计划目标核心项")
    private String targetCoreItems;

}
