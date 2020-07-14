package rabbitmq.api.exchange.confirmListener;

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

        // exchange 和 queue 声明和绑定
        String exchangeName = "test_confirm_exchange";
        String exchangeType = "topic";
        String routingKey = "confirm.#";
        String queueName = "test_confirm_queue";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 自定义 consumer
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope : " + envelope);
                String msg = new String(body);
                System.out.println("收到消息: " + msg);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 进行消费
        channel.basicConsume(queueName, false, consumer);
    }
}
