package com.socket.mynetty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/7 20:58
 */
public class SelectorThreadGroup {

    private List<SelectorThread> list = new ArrayList<>();

    private AtomicInteger next = new AtomicInteger();

    private SelectorThreadGroup worker;

    public SelectorThreadGroup(int num) {
        for (int i = 0; i < num; i++) {
            list.add(new SelectorThread(this));
            new Thread(list.get(i)).start();
        }
    }

    /**
     * 绑定服务端监听，并选取boss组中的一个线程去处理 accept 事件
     *
     * @param port
     */
    public void bind(int port) {
        try {
            // 服务端 channel 打开
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定监听
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port));
            // 从boss组选取线程处理 accept 事件
            nextSelector(serverSocketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorker(SelectorThreadGroup worker) {
        this.worker = worker;
    }

    public void nextSelector(Channel channel) {
        SelectorThreadGroup stg;
        if (channel instanceof SocketChannel) {
            stg = worker == null ? this : worker;
        } else {
            stg = this;
        }
        try {
            SelectorThread selectorThread = getSelectorThread(stg);
            selectorThread.queue.put(channel);
            selectorThread.selector.wakeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private SelectorThread getSelectorThread(SelectorThreadGroup stg) {
        return stg.list.get(next.getAndIncrement() % stg.list.size());
    }
}
