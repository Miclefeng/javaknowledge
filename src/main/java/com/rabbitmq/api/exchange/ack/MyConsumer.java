package rabbitmq.api.exchange.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private final Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        super.handleDelivery(consumerTag, envelope, properties, body);
        System.out.println("====================================");
        System.out.println("收到消息: " + (new String(body)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        if ((Integer) properties.getHeaders().get("num") == 0) {
            this.channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
            this.channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
