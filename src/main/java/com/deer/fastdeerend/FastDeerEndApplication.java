package com.deer.fastdeerend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication


@MapperScan("com.deer.fastdeerend.dao")
public class FastDeerEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastDeerEndApplication.class, args);
    }
}