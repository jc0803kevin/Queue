package com.kevin.mq.sprintbootrocketmq.Controller;

import com.alibaba.fastjson.JSON;
import com.kevin.mq.sprintbootrocketmq.config.JmsConfig;
import com.kevin.mq.sprintbootrocketmq.model.User;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kevin
 * @Description 发送消息
 * @Date Created on 2020/5/25 17:23
 */

@RestController
@RequestMapping("/message")
public class ProducerController {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("/send")
    public String send(@RequestParam("name") String name, @RequestParam("age") Integer age){

        User user = User.builder()
                .name(name)
                .age(age)
                .build();

        org.apache.rocketmq.common.message.Message message =
                new org.apache.rocketmq.common.message.Message(JmsConfig.TOPIC, "*", name, JSON.toJSONString(user).getBytes());


        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sendResult.getMsgId();
    }

}
