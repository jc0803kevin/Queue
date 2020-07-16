
## RocketMQ

RocketMQ 就安装的难易程度来说，是一个属于偏高的，不像ActiveMQ那样开箱即用，还需要一点配置

### Core Concept 核心概念

#### Producer
生产者

消息生成者，负责消息产生，由业务系统负责产生

#### Producer Group
生产组

一类producer的集合名称，这类Producer通常发送一类消息，且发送逻辑

具有相同角色的生产者被分组在一起。如果原始生产者在交易后崩溃，则broker 可以联系同一生产者组的不同生产者实例以提交或回滚交易。
#### Consumer
消息消费者，负责消费消息，由后台业务系统负责异步消费

#### Consumer Group
一类Consumer的集合名称，这类Consumer通常消费一类消息，且发送逻辑

#### Topic
Topic 是消息的逻辑分类

#### Message
Message 是发送的信息载体，Message 必须指定 Topic，可以类比信件地址。Message 有一个可选的 Tag 设置便于过滤消息，还可以添加额外的键值对。


#### Message Queue
Topic 会被分为一个或多个 Message Queue

#### Tag
Tag 是 Topic 的进一步细分，为用户提供额外的灵活性。同一业务不同目的的消息可以拥有相同的 Topic 和不同的 Tag。

#### Broker
消息中转角色，负责存储消息，转发消息，一般也称为Server

接收来自消息生产者的消息，存储这些消息并为消息消费者拉动这些消息做准备，也存储消息相关元数据
#### Name Server
Name Server 为消息生产者和消费者提供路由信息

#### Message Model
##### Clustering 集群消费
一个Consumer Group中的Consumer实例平均分摊消费消息。例如某个Topic有9条消息，Consumer Group有3个 实例(可能是3个进程或3台机器)，
那么每个实例只消费其中的3条消费。
##### Broadcasting 广播消费
一条消息被多个Consumer消息，即使这些 Consumer属于同一个 Consumer Group，消息也会被Consumer Group中的每个Consumer都消费一次。

#### Message Order
##### Orderly 顺序消费
指消息的消费顺序和产生顺序相同，在有些业务逻辑 下 ，必须保证顺序。比如订单的生成、付款、发货，这3个消息必须按顺序处理才行。
##### Concurrently 并发消费


## 使用脚本

### 启动mqnamesrv
start mqnamesrv.cmd 


### 启动mqbroker
start mqbroker.cmd -n 127.0.0.1:9876

### 手动创建Topic
```shell script
.\mqadmin updateTopic
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=128m; support was removed in 8.0
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=128m; support was removed in 8.0
usage: mqadmin updateTopic [-b <arg>] [-c <arg>] [-h] [-n <arg>] [-o <arg>] [-p <arg>] [-r <arg>] [-s <arg>]
       -t <arg> [-u <arg>] [-w <arg>]
 -b,--brokerAddr <arg>       create topic to which broker
 -c,--clusterName <arg>      create topic to which cluster
 -h,--help                   Print help
 -n,--namesrvAddr <arg>      Name server address list, eg: 192.168.0.1:9876;192.168.0.2:9876
 -o,--order <arg>            set topic's order(true|false)
 -p,--perm <arg>             set topic's permission(2|4|6), intro[2:W 4:R; 6:RW]
 -r,--readQueueNums <arg>    set read queue nums
 -s,--hasUnitSub <arg>       has unit sub (true|false)
 -t,--topic <arg>            topic name
 -u,--unit <arg>             is unit topic (true|false)
 -w,--writeQueueNums <arg>   set write queue nums

```

mqadmin updateTopic -n 127.0.0.1:9876 -t topic_family -c DefaultCluster -r 6 -w 6

### 查看Topic列表信息
```shell script
start mqadmin.cmd topicList -n 127.0.0.1:9876
```