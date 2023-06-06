package com.socket.myreactor;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/4/17 10:05
 */
public class SelectorThread implements Runnable {

    public Selector selector;

    public LinkedBlockingQueue<Channel> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private SelectorTreadGroup selectorTreadGroup;

    public SelectorThread(SelectorTreadGroup selectorTreadGroup) {
        this.selectorTreadGroup = selectorTreadGroup;
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                // 1, select()
                int select = selector.select();

                // 2, 处理 selectionKey
                if (select > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }

                // 3, wakeup之后的要处理的task:  listen 的 accept  和  client 的 read
                if (!linkedBlockingQueue.isEmpty()) {
                    Channel channel = linkedBlockingQueue.take();
                    if (channel instanceof ServerSocketChannel) {
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        System.out.println(Thread.currentThread().getName() + " register listen.");
                        server.register(selector, SelectionKey.OP_ACCEPT);
                    } else if (channel instanceof SocketChannel) {
                        SocketChannel client = (SocketChannel) channel;
                        System.out.println(Thread.currentThread().getName() + " register client: " + client.getRemoteAddress());
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        client.register(selector, SelectionKey.OP_READ, buffer);
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + "   acceptHandler......");
        try {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            // 当前线程只做 accept 处理，将接收读取的事件(SelectionKey.OP_READ)注册到其他 selector
            // 此时 selectorTreadGroup 为 boss 的 SelectorThreadGroup
            selectorTreadGroup.nextSelector(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName() + " read......");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();

        while (true) {
            try {
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
                    //客户端断开了
                    System.out.println("client: " + client.getRemoteAddress() + " closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
