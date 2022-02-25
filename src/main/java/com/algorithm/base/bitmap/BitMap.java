package com.algorithm.base.bitmap;

/**
 * @author miclefengzss
 * 2022/2/25 上午9:57
 */
public class BitMap {

    private long[] bits;

    public BitMap(int max) {
        bits = new long[(max + 64) >> 6];
    }

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
        int max = 200;
        int val = 100;
        BitMap bitMap = new BitMap(max);
        bitMap.add(val);
        System.out.println(bitMap.contains(val));
        bitMap.delete(val);
        System.out.println(bitMap.contains(val));
    }
}
