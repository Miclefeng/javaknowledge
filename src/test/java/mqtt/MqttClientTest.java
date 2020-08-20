package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;


/**
 * @author miclefengzss
 * @create 2020-08-20 3:06 下午
 */

public class MqttClientTest {

    String subTopic = "testtopic/#";
    String pubTopic = "testtopic/1";
    String content = "Hello World";
    int qos = 2;
    String broker = "tcp://127.0.0.1:1883";
    String clientId = "emqx_test";
    MemoryPersistence persistence = new MemoryPersistence(); // 内存持久化连接
    String userName = "admin";
    String password = "password";

    @Test
    public void Client() throws MqttException {
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        // MQTT 连接选项
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);

        // 设置回调
        mqttClient.setCallback(new OnMessageCallback());

        // 建立连接
        System.out.println("Connecting to broker: " + broker);
        mqttClient.connect(options);

        System.out.println("Connected");
        System.out.println("Publishing message: " + content);

        // 订阅
        mqttClient.subscribe(subTopic);

        mqttClient.publish(pubTopic, content.getBytes(), qos, false);
        System.out.println("Message published");

        mqttClient.disconnect();
        System.out.println("Disconnected");
        mqttClient.close();
        System.exit(0);
    }
}

class OnMessageCallback implements MqttCallback {
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + message.getQos());
        System.out.println("接收消息内容:" + new String(message.getPayload()));
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
