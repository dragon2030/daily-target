package com.example.dailyTarget.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.service.IPlanTargetService;
import com.example.dailyTarget.vo.PlanTargetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/6/28
 * @Description:
 */
@RestController
@RequestMapping("/planTarget")
@Api(value = "计划目标表", tags = {"计划目标表"})
public class PlanTargetController {

    @Resource
    IPlanTargetService planTargetService;

    @ApiOperation("获取计划目标全部记录")
    @GetMapping(value = "/getList")
    public List<PlanTargetVo> getList(){
        List<PlanTargetVo> list = planTargetService.getList();
        return list;
    }

}
