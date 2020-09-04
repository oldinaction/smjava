package cn.aezo.demo.rabbitmq.c04_model_direct;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 测试 direct 类型消息模型
 *
 * @author smalle
 * @date 2020-08-29 16:12
 */
public class Publish {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqU.getConnection();
        Channel channel = connection.createChannel();

        /*
        * 什么一个Exchange交换机，参数如下
        * 1.exchange: 交换机名称
        * 2.type: 交换机类型，fanout/direct/topic等。在管理端Exchanges-Type中会显示
        * */
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String[] types = {"info", "warning", "error"};
        for (int i = 0; i < 5; i++) {
            // 数据发送到交换机
            String routingKey = types[i % 3];
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, ("[" + routingKey + "] 这是 " + i + " 条消息").getBytes());
        }

        // 之后关掉 connection 程序才会退出，否则会一直运行
        RabbitmqU.close(channel, connection);
    }
}
