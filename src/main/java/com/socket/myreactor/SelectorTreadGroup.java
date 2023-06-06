package com.socket.myreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/4/17 10:05
 */
public class SelectorTreadGroup {

    /**
     * 持有执行的线程列表
     */
    private List<SelectorThread> selectorThreadList = new ArrayList<>();

    /**
     * 保证并发安全的在线程列表取出一个线程
     */
    private AtomicInteger idx = new AtomicInteger(0);

    /**
     * 设置工作组
     */
    private SelectorTreadGroup worker = this;

    /**
     * 初始化线程，并放入列表
     *
     * @param num
     */
    public SelectorTreadGroup(int num) {
        for (int i = 0; i < num; i++) {
            selectorThreadList.add(new SelectorThread(this));
            new Thread(selectorThreadList.get(i)).start();
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
            // 选取线程处理 accept 事件
            nextSelector(serverSocketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void nextSelector(Channel channel) {
        try {
            SelectorThread st;
            if (channel instanceof ServerSocketChannel) {
                // 处理 accept 事件从 boss 组中的线程列表取一个线程去处理后续任务
                st = nextThread(this);
            } else {
                // 处理 read 事件从 worker 组中的线程列表取一个线程去处理后续任务
                st = nextThread(this.worker == null ? this : worker);
            }
            // 把对应的channel放入到执行selector线程的阻塞队列里面
            st.linkedBlockingQueue.put(channel);
            // 唤醒需要执行的selector线程
            st.selector.wakeup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private SelectorThread nextThread(SelectorTreadGroup selectorTreadGroup) {
        int i = idx.incrementAndGet() % selectorTreadGroup.selectorThreadList.size();
        return selectorTreadGroup.selectorThreadList.get(i);
    }

    public void setWorker(SelectorTreadGroup worker) {
        this.worker = worker;
    }
}
