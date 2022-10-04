package com.heiqiwatchman.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongfengw
 * @create 2022-09-24 10:02
 * @Description:
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.heiqiwatchman"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
