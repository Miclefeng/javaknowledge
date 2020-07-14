package rabbitmq.api.exchange.fanout;

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

        // 声明
        String exchangeName = "test_fanout_exchange";
        String exchangeType = "fanout";
        String queueName = "test_fanout_queue";
        String routingKey = "";

        // 绑定exchange
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 绑定队列
        channel.queueDeclare(queueName, true, false, false, null);
        // queue绑定exchange
        channel.queueBind(queueName, exchangeName, routingKey);

        // 定义消费者
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

        // 进行消费
        channel.basicConsume(queueName, false, consumer);
    }
}
