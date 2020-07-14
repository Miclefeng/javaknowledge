package rabbitmq.api.exchange.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        String exchangeName = "test_ack_exchange";
        String routingKey = "ack.save";
        String msg = "Hello RabbitMQ To Ack Message... ";

        Map<String, Object> headers = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            headers.put("num", i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .headers(headers)
                    .build();

            channel.basicPublish(exchangeName, routingKey, true, properties, (msg + i).getBytes());
        }
    }
}
