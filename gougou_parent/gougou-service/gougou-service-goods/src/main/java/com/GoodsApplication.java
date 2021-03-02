package com;

import com.gg.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages ={"com.gg"})
@EnableEurekaClient //开启eureka客户端
@MapperScan(basePackages = {"com.gg.dao"}) //开启通用mapper的包扫描
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
         return new IdWorker(0,0);
    }
}
