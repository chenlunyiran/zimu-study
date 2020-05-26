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
public class Consumer {

        /**
         * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
         * 但是实际PushConsumer内部是使用长轮询Pull方式从RocketMQ服务器拉消息，然后再回调用户Listener方法<br>
         */
        public static void main(String[] args) throws InterruptedException, MQClientException {
            /**
             * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
             * 注意：ConsumerGroupName需要由应用来保证唯一
             */
            DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer("ConsumerGroupName");
//            consumer.setNamesrvAddr("127.0.0.1:9876");
            consumer2.setNamesrvAddr("192.168.32.10:9876;192.168.32.11:9876");
            consumer2.setInstanceName("Consume2");
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            /**
             * 订阅指定topic下tags分别等于TagA或TagC或TagD
             */
            consumer2.subscribe("TopicTest1", "TagA || TagC || TagD");
            /**
             * 订阅指定topic下所有消息<br>
             * 注意：一个consumer对象可以订阅多个topic
             */
            consumer2.subscribe("TopicTest2", "*");
            
            

            consumer2.registerMessageListener(new MessageListenerConcurrently() {
                
                AtomicLong count = new AtomicLong(0);
                
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    
                    System.out.println(Thread.currentThread().getName()  + " Receive New Messages: " + msgs.size());

                    MessageExt msg = msgs.get(0);
                    if (msg.getTopic().equals("TopicTest1")) {
                        //执行TopicTest1的消费逻辑
                        if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                            //执行TagA的消费
                            System.out.println(new String(msg.getBody()) + "    " + msg.getKeys()+ "  StoreHost:"+msg.getStoreHost() +  "  QueueId:" + msg.getQueueId());
                            count.incrementAndGet();
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagC")) {
                            //执行TagC的消费
                            System.out.println(new String(msg.getBody()) + "    " + msg.getKeys()+ "  StoreHost:"+msg.getStoreHost() + "  QueueId:" + msg.getQueueId());
                            count.incrementAndGet();
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagD")) {
                            //执行TagD的消费
                            System.out.println(new String(msg.getBody()));
                            count.incrementAndGet();
                        }
                    } else if (msg.getTopic().equals("TopicTest2")) {
                        System.out.println(new String(msg.getBody()) + "    " + msg.getKeys()+ "  StoreHost:"+msg.getStoreHost() + "  QueueId:" + msg.getQueueId());
                        count.incrementAndGet();
                    }
                    
                    System.out.println("Consume Count:"+count+ "\r");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                
            });

            
            /**
             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             */
            consumer2.start();

            System.out.println("Consumer2 Started.");
        }
}
