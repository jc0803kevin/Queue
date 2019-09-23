package com.kevin.mq;

import com.kevin.mq.producer.ProducerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 发送者
 *
 */
public class Sender {
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService producerService = context.getBean(ProducerService.class);

        for (int i = 0; i < 10; i++) {
            producerService.sendMessage("message =》" + i);
        }
        context.close();
    }
}
