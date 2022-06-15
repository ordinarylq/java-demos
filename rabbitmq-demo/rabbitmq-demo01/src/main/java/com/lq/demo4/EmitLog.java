package com.lq.demo4;

import com.lq.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @ClassName: EmitLog
 * @Author: LiQi
 * @Date: 2022-06-15 14:26
 * @Version: V1.0
 * @Description:
 */
public class EmitLog {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 2. 开启发布确认
        channel.confirmSelect();

        // 3. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 4. 创建存储消息的map
        ConcurrentSkipListMap<Long, String> toConfirmMap = new ConcurrentSkipListMap<>();

        // 5. 设置确认发布的回调函数(成功、失败)
        ConfirmCallback confirmCallback = (deliveryTag, multiple) -> {
            if(multiple) {
                // 批量确认，则删除key<=deliveryTag的所有消息
                ConcurrentNavigableMap<Long, String> confirmedMap = toConfirmMap.headMap(deliveryTag);
                confirmedMap.clear();
            } else {
                // 单个确认
                toConfirmMap.remove(deliveryTag);
            }
        };

        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = toConfirmMap.get(deliveryTag);
            System.err.format("Message with body %s has been nack-ed. Sequence number: %d, multiple: %b%n",
                    message, deliveryTag, multiple
            );
            // 删除掉发布失败的消息
            confirmCallback.handle(deliveryTag, multiple);
        };

        // 6. 设置确认的监听器
        channel.addConfirmListener(confirmCallback, nackCallback);

        // 4. 发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            toConfirmMap.put(channel.getNextPublishSeqNo(), message);
            System.out.println("发布消息：" + message);
            channel.basicPublish(EXCHANGE_NAME, "test", null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
