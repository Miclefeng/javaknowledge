package rabbitmq.api.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // 声明
        String exchangeName = "test_fanout_exchange";
        String routingKey = "";
        String msg = "Hello RabbitMQ 4 Fanout Exchange Message ...";

        // 发送消息
        for (int i=0; i<5; i++) {
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }

        // 关闭channel和connection
        channel.close();
        connection.close();
    }
}
