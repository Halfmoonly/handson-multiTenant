package com.lyflexi.samples;

import com.lyflexi.multitenant.config.EnableDynamicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: lyflexi
 * @project: handson-mulit-tenant
 * @Date: 2024/10/21 16:34
 */
@SpringBootApplication
@EnableDynamicDataSource
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
