package com.kevin.mq.sprintbootrocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author kevin
 * @Description 启动类
 * @Date Created on 2019/12/24 14:43
 */

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.kevin.mq"})
public class SprintBootRocketMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintBootRocketMQApplication.class, args);
    }

}
