package com.algorithm.system.code06_heap;

import java.util.*;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/18 12:48
 */
public class HeapGreater03 {

    public static class HeapGreater<T> {
        private final List<T> heap;
        private final Map<T, Integer> indexMap;
        private final Comparator<? super T> comp;
        private int size;

        public HeapGreater(Comparator<? super T> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.comp = comparator;
            this.size = 0;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int getSize() {
            return this.size;
        }

        public boolean contains(T v) {
            return indexMap.containsKey(v);
        }

        public void add(T v) {
            heap.add(v);
            indexMap.put(v, size);
            heapInsert(size++);
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            T ans = heap.get(0);
            swap(0, size - 1);
            heap.remove(--size);
            indexMap.remove(ans);
            heapify(0);
            return ans;
        }

        public void remove(T v) {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            if (!indexMap.containsKey(v)) {
                throw new RuntimeException("element not exist");
            }
            T replace = heap.get(size - 1);
            Integer index = indexMap.get(v);
            heap.remove(--size);
            indexMap.remove(v);
            if (!replace.equals(v)) {
                heap.set(index, replace);
                indexMap.put(replace, index);
                ressign(index);
            }
        }

        private void heapInsert(int index) {
            while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = (index << 1) + 1;
            while (left < size) {
                int best = left + 1 < size && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1: left;
                best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                if (best == index) {
                    break;
                }
                swap(best, index);
                index = best;
                left = (index << 1) + 1;
            }
        }

        private void ressign(int index) {
            heapInsert(index);
            heapify(index);
        }

        private void swap(int a, int b) {
            T o1 = heap.get(a);
            T o2 = heap.get(b);
            heap.set(b, o1);
            heap.set(a, o2);
            indexMap.put(o1, b);
            indexMap.put(o2, a);
        }
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 100000;
        boolean flag = true;
        System.out.println("begin.");
        for (int i = 0; i < testTimes && flag; i++) {
            HeapGreater<Integer> my = new HeapGreater<>(Comparator.comparingInt(o -> o));
            PriorityQueue<Integer> test = new PriorityQueue<>();

            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops 01!");
                    flag = false;
                    break;
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.add(curValue);
                    test.add(curValue);
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.add(curValue);
                        test.add(curValue);
                    } else {
                        int a = my.poll();
                        int b = test.poll();
                        if (a != b) {
                            System.out.println("Oops 04!");
                            System.out.println("a = " + a + ", b = " + b);
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

        HeapGreater<Integer> my = new HeapGreater<>(Comparator.comparingInt(o -> o));
        my.add(1);
        my.add(2);
        my.add(3);
        my.add(4);
        my.add(5);
        my.add(6);
        my.remove(4);
        for (int i = 0; i< 5; i++) {
            System.out.println(my.poll());
        }
    }
}
