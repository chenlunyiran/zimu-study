package com.zimu.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName:Consumer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-05-26 16:47 <br/>
 *
 * @author Administrator
 * @see
 * @since JDK 1.8
 */
public class Consumer_order {

        /**
         * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
         * 但是实际PushConsumer内部是使用长轮询Pull方式从RocketMQ服务器拉消息，然后再回调用户Listener方法<br>
         */
        public static void main(String[] args) throws InterruptedException, MQClientException {
            /**
             * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
             * 注意：ConsumerGroupName需要由应用来保证唯一
             */
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
//            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer.setNamesrvAddr("192.168.32.10:9876;192.168.32.11:9876");
            consumer.setInstanceName("Consume2");
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            /**
             * 订阅指定topic下tags分别等于TagA或TagC或TagD
             */
//            consumer.subscribe("TopicTest", "TagA || TagC || TagD");
            /**
             * 订阅指定topic下所有消息<br>
             * 注意：一个consumer对象可以订阅多个topic
             */
//            consumer.subscribe("TopicTest1", "*");
//            consumer.subscribe("TopicTest2", "*");
//            consumer.subscribe("TopicTest3", "*");
            
            for (int i = 0; i < 10; i++) {
                consumer.subscribe("TopicTest" + (i%3+1), "*");
            }

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                
                AtomicLong count = new AtomicLong(0);
                
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    
                    System.out.println(Thread.currentThread().getName()  + " Receive New Messages: " + msgs.size());

                    MessageExt msg = msgs.get(0);
                    
                    System.out.println(msg.getKeys() + "    " + new String(msg.getBody()) + "  StoreHost:"+msg.getStoreHost() + "  QueueId:" + msg.getQueueId());
                    count.incrementAndGet();
                    
                    System.out.println("Consume Count:"+count+ "\r");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                
            });

            
            /**
             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             */
            consumer.start();

            System.out.println("Consumer_order Started.");
        }
}
