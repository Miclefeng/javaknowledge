package rabbitmq.api.exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // 4、声明
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        String routingKey = "user.#";

        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 5、创建Consumer

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body, "UTF-8");
                System.out.println("收到消息：" + msg);
                // 消息 ack 回应
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 6、进行消费
        channel.basicConsume(queueName, false, consumer);
    }
}
