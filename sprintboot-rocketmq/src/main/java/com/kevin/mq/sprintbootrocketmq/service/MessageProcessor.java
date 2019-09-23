package com.kevin.mq.sprintbootrocketmq.service;

import com.alibaba.fastjson.JSON;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/9/10 17:58
 */
public interface MessageProcessor<T> {

    boolean handleMessage(T message);

    Class<T> getClazz();

    default T transferMessage(String message) {
        return JSON.parseObject(message, getClazz());
    }

}
