package com.kevin.mq.springbootkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author kevin
 * @Description 消费者监听
 * @Date Created on 2019/9/25 11:28
 */
@Component
public class RawDataConsumerListener {

    @KafkaListener(topics = {"test_topic"})
    public void onMessage(String message){
        System.out.println("consumer receive message->"+  message);
    }

}
