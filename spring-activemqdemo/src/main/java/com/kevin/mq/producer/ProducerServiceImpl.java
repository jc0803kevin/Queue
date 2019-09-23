package com.kevin.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author kevin
 * @Description 生产者服务实现类
 * @Date Created on 2019/8/4 15:07
 */
public class ProducerServiceImpl implements ProducerService {

    @Resource(name = "producerTemplate")
    private JmsTemplate producerTemplate;

    //@Resource(name = "destination")//队列模式
    @Resource(name = "topicDestination")//主题模式
    private Destination destination;

    @Override
    public void sendMessage(String message) {
        System.out.println("发送内容->" + message);
        producerTemplate.send(destination,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message textMessage = session.createTextMessage(message);
                textMessage.setStringProperty("ID", message);
                return textMessage;
            }
        });

    }
}
