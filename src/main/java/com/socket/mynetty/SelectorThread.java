package com.socket.mynetty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/7 20:58
 */
public class SelectorThread implements Runnable {

    Selector selector;

    private SelectorThreadGroup stg;

    public LinkedBlockingQueue<Channel> queue = new LinkedBlockingQueue<>();

    public SelectorThread(SelectorThreadGroup stg) {
        try {
            this.stg = stg;
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSelectorThreadGroup(SelectorThreadGroup stg) {
        this.stg = stg;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                if (this.selector.select() > 0) {
                    Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readableHandler(key);
                        }
                    }
                }

                if (!queue.isEmpty()) {
                    Channel channel = queue.take();
                    if (channel instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName() + " register listen.");
                    } else if (channel instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + " register read: " + client.getRemoteAddress());
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        try {
            System.out.println(Thread.currentThread().getName() + " : accept.");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            // 当前线程只做 accept 处理，将接收读取的事件(SelectionKey.OP_READ)放到其他线程的selector上
            // 此时 selectorTreadGroup 为 boss 的 SelectorThreadGroup
            stg.nextSelector(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readableHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " : read.");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        try {
            for (; ; ) {
                int num = client.read(buffer);
                if (num > 0) {
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0) {
                    break;
                } else {
                    // 客户端断开了
                    key.cancel();
                    client.close();
                    System.out.println("client: " + client.getRemoteAddress() + " closed......");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
