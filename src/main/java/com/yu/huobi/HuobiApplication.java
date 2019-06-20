package com.yu.huobi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@MapperScan("com.yu.huobi.dao")
public class HuobiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuobiApplication.class, args);
    }

}

