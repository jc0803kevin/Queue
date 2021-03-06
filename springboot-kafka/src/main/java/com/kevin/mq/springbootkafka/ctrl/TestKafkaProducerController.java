package com.kevin.mq.springbootkafka.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kevin
 * @Description 生产者
 * @Date Created on 2019/9/19 21:46
 */

@RestController
@RequestMapping("/kafka")
public class TestKafkaProducerController {

    @Autowired(required = false )
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public String send(String message){
        String temp = String.valueOf(System.currentTimeMillis());
        message = message + temp;
        kafkaTemplate.send("test_topic", message);
        kafkaTemplate.send("test_topic2", message);
        System.out.println("producer send message-->" + message);
        return message;
    }

}
