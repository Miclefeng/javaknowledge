package com.middleware.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/5/26 10:36
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        // zookeeper 有session的概念，没有连接池的概念
        // watch: 观察，回调
        // watch 的注册只发生在 读类型调用, get / exists
        // 第一类: new zk 的时候，传入的watch，这个 watch, session 级别的,跟 path、node 没关系
        ZooKeeper zooKeeper = new ZooKeeper("172.16.18.11:2181,172.16.18.12:2181,172.16.18.13:2181,172.16.18.14:2181", 3000, new Watcher() {
            // watch 的回调方法
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();

                System.out.println("[new Event]: " + event);

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        System.out.println("connected.");
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                }

                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                    case DataWatchRemoved:
                        break;
                    case ChildWatchRemoved:
                        break;
                    case PersistentWatchRemoved:
                        break;
                }
            }
        });

        countDownLatch.await();

        ZooKeeper.States state = zooKeeper.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("ing......");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("end......");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        String pathName = zooKeeper.create("/foo", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Stat stat = new Stat();
        byte[] node = zooKeeper.getData("/foo", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("[getData Watch]: " + event.toString());
                try {
                  byte[] data2 =  zooKeeper.getData("/foo", this, stat);
                    System.out.println("changed after,the new data: " + new String(data2));
                } catch (KeeperException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, stat);

        System.out.println(new String(node));
        System.out.println("=========================");
        // 触发回调
        Stat stat1 = zooKeeper.setData("/foo", "new test".getBytes(), 0);
        // 默认不触发回调，因为 watch 为一次性监控，只会触发一次
        // 如果需要继续监控，需要在 watch 触发回调后，在将 watch 注册回去
        Stat stat2 = zooKeeper.setData("/foo", "new test 02".getBytes(), stat1.getVersion());
        System.out.println("--------------async start------------------");
        zooKeeper.getData("/foo", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("------------async call back----------------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        }, "async");
        System.out.println("---------------async end-------------------");
        Thread.sleep(10000);
    }
}
