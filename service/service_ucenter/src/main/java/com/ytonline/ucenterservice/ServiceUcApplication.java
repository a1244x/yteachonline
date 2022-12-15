package com.ytonline.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.ytonline"})
@SpringBootApplication//取消数据源自动配置
@MapperScan("com.ytonline.ucenterservice.mapper")
@EnableDiscoveryClient
@EnableCaching
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}