package cn.aezo.demo.rabbitmq.c03_model_fanout;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 测试 fanout（广播） 类型消息模型
 *
 * 结果如下(需要先启动消费者，在运行生产者)：
 * consumer2收到消息：这是 0 条消息
 * consumer1收到消息：这是 0 条消息
 * consumer1收到消息：这是 1 条消息
 * consumer2收到消息：这是 1 条消息
 * consumer1收到消息：这是 2 条消息
 * consumer2收到消息：这是 2 条消息
 * consumer1收到消息：这是 3 条消息
 * consumer2收到消息：这是 3 条消息
 * consumer1收到消息：这是 4 条消息
 * consumer2收到消息：这是 4 条消息
 *
 * @author smalle
 * @date 2020-08-29 16:31
 */
public class Consumer {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {
        consumer1();
        consumer2();
    }

    public static void consumer1() throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 获取一个临时队列。管理后台的Queues-Features会增加"AD"(autoDelete)和"Excl"(exclusive)标识
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("consumer1 queueName = " + queueName); // consumer1 queueName = amq.gen-f1i6OTSGv9-oUK7wsLaCWA
        // **将临时队列和交换机绑定，routingKey留空**
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer1收到消息：" + new String(body, "UTF-8"));
            }
        });
    }

    public static void consumer2() throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("consumer2 queueName = " + queueName);
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer2收到消息：" + new String(body, "UTF-8"));
            }
        });
    }
}
