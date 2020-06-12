package com.kevin.mq.sprintbootrocketmq.producer;

import com.kevin.mq.sprintbootrocketmq.model.User;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/26 9:50
 */

@Component
public class CommonProducer {

    private static final String stringTopic = "string-topic";


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void syncSend(int order){

        SendResult sendResult = rocketMQTemplate.syncSend(stringTopic, order+ "kevin" + System.currentTimeMillis());
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", stringTopic, sendResult);

//        sendResult = rocketMQTemplate.syncSend(stringTopic, User.builder().name("kevin").age(20).toString());
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", stringTopic, sendResult);


//        sendResult = rocketMQTemplate.syncSend(stringTopic, MessageBuilder.withPayload(User.builder().name("kevin").age(20).toString()).build());
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", stringTopic, sendResult);


//        sendResult = rocketMQTemplate.syncSend(stringTopic, "kevin" + System.currentTimeMillis(),1);
//        System.out.printf("syncSend1 to topic %s sendResult=%s %n", stringTopic, sendResult);

    }


    public void asyncSend(int order){

        // 1. 创建消息
        Message message = new Message(stringTopic, // 主题
                "TagA", // 标签
                "key" + order, // 用户自定义的key ,唯一的标识
                ("kevin" + order).getBytes()); // 消息内容实体（byte[]）


        rocketMQTemplate.asyncSend(stringTopic, message, new SendCallback(){

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("onSuccess -> msg= %s %n", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        });

    }


    public void convertAndSend(){
        rocketMQTemplate.convertAndSend(stringTopic + ":tagA", "I'm from tagA");  // tag0 will not be consumer-selected
        System.out.printf("syncSend topic %s tag %s %n", stringTopic, "tagA");
        rocketMQTemplate.convertAndSend(stringTopic + ":tag1", "I'm from tag1");
        System.out.printf("syncSend topic %s tag %s %n", stringTopic, "tag1");
    }

    public void batchMessages(){
        List<org.springframework.messaging.Message> msgs = new ArrayList<org.springframework.messaging.Message>();
        for (int i = 0; i < 100; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }

        SendResult sr = rocketMQTemplate.syncSend(stringTopic, msgs, 60000);
    }


    /**
     * @Author kevin
     * @Description 以同步模式发送，并且接口回复信息
     * @Date Created on 2020/5/26 15:43
     */
    public void sendReply(){
        //String replyString = rocketMQTemplate.sendAndReceive("stringRequestTopic:tagA", "request string kevin:", String.class);
        //System.out.printf("send %s and receive %s %n", "request string", replyString);


    }


    /**
     * @Author kevin
     * @Description 发送延迟消息
     * @Date Created on 2020/6/12 10:06
     * @param
     * @return
     */

}
