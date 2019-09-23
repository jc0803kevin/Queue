package com.kevin.mq.activemqdemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author kevin
 * @Description 消息的接收者
 * @Date Created on 2019/8/2 15:34
 */
public class Receive {
    /**消息服务器的连接地址**/
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) {
        Receive receiver = new Receive();
        receiver.receiveMessage();
    }

    /**
     * 接收消息
     *
     */
    public void receiveMessage () {
        Connection connection = null;
        Session session = null;
        MessageConsumer messageConsumer = null;

        try{
            //1.创建一个连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            //2.创建一个连接
            connection = connectionFactory.createConnection();
            //3.创建一个Session回话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //4.创建一个目的地
            Destination destination = session.createQueue("kevin.shoudong.queue");

            //5.创建一个消费者
            //selector即为消息选择器,通过选择需要的标识,过滤消息接受id为10-15之 //间的消息
            String selector = "Id >=10 and Id<=15";
            messageConsumer = session.createConsumer(destination, selector);
            //messageConsumer = session.createConsumer(destination);

            //接收消息之前 需要启动连接
            connection.start();
            while (true){
                Message message = messageConsumer.receive();
                if (message instanceof TextMessage){
                    //判断是否是文本消息
                    String text = ((TextMessage) message).getText();
                    System.out.println("接收到的消息内容是：" + text);
                }else{

                    System.out.println("接收到的消息内容是   非文本消息" );
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                //关闭连接释放资源
                if (null != messageConsumer) {
                    messageConsumer.close();
                }
                if (null != session) {
                    session.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


    }

}
