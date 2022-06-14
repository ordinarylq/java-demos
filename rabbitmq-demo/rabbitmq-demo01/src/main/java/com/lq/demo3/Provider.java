package com.lq.demo3;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import sun.plugin2.message.Message;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author qili
 * @create 2022-06-14-20:04
 */
public class Provider {
    public static final Integer MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        // 1. 发布确认：单个确认 -> 同步确认
        // 发布1000条消息，单个确认->耗时3596ms
        // Provider.publishMessageIndividually();

        // 2. 发布确认：批量确认
        // 发布1000条消息，批量确认->耗时152ms
        Provider.publishMessageBatch();

        // 3. 发布确认：异步确认
        // 发布1000条消息，异步确认->耗时51ms
        //Provider.publishMessageAsync();
    }

    static void publishMessageIndividually() throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 开启发布确认功能
        channel.confirmSelect();

        // 3. 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        // 4. 发布消息
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            // 每发送一条消息，就需要等待RabbitMQ发送确认消息
            String message = "消息" + i;
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            boolean flag = channel.waitForConfirms();
            if(flag) {
                System.out.println("【" + message + "】消息发送成功！");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "条消息，单个确认->耗时" + (endTime - startTime) + "ms");
    }

    static void publishMessageBatch() throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 设置发布确认
        channel.confirmSelect();

        // 3. 声明队列
        String queueName = "b6271c8c-5cc1-4311-ad78-7b68e690252f";
        channel.queueDeclare(queueName, true, false, false, null);

        // 4. 发送消息
        int batchSize = 100;
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= MESSAGE_COUNT; i++) {
            String message = "消息" + i;
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            if(i % batchSize == 0) {
                boolean flag = channel.waitForConfirms();
//                if(flag) {
//                    System.out.println("批量消息发送成功！");
//                }
            }
        }

        // 5. 批量确认
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "条消息，批量确认->耗时" + (endTime - startTime) + "ms");

    }

    static void publishMessageAsync() throws Exception {
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 开启发布确认
        channel.confirmSelect();

        // 3. 创建消息存储的线程安全的map,，key-消息序列号，value-消息内容
        ConcurrentSkipListMap<Long, String> toConfirmMap = new ConcurrentSkipListMap<>();

        // 4. 设置确认发布回调函数(成功回调、失败回调)
        // 4.1 multiple = true 可以确认消息序列化<=当前序列号的消息
        // 4.2 multiple = false 只确认当前序列号消息
        // 成功回调
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            if(multiple) {
                // headMap(K toKey, boolean inclusive) 返回map中key值小于等于toKey所有元素，返回值是ConcurrentNavigableMap<K,V>
                ConcurrentNavigableMap<Long, String> confirmedMap = toConfirmMap.headMap(deliveryTag, true);
                // 清除掉已经确认发布的消息(<=当前序列号的所有消息)
                confirmedMap.clear();
            } else {
                // 清除掉当前序列号消息
                toConfirmMap.remove(deliveryTag);
            }
        };

        // 失败回调
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String body = toConfirmMap.get(deliveryTag);
            System.err.format(
                    "Message with body %s has been nack-ed. Sequence number: %d, multiple: %b%n",
                    body, deliveryTag, multiple
            );
            ackCallback.handle(deliveryTag, multiple);
        };

        // 5. 设置确认消息监视器
        channel.addConfirmListener(ackCallback, nackCallback);

        // 6. 设置队列
        String queueName = "b6271c8c-5cc1-4311-ad78-7b68e690252f";
        channel.queueDeclare(queueName, true, false, false, null);

        // 7. 发送消息
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;
            // 将消息存到map中，便于后续发布确认
            // 序列号需要在消息发布前调用
            long seqNum = channel.getNextPublishSeqNo();
            toConfirmMap.put(seqNum, message);

            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));

        }


        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "条消息，异步确认->耗时" + (endTime - startTime) + "ms");
    }
}
