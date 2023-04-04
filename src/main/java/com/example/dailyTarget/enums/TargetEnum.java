package com.example.dailyTarget.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: bigDragon
 * @create: 2023/4/4
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum TargetEnum {
    SPORT_TARGET("1","sportTarget"),
    STUDY_TARGET("2","studyTarget"),
    SLEEP_TARGET("3","sleepTarget");

    private String code;
    private String value;
}
