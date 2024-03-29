package com.algorithm.base.bitmap;

import java.util.HashSet;

/**
 * @author miclefengzss
 * 2022/2/25 上午9:57
 */
public class BitMap {

    private long[] bits;

    public BitMap(int max) {
        bits = new long[(max + 64) >> 6];
    }

    /**
     * >> 6      等同于   除以64
     * val & 63  等同于   % 64
     *
     * @param val
     */
    public void add(int val) {
        bits[val >> 6] |= (1L << (val & 63));
    }

    public void delete(int val) {
        bits[val >> 6] &= ~(1L << (val & 63));
    }

    public boolean contains(int max) {
        return (bits[max >> 6] & (1L << (max & 63))) != 0;
    }

    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 1000000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
}
