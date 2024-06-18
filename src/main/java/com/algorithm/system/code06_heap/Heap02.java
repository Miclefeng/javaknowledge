package com.algorithm.system.code06_heap;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/8 11:34
 */
public class Heap02 {

    public static class MyHeap {

        public int limit;
        public int size;
        public int[] heap;

        public MyHeap(int l) {
            this.heap = new int[l];
            this.limit = l;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int v) {
            if (size >= limit) {
                throw new RuntimeException("heap is full.");
            }
            heap[size] = v;
            heapInsert(heap, size++);
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            return heap[0];
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            int ans = heap[0];
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return ans;
        }

        // 从 index 位置，往上看，不断上浮
        // 停: 不比父节点大；已是头结点
        // 父节点索引计算： (index - 1) / 2
        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从index位置，往下看，不断的下沉
        // 停：较大的孩子都不再比index位置的数大；已经没孩子了
        // 左右孩子的索引计算，左：2 * index + 1，右：2 * index + 2; index 当前位置索引
        private void heapify(int[] heap, int index, int size) {
            int left = (index << 1) + 1;
            while (left < size) {
                int lagest = left + 1 < size && heap[left + 1] > heap[left] ? left + 1 : left;
                lagest = heap[lagest] > heap[index] ? lagest : index;
                if (lagest == index) {
                    break;
                }
                swap(heap, lagest, index);
                index = lagest;
                left = (index << 1) + 1;
            }
        }

        public void printArray() {
            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    public static class RightMaxHeap {
        public int limit;
        public int size;
        public int[] heap;

        public RightMaxHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int v) {
            if (size >= limit) {
                throw new RuntimeException("heap is full.");
            }
            heap[size++] = v;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty.");
            }
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (heap[maxIndex] < heap[i]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            swap(heap, maxIndex, --size);
            return ans;
        }

        public void printArray() {
            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    private static void swap(int[] heap, int a, int b) {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        boolean flag = true;
        System.out.println("begin.");
        for (int i = 0; i < testTimes && flag; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyHeap my = new MyHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops 01!");
                    flag = false;
                    break;
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops 02!");
                    flag = false;
                    break;
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops 03!");
                        flag = false;
                        break;
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        int a = my.pop();
                        int b = test.pop();
                        if (a != b) {
                            System.out.println("Oops 04!");
                            System.out.println("a = " + a + ", b = " + b);
                            flag = false;
                            my.printArray();
                            test.printArray();
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
