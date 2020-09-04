package cn.aezo.demo.rabbitmq.c02_model_work;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 测试可以有多个而消费者消费同一个队列，且设置autoAck=false防止平均消费(即希望能者多劳)
 *
 * 打印结果：
 * consumer1收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 * consumer2收到消息：这是一条消息
 *
 * @author smalle
 * @date 2020-08-29 16:31
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        consumer1();
        consumer2();
    }

    public static void consumer1() throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        // 设置prefetchCount=1，一次只能消费一个消息，否则MQ会把消息队列的中的消息都发channel中，可能导致数据丢失
        channel.basicQos(1);
        channel.queueDeclare("hello", false, false, false, null);

        /*
        * autoAck: 是否开启自动消息确认机制
        * 1.autoAck=true 开启自动确认。则消息发送到channel，不管是否已经消费完成，都会告诉服务端，从而服务端会继续发消息。当有多个消费者的时候是平均消费消息的
        * 2.autoAck=false 关闭消息自动确认，即消费完消息后需要手动确认告知MQ服务端。到MQ发送到消费者的Channel，但是又没有手动确认的，会在管理端的Queues-Messages-Unacked显示未确认数
        * */
        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer1收到消息：" + new String(body, "UTF-8"));

                // 处理消息逻辑...
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 手动确认消息。参数1：确认的消息ID，参数2：是否一次确认多条消息（由于上文开启了设置了channel一次只能消费1条消息，因此此处无需开启多条消息确认）
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    public static void consumer2() throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("hello", false, false, false, null);

        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer2收到消息：" + new String(body, "UTF-8"));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
