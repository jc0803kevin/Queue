package com.kevin.mq.sprintbootrocketmq.config;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 17:45
 */
public class JmsConfig {

    /**
     * Name Server 地址，因为是集群部署 所以有多个用 分号 隔开
     */
    public static final String NAME_SERVER = "127.0.0.1:9876";
    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String PRODUCER = "topic_family_producer";

    public static final String TOPIC = "topic_family";

}
