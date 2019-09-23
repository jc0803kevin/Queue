package com.kevin.mq.producer;

/**
 * @Author kevin
 * @Description 生产者服务
 * @Date Created on 2019/8/4 15:07
 */
public interface ProducerService {

    void sendMessage(String message);

}
