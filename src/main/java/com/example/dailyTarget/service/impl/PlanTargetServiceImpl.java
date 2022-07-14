package com.example.dailyTarget.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dailyTarget.entity.PlanTarget;
import com.example.dailyTarget.mapper.PlanTargetMapper;
import com.example.dailyTarget.service.IPlanTargetService;
import com.example.dailyTarget.service.convert.ConvertService;
import com.example.dailyTarget.vo.PlanTargetVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    ConvertService convertService;

    //获取日记及目标全部记录
    @Override
    public List<PlanTargetVo> getList(){
        List<PlanTarget> planTargets =
                new LambdaQueryChainWrapper<>(planTargetMapper).orderByDesc(PlanTarget::getStatus,PlanTarget::getCreateTime).list();

        List<PlanTargetVo> planTargetVos = new ArrayList<>();
        for(PlanTarget planTarget : planTargets){
            PlanTargetVo planTargetVo = new PlanTargetVo();
            BeanUtil.copyProperties(planTarget, planTargetVo);

            List<String> planTargetList = convertService.parsePlanTargetDesToList(planTarget.getPlanTargetDes());
            planTargetVo.setPlanTargetList(planTargetList);

            List<String> targetCoreItemList = convertService.parsePlanTargetDesToList(planTarget.getTargetCoreItems());
            planTargetVo.setTargetCoreItemList(targetCoreItemList);

            planTargetVos.add(planTargetVo);
        }

        return planTargetVos;
    }

}
