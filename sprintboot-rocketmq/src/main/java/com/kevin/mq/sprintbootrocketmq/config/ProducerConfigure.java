package com.kevin.mq.sprintbootrocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.admin.ConsumeStats;
import org.apache.rocketmq.common.admin.OffsetWrapper;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 18:54
 */

@Configuration
@Slf4j
public class ProducerConfigure {

    private DefaultMQProducer producer;
    private DefaultMQAdminExt defaultMQAdminExt;

    private String groupName;
    private String topicName;

    //@Bean
    //@ConditionalOnProperty(prefix = "rocketmq.producer", value = "default", havingValue = "true")
    public DefaultMQProducer defaultProducer() throws MQClientException {
        log.info("defaultProducer 正在创建---------------------------------------");
        DefaultMQProducer producer = new DefaultMQProducer("kevin-group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq producer server开启成功---------------------------------.");
        return producer;
    }

    public void init() {
        //初始化生产者客户端
        try {
            producer = new DefaultMQProducer("kevin-group");
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();
        } catch (MQClientException e) {
            log.error(e.getMessage());
        }
        //初始化监控客户端
        try {
            defaultMQAdminExt = new DefaultMQAdminExt();
            defaultMQAdminExt.setInstanceName(Long.toString(System.currentTimeMillis()));
            defaultMQAdminExt.setNamesrvAddr("127.0.0.1:9876");
            defaultMQAdminExt.start();
        }catch (Exception e){
            log.error("监控客户端启动失败...", e);
        }
        //当jvm关闭的时,关闭两个客户端
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                producer.shutdown();
                defaultMQAdminExt.shutdown();
            }
        }));
    }


    /**
     * @Author kevin
     * @Description 获取消息堆积量
     * \org\apache\rocketmq\rocketmq-common\4.4.0\rocketmq-common-4.4.0.jar!\org\apache\rocketmq\common\admin\ConsumeStats.class
     * @Date Created on 2020/7/6 13:48
     * @param
     * @return
     */
    public long getDiffNum(){
        long diffTotal = 0L;
        try{
            //当消费端未消费时，此方法会报错
            ConsumeStats consumeStats = defaultMQAdminExt.examineConsumeStats(this.groupName);
            List<MessageQueue> mqList = new LinkedList();
            mqList.addAll(consumeStats.getOffsetTable().keySet());
            Collections.sort(mqList);
            //遍历所有的队列，计算堆积量
            for (MessageQueue mq : mqList) {
                //只计算group下此生产端发送对应的Topic
                if(this.topicName.equals(mq.getTopic())){
                    OffsetWrapper offsetWrapper = (OffsetWrapper)consumeStats.getOffsetTable().get(mq);
                    long diff = offsetWrapper.getBrokerOffset() - offsetWrapper.getConsumerOffset();
                    diffTotal += diff;
                }
            }
        }catch(Throwable e){
            log.error("监控客户端获取消息堆积量异常，未能正常获取消息堆积量，消息堆积量默认设置为0", e);
            //此中出现任何错误，均返回堆积量为0；
            diffTotal = 0L;
            /**
             * 此处屏蔽不要了，鉴于这种情况只是刚开始时消费端未消费时会出现，发生频率低，
             * 且捕获异常后获取队列偏移量，无法确定异常类型，
             * 会对后面的逻辑处理造成影响，故不兼容此情况了
             */
            //当只有生产，还未有消费时，上述方法会报错，
            //这是只需获取topic中所有的Queue最大位移和即为消息堆积量
//            try{
//                TopicStatsTable topicStatsTable = defaultMQAdminExt.examineTopicStats(this.topicName);
//                List<MessageQueue> mqList = new LinkedList();
//                mqList.addAll(topicStatsTable.getOffsetTable().keySet());
//                Collections.sort(mqList);
//                diffTotal = 0L;
//                for (MessageQueue mq : mqList) {
//                    TopicOffset topicOffset = (TopicOffset)topicStatsTable.getOffsetTable().get(mq);
//                    long diff = topicOffset.getMaxOffset() - topicOffset.getMinOffset();
//                    diffTotal += diff;
//                }
//            }catch(Throwable e1){
//                logger.error("监控获取消息堆积量出错，返回消息堆积量为0");
//                logger.error(e.getMessage(), e);
//                logger.error(e1.getMessage(), e1);
//                diffTotal = 0L;
//            }
        }
        return diffTotal;
    }
}
