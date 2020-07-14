package rabbitmq.api.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1、创建ConnectionFactory，并配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2、通过ConnectionFactory 创建 Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection 创建 Channel
        Channel channel = connection.createChannel();

        // 4、发送数据
        String queueName = "test001";
        String msg = "Hello RabbitMQ!";

        for (int i=0; i<5; i++) {
            channel.basicPublish("", queueName, true, null, msg.getBytes());
        }

        // 5、关闭channel和connection
        channel.close();
        connection.close();
    }
}
