package com.kevin.mq.sprintbootrocketmq;

import com.kevin.mq.sprintbootrocketmq.producer.CommonProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/26 9:57
 */
public class CommonProducerTest  extends  SprintBootRocketMQApplicationTests{

    @Autowired
    private CommonProducer commonProducer;

    @Test
    public void syncSend(){

        for (int i = 0; i < 10000; i++) {

            commonProducer.asyncSend(i);
        }

    }


}
