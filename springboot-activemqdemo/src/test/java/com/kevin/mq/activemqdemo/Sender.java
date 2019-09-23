package com.kevin.mq.activemqdemo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author kevin
 * @Description 消息发送者
 * @Date Created on 2019/8/2 15:21
 */
public class Sender {
    /**消息服务器的连接地址**/
    public static final String BROKER_URL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) {
        Sender sender = new Sender();
        sender.sendMessage("Kevin Hello ActiveMQ.");
    }


    public void sendMessage (String msg) {

        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;

        try {
            //1.创建一个连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            //2.创建一个连接
            connection = connectionFactory.createConnection();
            //3.创建一个Session回话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //4.创建一个目的地
            Destination destination = session.createQueue("kevin.shoudong.queue");

            //5.创建一个消息的生产者
            messageProducer = session.createProducer(destination);

            //6.设置消息是否需要持久化
            //messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

            for (int i=0;i<20;i++){
                Message message = session.createTextMessage("kevin "+ i);
                message.setIntProperty("Id", i);
                //发送消息
                messageProducer.send(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                //关闭连接释放资源
                if (null != messageProducer) {
                    messageProducer.close();
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
