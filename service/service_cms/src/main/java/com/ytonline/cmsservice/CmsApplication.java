package com.ytonline.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ytonline"}) //指定扫描位置
@MapperScan("com.ytonline.cmsservice.mapper")
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}