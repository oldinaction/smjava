package cn.aezo.demo.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author smalle
 * @date 2020-08-29 16:00
 */
public class RabbitmqU {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        // factory.setHost("127.0.0.1");
        factory.setHost("192.168.6.135");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test"); // 需要提前创建好此虚拟主机

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Channel channel, Connection connection) {
        try {
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
