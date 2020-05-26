package com.kevin.mq.sprintbootrocketmq.mqlistener;

import com.kevin.mq.sprintbootrocketmq.config.JmsConfig;
import com.kevin.mq.sprintbootrocketmq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author kevin
 * @Description 消费者
 * @Date Created on 2020/5/25 17:06
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = JmsConfig.TOPIC,
        consumerGroup = JmsConfig.PRODUCER
)
public class UserMessageListener implements RocketMQListener<User> {
    @Override
    public void onMessage(User message) {
        log.info("消费者 处理user: {}", message );
    }
}
