package com.kevin.mq.sprintbootrocketmq.mqlistener;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kevin.mq.sprintbootrocketmq.config.JmsConfig;
import com.kevin.mq.sprintbootrocketmq.model.User;
import com.kevin.mq.sprintbootrocketmq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 18:32
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = JmsConfig.PRODUCER)
public class UserTopicListener implements RocketMQLocalTransactionListener {

    @Autowired
    private UserService userService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info(msg.toString());


        try {

            String msgStr = new String((byte[])msg.getPayload());
            User user = JSON.parseObject(msgStr, User.class);

            userService.save(user);
        }catch (Exception e){
            log.error("监听 捕获异常 ：", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }


        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info(msg.toString());
        return RocketMQLocalTransactionState.COMMIT;
    }
}
