package cn.aezo.demo.rabbitmq.c02_model_work;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 测试Work模式
 *
 * @author smalle
 * @date 2020-08-29 16:12
 */
public class Publish {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", false, false, false, null);
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "hello", null, "这是一条消息".getBytes());
        }

        // 之后关掉 connection 程序才会退出，否则会一直运行
        RabbitmqU.close(channel, connection);
    }
}
