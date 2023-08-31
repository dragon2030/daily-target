package com.example.dailyTarget.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: bigDragon
 * @create: 2023/5/22
 * @Description:
 */
@Data
public class ExportDataQuery {
    private String startTime;
    private String endTime;
}
