package com.kevin.mq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/8/11 14:43
 */
public class ConsumerMessageListener implements MessageListener {

    /**
     * @Author kevin
     * @Description 消息监听只需要实现一个方法
     * @Date Created on 2019/8/11 15:01
     * @param  
     * @return 
     */
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收消息："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
