package rabbitmq.api.exchange.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.AMQBasicProperties;

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

        String exchangeName = "test_ttl_exchange";
        String routingKey = "ttl.save";
        String msg = "Hello RabbitMQ to TTL Message...";

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("100000")
                .build();

        for (int i=0; i<5; i++) {
            channel.basicPublish(exchangeName, routingKey, true, properties, msg.getBytes());
        }
    }
}
