package com.kevin.mq.activemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Author kevin
 * @Description 消息队列配置
 * @Date Created on 2019/8/1 16:45
 */
@Component
public class ActiveMQConfig {

    public static final String KEVIN_QUEUE_NAME = "activemq.queue.kevin";
    public static final String COCO_QUEUE_NAME = "activemq.queue.coco";
    public static final String ALIBABA_TOPIC_NAME = "activemq.topic.alibaba";
    public static final String BAIDU_TOPIC_NAME = "activemq.topic.baidu";



    /**
     * @Author kevin
     * @Description 声明一个队列 名称为activemq.queue.kevin 点对点模型
     * @Date Created on 2019/8/1 17:49
     * @param
     * @return
     */
    @Bean
    public Queue kevin(){
        return new ActiveMQQueue(ActiveMQConfig.KEVIN_QUEUE_NAME);
    }

    /**
     * @Author kevin
     * @Description 声明一个队列 名称为activemq.queue.coco 点对点模型
     * @Date Created on 2019/8/1 17:49
     * @param
     * @return
     */
    @Bean
    public Queue coco(){
        return new ActiveMQQueue(ActiveMQConfig.COCO_QUEUE_NAME);
    }


    /**
     * @Author kevin
     * @Description 声明一个主题 发布/订阅模式
     * @Date Created on 2019/8/2 10:03
     * @param
     * @return
     */
    @Bean
    public Topic alibaba(){
        return new ActiveMQTopic(ActiveMQConfig.ALIBABA_TOPIC_NAME);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);//默认为false queue模式
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(false);//默认为false queue模式
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory){
        return new JmsMessagingTemplate(connectionFactory);
    }
}
