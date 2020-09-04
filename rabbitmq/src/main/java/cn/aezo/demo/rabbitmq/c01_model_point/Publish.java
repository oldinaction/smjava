package cn.aezo.demo.rabbitmq.c01_model_point;

import cn.aezo.demo.rabbitmq.util.RabbitmqU;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 点对点发布消息案例
 *
 * @author smalle
 * @date 2020-08-29 16:12
 */
public class Publish {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqU.getConnection();

        // 开启一个会话
        Channel channel = connection.createChannel();

        /*
        * 声明一个消息队列，如果无此消息队列会自动创建。参数如下：
        * 1.queue: 消息队列名称
        * 2.durable: 是否持久化。true时，管理后台的Queues-Features会增加"D"标识。false不进行持久化，当MQ服务端重启后，消息队列会丢失(包括队列中的数据)
        * 3.exclusive: 是否独占。true独占，表示只能这个channel(会话)访问这个消息队列
        * 4.autoDelete: 是否自动删除。true自动删除，当消息消费完成后，且消费者断开连接，则删除此队列
        * 5.arguments: 额外参数
        */
        channel.queueDeclare("hello", false, false, false, null);

        /*
        * 发布消息，参数如下：
        * 1.exchange: 使用的交换机名。此时案例展示点对点，留空
        * 2.routingKey: 路由键名。此时为点对点传输，填写队列名
        * 3.props: 传递消息的额外配置。AMQP.BasicProperties
        * 4.body: 消息体
        */
        channel.basicPublish("", "hello", null, "这是一条消息".getBytes());

        // 之后关掉 connection 程序才会退出，否则会一直运行
        RabbitmqU.close(channel, connection);
    }
}
