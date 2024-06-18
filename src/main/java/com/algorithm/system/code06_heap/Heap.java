package com.algorithm.system.code06_heap;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/30 15:47
 */
public class Heap {

    public static class MyMaxHeap {
        private int capacity;
        private int[] heap;
        private int size;

        public MyMaxHeap(int capacity) {
            this.heap = new int[capacity];
            this.capacity = capacity;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public void push(int v) throws Exception {
            if (isFull()) {
                throw new RuntimeException("heap is full.");
            }
            heap[size] = v;
            // 这里的 heapSize++ 会在方法执行完之后才进行++操作
            heapInsert(heap, size++);
        }

        public int peek() throws Exception {
            if (isEmpty()) {
                throw new RuntimeException("heap is null");
            }
            return heap[0];
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop() throws Exception {
            int ret = heap[0];
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return ret;
        }

        // 新加进来的数，现在停在了index位置，请依次往上移动，
        // 移动到0位置，或者替换不掉父节点，停！
        // 父节点索引获得公式：(index - 1) / 2 ; index 当前位置索引
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从index位置，往下看，不断的下沉
        // 停：较大的孩子都不再比index位置的数大；已经没孩子了
        // 左右孩子的索引计算，左：2 * index + 1，右：2 * index + 2; index 当前位置索引
        private void heapify(int[] arr, int index, int heapSize) {
            int left = (index << 1) + 1;
            while (left < heapSize) {
                int lagest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
                lagest = arr[lagest] > arr[index] ? lagest : index;
                if (lagest == index) {
                    break;
                }
                swap(arr, index, lagest);
                index = lagest;
                left = (index << 1) + 1;
            }
        }

        private void swap(int[] arr, int a, int b) {
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        public void printArray() {
            for (int i =0; i<size;i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    public static class RightMaxHeap {
        private int capacity;
        private int size;
        private int[] heap;

        public RightMaxHeap(int capacity) {
            this.heap = new int[capacity];
            this.capacity = capacity;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public void push(int v) {
            if (size == capacity) {
                throw new RuntimeException("heap is full");
            }
            heap[size++] = v;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (heap[maxIndex] < heap[i]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--size];
            return ans;
        }

        public void printArray() {
            for (int i =0; i<size;i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        boolean flag = true;
        System.out.println("begin.");
        for (int i = 0; i < testTimes && flag; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
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
                            my.printArray();
                            test.printArray();
                            flag = false;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
