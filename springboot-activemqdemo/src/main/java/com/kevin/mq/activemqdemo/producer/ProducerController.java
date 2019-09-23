package com.kevin.mq.activemqdemo.producer;

//import org.apache.activemq.broker.region.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

@RestController
public class ProducerController{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    private Queue kevin;

    @Autowired
    private Queue coco;

    @Autowired
    private Topic alibaba;



    @RequestMapping("/send")
      public String sendmsg(String msg){
        msg = msg + "->" + UUID.randomUUID();
        System.out.println("msg-->" + msg );
        return msg;
      }


    @RequestMapping("/kevin")
    public String kevin(String msg){
        msg = msg + "->" + UUID.randomUUID();
         System.out.println("kevin send msg-->" + msg);
         jmsMessagingTemplate.convertAndSend(this.kevin, msg);
        return msg;
     }

    @RequestMapping("/coco")
    public String coco(String msg){
        msg = msg + "->" + UUID.randomUUID();
         System.out.println("coco send msg-->" + msg);
         jmsMessagingTemplate.convertAndSend(this.coco, msg);
        return msg;
     }


    @RequestMapping("/alibaba")
    public String alibaba(String msg){
        msg = msg + "->" + UUID.randomUUID();
         System.out.println("alibaba send msg-->" + msg);
         jmsMessagingTemplate.convertAndSend(this.alibaba, msg);
        return msg;
     }
}