package rabbitmq.api.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private final static String QUEUE_NAME = "test001";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 1、创建ConnectionFactory，并配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2、通过ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建channle
        Channel channel = connection.createChannel();

        // 4、指定一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 5、创建Consumer
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body, "UTF-8");
                System.out.println("Received Message '" + msg + "'");
                // 消息 ack 回应
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
