package com.kevin.mq.sprintbootrocketmq.mqlistener;

import com.kevin.mq.sprintbootrocketmq.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 18:32
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = JmsConfig.PRODUCER)
public class UserTopicListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info(msg.toString());
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info(msg.toString());
        return null;
    }
}
