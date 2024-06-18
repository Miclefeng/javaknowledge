package com.algorithm.system.code06_heap;

import java.util.*;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/16 17:14
 */
public class HeapGreater02 {

    public static class HeapGreater<T> {

        public int size;

        public List<T> heap;

        public Map<T, Integer> indexMap;

        public Comparator<? super T> comp;

        public HeapGreater(Comparator<? super T> comparator) {
            this.size = 0;
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.comp = comparator;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public boolean contains(T v) {
            return indexMap.containsKey(v);
        }

        public void add(T v) {
            heap.add(v);
            indexMap.put(v, size);
            heapInsert(size++);
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            return heap.get(0);
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            T ans = heap.get(0);
            swap(0, size - 1);
            indexMap.remove(ans);
            heap.remove(--size);
            heapify(0);
            return ans;
        }

        public void remove(T v) {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            T replace = heap.get(size - 1);
            int index = indexMap.get(v);
            heap.remove(--size);
            indexMap.remove(v);
            if (!v.equals(replace)) {
                heap.set(index, replace);
                indexMap.put(replace, index);
                ressign(index);
            }
        }

        public List<T> getAllElements() {
            return new ArrayList<>(heap);
        }

        public void ressign(int index) {
            heapInsert(index);
            heapify(index);
        }

        /**
         * 当前节点上浮，一直往上找，直到不大于父节点或者已到头结点为止
         *
         * @param index
         */
        public void heapInsert(int index) {
            // (index - 1) / 2 = 头结点位置
            while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 当前节点下沉，和左右孩子中较大的比较，如果大，和较大的孩子交换位置，继续下沉
         *
         * @param index
         */
        public void heapify(int index) {
            int left = (index << 1) + 1;
            while (left < size) {
                int lagest = left + 1 < size && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                lagest = comp.compare(heap.get(index), heap.get(lagest)) < 0 ? index : lagest;
                if (lagest == index) {
                    break;
                }
                swap(lagest, index);
                index = lagest;
                left = (index << 1) + 1;
            }
        }

        public void swap(int a, int b) {
            T o1 = heap.get(a);
            T o2 = heap.get(b);
            heap.set(a, o2);
            heap.set(b, o1);
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
