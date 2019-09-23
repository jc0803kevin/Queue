package com.kevin.mq.activemqdemo.comsumer;

import com.kevin.mq.activemqdemo.config.ActiveMQConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author kevin
 * @Description 消费者
 * @Date Created on 2019/8/1 16:20
 */


@Component
public class ConsumerController {

    /**
     * 点对点模式
     * 生产者发送的消息，只能被一个消费者消费
     *
     *
     * */

    //消费者1
    @JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME)
    //@JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    public void readActiveQueue(String message) {
        try{
            Thread.sleep(1000);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("readActiveQueue kevin  接受到：" + message);

    }
    //消费者2
    @JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME)
    //@JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    public void readActiveQueue2(String message) {
        try{
            Thread.sleep(1000);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }

        System.err.println("readActiveQueue2 kevin  接受到：" + message );

    }

    //消费者3
    @JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME)
    //@JmsListener(destination = ActiveMQConfig.KEVIN_QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    public void readActiveQueue3(String message) {
        try{
            Thread.sleep(1000);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }

        System.err.println("readActiveQueue3 kevin  接受到：" + message);

    }


    @JmsListener(destination = ActiveMQConfig.COCO_QUEUE_NAME)
    //@JmsListener(destination = ActiveMQConfig.COCO_QUEUE_NAME, containerFactory = "jmsListenerContainerQueue")
    public void coco(String message) {
        try{
            Thread.sleep(2000);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("coco  接受到：" + message);

    }

    /**
     *  发布/订阅模式
     *  生产者发送的消息 可以被多个消费者消费
     *
     *
     * **/

    @JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME)
    //@JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void alibaba(String message) {
        try{
            Thread.sleep(1500);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("alibaba  接受到：" + message );

    }


    @JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME)
    //@JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void alibaba2(String message) {
        try{
            Thread.sleep(1500);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("alibaba2  接受到：" + message );

    }


    @JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME)
    //@JmsListener(destination = ActiveMQConfig.ALIBABA_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void alibaba3(String message) {
        try{
            Thread.sleep(1500);
            //TODO something
        }catch (Exception e){
            e.printStackTrace();
        }
        System.err.println("alibaba3  接受到：" + message);

    }

}
