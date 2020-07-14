package rabbitmq.api.exchange.qos;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private final Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        System.out.println("consumerTag: " + consumerTag);
        System.out.println("envelope: " + envelope);
        System.out.println("body: " + (new String(body)));

        this.channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
