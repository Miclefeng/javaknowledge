package com.algorithm.system.code06_heap;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/22 12:44
 */
public class Heap03 {

    public static class MyHeap {
        private int limit;
        private int size;
        private int[] heap;

        public MyHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.size = 0;
        }

        public int getSize() {
            return this.size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }


    }

    public static void main(String[] args) {

    }
}
