package com.example.dailyTarget;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.dailyTarget.mapper")
//@EnableScheduling
public class DailyTargetApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyTargetApplication.class, args);
    }

}
