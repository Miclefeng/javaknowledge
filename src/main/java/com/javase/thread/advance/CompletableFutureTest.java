package com.javase.thread.advance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/27 17:55
 */
public class CompletableFutureTest {

    public static final boolean flag = true;

    public static void main(String[] args) {
        long start = System.currentTimeMillis() / 1000;

        CompletableFuture<Map<String, String>> a = CompletableFuture.supplyAsync(() -> {
            Map<String, String> map = new HashMap<>();
            map.put("a", "A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return map;
        });

        CompletableFuture<List<String>> b = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("B");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return list;
        });

//        CompletableFuture.allOf(a, b).getNow(null);
        a.join();
        b.join();
        long end = System.currentTimeMillis() / 1000;
        System.out.println(end - start);
    }
}
