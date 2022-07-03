package com.example.dailyTarget.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.mapper.PlanTargetMapper;
import com.example.dailyTarget.service.IPlanTargetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 */
@Service
public class PlanTargetServiceImpl extends ServiceImpl<PlanTargetMapper, PlanTarget> implements IPlanTargetService {

    @Resource
    PlanTargetMapper planTargetMapper;

    //获取日记及目标全部记录
    @Override
    public List<PlanTarget> getList(){
        List<PlanTarget> planTargets =
                new LambdaQueryChainWrapper<>(planTargetMapper).orderByDesc(PlanTarget::getCreateTime).list();
        return planTargets;
    }

}
