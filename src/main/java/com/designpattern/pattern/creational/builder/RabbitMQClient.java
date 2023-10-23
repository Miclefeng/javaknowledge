package com.designpattern.pattern.creational.builder;

/**
 * 建造者模式
 * 1.目标类的构造方法要传入一个Builder对象
 * 2.builder类位于目标类的内部,并且使用static修饰
 * 3.builder类对象提供内置各种set方法,注意: set方法的返回值是builder本身
 * 4.builder类提供一个build() 方法,实现目标对象的创建
 *
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/22 22:33
 */
public class RabbitMQClient {

    // 构造方法私有化
    private RabbitMQClient(Builder builder) {

    }

    // 保证不可变对象的属性密闭性
    public static class Builder {
        private String host = "127.0.0.1";
        private int port = 5672;
        private int mode = 1;
        private String exchange;
        private String queue;
        private boolean isDurable = true;
        private int connectionTimeout = 1000;

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder setExchange(String exchange) {
            this.exchange = exchange;
            return this;
        }

        public Builder setQueue(String queue) {
            this.queue = queue;
            return this;
        }

        public Builder setDurable(boolean durable) {
            isDurable = durable;
            return this;
        }

        public Builder setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public RabbitMQClient build() throws Exception {
            if (1 == mode) {
                // 工作队列模式不需要交换机，但需要队列
                if (exchange != null) {
                    throw new Exception("工作队列模式不需要交换机");
                }
                if (queue == null || "".equals(queue.trim())) {
                    throw new Exception("工作队列模式需要设置队列");
                }
                if (!isDurable) {
                    throw new Exception("工作队列模式必须设置持久化");
                }
            } else if (2 == mode) {
                // 路由模式必须设置交换机，但不能设计队列
                if (queue != null) {
                    throw new Exception("路由模式不需要队列");
                }
                if (exchange == null || "".equals(exchange.trim())) {
                    throw new Exception("路由模式需要设置交换机");
                }
            }

            return new RabbitMQClient(this);
        }
    }

    public void sendMessage() {
        System.out.println("send message...");
    }
}
