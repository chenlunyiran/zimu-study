package com.zimu.mq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:Producer_order <br/>
 * Function: 生产者  有序（同一个订单会顺序消费）. <br/>
 * Reason:  把订单号取了做了一个取模运算再丢到selector中，selector保证同一个模的都会投递到同一条queue. <br/>
 * Date:     2017-05-26 16:28 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class Producer_order {

    public static void main(String[] args) throws MQClientException,
            InterruptedException {
        /**
         * 一个应用创建一个Producer ，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        final DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
//        producer.setNamesrvAddr("127.0.0.1:9876");
//        producer.setNamesrvAddr("192.168.32.10:9876");
        producer.setNamesrvAddr("192.168.32.10:9876;192.168.32.11:9876");
        producer.setInstanceName("Producer_order");
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

        /**
         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
         */
        for (int i = 0; i < 10; i++) {
            try {
                int orderId = i+1;
                Message msg = new Message("TopicTest" + (i%3+1), "TagA", "OrderID00" + (i+1) , ("Order Create").getBytes());
//                SendResult sendResult = producer.send(msg);
//                System.out.println(sendResult);
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        int index = id % list.size();
                        return list.get(index);
                    }
                },orderId);
                System.out.println(sendResult);

                Message msg2 = new Message("TopicTest" + (i%3+1), "TagB", "OrderID00" + (i+1), ("Order Pay").getBytes());
//                SendResult sendResult2 = producer.send(msg2);
//                System.out.println(sendResult2);
                SendResult sendResult2 = producer.send(msg2, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        int index = id % list.size();
                        return list.get(index);
                    }
                },orderId);
                System.out.println(sendResult2);

                Message msg3 = new Message("TopicTest" + (i%3+1), "TagC", "OrderID00" + (i+1), ("Order Complete").getBytes());
//                SendResult sendResult3 = producer.send(msg3);
//                System.out.println(sendResult3);
                SendResult sendResult3 = producer.send(msg3, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer id = (Integer) o;
                        int index = id % list.size();
                        return list.get(index);
                    }
                },orderId);
                System.out.println(sendResult3);

            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }

        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
         */
//    producer.shutdown();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                producer.shutdown();
            }
        }));
        System.exit(0);
    }

}
