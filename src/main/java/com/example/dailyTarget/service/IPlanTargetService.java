package com.example.dailyTarget.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.vo.PlanTargetVo;

import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
public interface IPlanTargetService extends IService<PlanTarget> {

    //获取日记及目标全部记录
    List<PlanTargetVo> getList();

}
