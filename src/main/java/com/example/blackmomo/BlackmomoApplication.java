package com.example.blackmomo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.example.blackmomo.mapper"})
@SpringBootApplication
public class BlackmomoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackmomoApplication.class, args);
    }

}