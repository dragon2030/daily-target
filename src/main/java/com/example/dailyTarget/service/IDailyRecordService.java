package com.example.dailyTarget.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dailyTarget.dto.EditDailyRecordDto;
import com.example.dailyTarget.entity.DailyRecord;
import com.example.dailyTarget.vo.DailyRecordVo;

import java.util.List;

/**
 * @author: bigDragon
 * @create: 2022/6/26
 * @Description:
 */
public interface IDailyRecordService extends IService<DailyRecord> {

    //生成每日记录
    void addRecord();

    //编辑每日记录
    void editRecord(EditDailyRecordDto dto);

    //获取日记及目标全部记录
    List<DailyRecordVo> getList();
}
