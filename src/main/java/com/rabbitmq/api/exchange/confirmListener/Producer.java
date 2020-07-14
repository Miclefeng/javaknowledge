package rabbitmq.api.exchange.confirmListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

        // 4、指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();
        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";
        String msg = "Hello RabbitMQ Confirm Message...";

        // 5、发送消息
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
        }

        // 6、添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                System.err.println("-------no ack!-----------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                System.err.println("-------ack!-----------");
            }
        });
    }
}
