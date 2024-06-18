package com.cache.redis.api;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/25 14:28
 */
public class RedissonTest {

    public static class R implements Runnable {

        private final RedissonClient redissonClient;

        public R(RedissonClient redissonClient) {
            this.redissonClient = redissonClient;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            RLock lock = redissonClient.getLock("REDISSON_TEST_LOCK");
            try {
                boolean b = lock.tryLock(5, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + ": Get Lock Success.");
                    long l = System.currentTimeMillis() / 1000;
                    System.out.println(name + ": Time: " + l);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(name + ": Release Lock");
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String address = "redis://127.0.0.1:6379";
        singleServerConfig.setAddress(address);
        singleServerConfig.setPassword("zss25456585");
        singleServerConfig.setDatabase(0);
        singleServerConfig.setTimeout(3000);
        RedissonClient redissonClient = Redisson.create(config);
        Thread[] list = new Thread[3];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Thread(new R(redissonClient));
        }

        for (int i = 0; i < list.length; i++) {
            list[i].start();
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
