package com.example.dailyTarget.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.dailyTarget.dto.EditDailyRecordDto;
import com.example.dailyTarget.mapper.DailyRecordMapper;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.service.IDailyRecordService;
import com.example.dailyTarget.vo.DailyRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/6/26
 * @Description:
 */
@RestController
@RequestMapping("/dailyRecord")
@Api(value = "每日日记及目标完成记录表", tags = {"每日日记及目标完成记录表"})
public class DailyRecordController {

    @Resource
    private IDailyRecordService dailyRecordService;

    @Resource
    private DailyRecordMapper dailyRecordMapper;


    @ApiOperation("获取日记全部记录")
    @GetMapping(value = "/getList")
    public List<DailyRecordVo> getList(){
        List<DailyRecordVo> list = dailyRecordService.getList();
        return list;
    }

    //生成每日记录
    @ApiOperation("生成每日记录")
    @GetMapping(value = "/addRecord")
    public String addRecord(){
        dailyRecordService.addRecord();
        return "SUCCESS";
    }

    //编辑每日记录
    @ApiOperation("编辑每日记录")
    @GetMapping(value = "/editRecord")
    public String editRecord(EditDailyRecordDto dto){
        dailyRecordService.editRecord(dto);
        return "SUCCESS";
    }
}
