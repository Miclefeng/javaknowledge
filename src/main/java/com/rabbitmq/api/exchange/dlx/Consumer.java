//package rabbitmq.api.exchange.dlx;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeoutException;
//
//public class Consumer {
//
//    public static void main(String[] args) throws IOException, TimeoutException {
//
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setAutomaticRecoveryEnabled(true);
//        connectionFactory.setNetworkRecoveryInterval(3000);
//
//        Connection connection = connectionFactory.newConnection();
//
//        Channel channel = connection.createChannel();
//
//        String exchangeName = "test_dlx_exchange";
//        String exchangeType = "topic";
//        String routingKey = "dlx.#";
//        String queueName = "test_dlx_exchange";
//        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);
//
//        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-dead-letter-exchange", "dlx.exchange");
//        // 死信队列 arguments 必须在队列上设置
//        channel.queueDeclare(queueName, true, false, false, arguments);
//        channel.queueBind(queueName, exchangeName, routingKey);
//
//        // 死信队列的 exchange 和 queue 声明和绑定
//        channel.exchangeDeclare("dlx.exchange", exchangeType, true, false, null);
//        channel.queueDeclare("dlx.queue", true, false, false, null);
//        channel.queueBind("dlx.queue", "dlx.exchange", "#");
//
//        channel.basicConsume(queueName, false, new rabbitmq.api.exchange.consumer.MyConsumer(channel));
//    }
//}
