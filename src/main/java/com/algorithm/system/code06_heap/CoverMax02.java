package com.algorithm.system.code06_heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/14 13:01
 */
public class CoverMax02 {

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    /**
     * 按照线段头排序
     */
    public static class StartCompertor implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    /**
     * 以 .5 为分割，查看穿过 .5 的线段
     *
     * @param lines
     * @return
     */
    public static int coverMaxLow(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 找到最大和最小的数
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(lines[i][0], min);
            max = Math.max(lines[i][1], max);
        }

        int ans = 0;
        // 从最小的 .5 到最大的 .5 ，查看穿过去的线段
        for (double i = min + 0.5; i < max; i += 1.0) {
            int cur = 0;
            for (int[] line : lines) {
                cur += line[0] < i && line[1] > i ? 1 : 0;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    /**
     * 使用堆，堆中保留线段尾部，小于线段起点的会依次弹出。
     *
     * @param arr
     * @return
     */
    public static int coverMaxHeap(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }

        // 按照线段开始位置排序
        Arrays.sort(lines, new StartCompertor());

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把 <= cur.start 东西都弹出
            // 后续的起点如果大于之前的终点，说明两个线段肯定不重合，一直弹到有重合的位置
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            ans = Math.max(ans, heap.size());
        }

        return ans;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = coverMaxHeap(lines);
            int ans2 = coverMaxLow(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }
}
