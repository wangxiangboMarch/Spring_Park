package com.wxb.eduServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wxb"}) // 扫描规则（扫描到base模块中的配置类）
public class EduServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServerApplication.class, args);
    }
}
