package com.ytonline.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ytonline.aclservice.mapper")
@ComponentScan(basePackages = {"com.ytonline"})
public class ServiceAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }

}
