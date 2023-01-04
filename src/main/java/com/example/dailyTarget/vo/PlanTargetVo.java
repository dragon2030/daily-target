package com.example.dailyTarget.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/7/10
 * @Description:
 */
@Data
public class PlanTargetVo {
//    @ApiModelProperty("目标计划内容（目标项间以“1、XXX。2、XXX。”格式，方便后期io截取统计）")
//    private String planTargetDes;

    @ApiModelProperty("目标计划内容list")
    private List<String> planTargetList;

//    @ApiModelProperty("远期目标对照")
//    private String longTermGoal;

    @ApiModelProperty("远期目标对照list")
    private List<String> longTermGoalList;

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

//    @ApiModelProperty("改变习惯")
//    private String changeHabit;

    @ApiModelProperty("改变习惯list")
    private List<String> changeHabitList;

//    @ApiModelProperty("计划目标核心项")
//    private String targetCoreItems;

    @ApiModelProperty("目标计划内容list）")
    private List<String> targetCoreItemList;
}
