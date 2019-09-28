package com.kevin.mq.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author kevin
 * @Description 消费者监听
 * @Date Created on 2019/9/25 11:28
 */
@Component
public class RawDataConsumerListener {

    @KafkaListener(topics = {"test_topic", "test_topic2"})
    public void onMessage(String message){
        System.out.println("test_topic consumer thread name->"+  Thread.currentThread().getName());
        System.out.println("test_topic consumer receive message->"+  message);
    }

    @KafkaListener(topics = {"test_topic2"})
    public void listence(ConsumerRecord<String, String> context){
        //ConsumerRecord(topic = test_topic2, partition = 0, offset = 0, CreateTime = 1569395997850, serialized key size = -1,
        //        serialized value size = 18, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = kevin1569395997499)

        System.out.println("test_topic2 consumer thread name->"+  Thread.currentThread().getName());
        System.out.println("test_topic2 consumer topic name->"+  context.topic());
        System.out.println("test_topic2 consumer receive message->"+  context);
    }

    /**
     * @Author kevin
     * @Description 同一个主题 配置多个消费者 只会消费一次
     * @Date Created on 2019/9/25 15:39
     * @param
     * @return
     */
    @KafkaListener(topics = {"test_topic2"})
    public void receive(ConsumerRecord<String, String> context){

        System.out.println("receive test_topic2 consumer thread name->"+  Thread.currentThread().getName());
        System.out.println("receive test_topic2 consumer topic name->"+  context.topic());
        System.out.println("receive test_topic2 consumer receive message->"+  context);
    }

}
