package com.javase.thread.oldbase.juc.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author miclefengzss
 * 2021/12/7 上午10:32
 */
public class CacheData {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 要么多读要么一写，是针对多个线程而言的。同一个线程，可以同时持有读锁和写锁。
     */
    void processCachedData() {
        //最开始是读取
        rwl.readLock().lock();
        if (!cacheValid) {
            //发现缓存失效，那么就需要写入了，所以现在需要获取写锁。由于锁不支持升级，所以在获取写锁之前，必须首先释放读锁。
            rwl.readLock().unlock();
            //获取到写锁
            rwl.writeLock().lock();
            try {
                //这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他线程修改了数据。
                if (!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                //在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                rwl.readLock().lock();
            } finally {
                //释放了写锁，但是依然持有读锁，这样一来，就可以多个线程同时读取了，提高了整体效率。
                rwl.writeLock().unlock();
            }
        }

        try {
            System.out.println(data);
        } finally {
            //最后释放读锁
            rwl.readLock().unlock();
        }
    }
}
