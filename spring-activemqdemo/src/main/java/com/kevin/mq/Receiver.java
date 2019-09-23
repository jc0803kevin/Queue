package com.kevin.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消费者
 * 需要一直保持运行状态 处于监听状态
 *
 */
public class Receiver {
    public static void main( String[] args )
    {
        //只需要把容器启动即可，保证消费者一直在线
        ApplicationContext context = new ClassPathXmlApplicationContext("comsumer.xml");

    }
}
