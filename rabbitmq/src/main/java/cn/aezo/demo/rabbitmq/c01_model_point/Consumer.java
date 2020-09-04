package cn.aezo.demo.rabbitmq.c01_model_point;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author smalle
 * @date 2020-08-29 16:31
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列时，参数需要和生产者定义的队列一样
        channel.queueDeclare("hello", false, false, false, null);

        /*
         * 消费消息，参数如下：
         * 1.queue: 消息队列名
         * 2.autoAck: 是否开启自动消息确认机制。true开启自动确认。具体看c02_work
         * 3.deliverCallback: 收到消息回调
         * 4.cancelCallback
         * */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到消息：" + new String(body, "UTF-8"));
            }
        });

        // 测试时不关闭，防止还没进行 handle 就退出了
        // RabbitmqU.close(channel, connection);
    }
}
