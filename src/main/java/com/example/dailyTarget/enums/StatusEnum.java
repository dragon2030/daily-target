package com.example.dailyTarget.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: bigDragon
 * @create: 2022/6/27
 * @Description:
 *  状态(1->启用;0->停用)
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    ENABLE("1","启用"),
    DISABLE("0","停用");

    private String code;
    private String value;
}
