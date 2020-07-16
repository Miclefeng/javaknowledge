package redis.api;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        public String id;
        public T message;
    }

    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
    final private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();

    final private Jedis jedis;
    final private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();
        task.message = msg;
        String s = JSON.toJSONString(task); // fastjson 序列号
        jedis.zadd(queueKey, System.currentTimeMillis() + 500, s);
    }

    public void loop() {
        while (!Thread.interrupted()) {
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
                    continue;
                } catch (InterruptedException e) {
                    break;
                }
            }

            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) {  // 抢到删除，类似抢到锁
                TaskItem<T> task = JSON.parseObject(s, TaskType); // fastjson 反序列化
                this.handleMsg(task.message);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

    public static void main(String args[]) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("zss123456");

        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "delay-queue");

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.delay("codeHole - " + i);
            }
        });

        Thread consumer = new Thread(queue::loop); // queue::loop <==> () -> queue.loop()

        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException ignored) {
        }
    }
}
