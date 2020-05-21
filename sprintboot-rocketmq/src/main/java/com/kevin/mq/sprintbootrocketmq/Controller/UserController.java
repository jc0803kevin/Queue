package com.kevin.mq.sprintbootrocketmq.Controller;

import com.alibaba.fastjson.JSON;
import com.kevin.mq.sprintbootrocketmq.config.JmsConfig;
import com.kevin.mq.sprintbootrocketmq.model.User;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 18:00
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/")
    public String index(){
        return System.currentTimeMillis()+"";
    }

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/add")
    public String add(@RequestParam("name") String name, @RequestParam("age") Integer age){

        User user = User.builder().name(name).age(age).build();

        MessageBuilder messageBuilder = MessageBuilder.withPayload(JSON.toJSONString(user));
        messageBuilder.setHeader(RocketMQHeaders.KEYS, name);
        Message message =messageBuilder.build();


        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(JmsConfig.PRODUCER, JmsConfig.TOPIC, message, null);


        return result.getMsgId();
    }

}
