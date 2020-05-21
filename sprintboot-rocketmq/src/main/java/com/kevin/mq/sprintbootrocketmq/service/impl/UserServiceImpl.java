package com.kevin.mq.sprintbootrocketmq.service.impl;

import com.kevin.mq.sprintbootrocketmq.model.User;
import com.kevin.mq.sprintbootrocketmq.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 17:56
 */
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        log.info("持久化 User : " + user.toString());
    }
}
