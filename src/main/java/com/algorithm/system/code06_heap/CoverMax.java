package com.algorithm.system.code06_heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/1 11:59
 */
public class CoverMax {

    public static class Line {
        public int s;
        public int e;

        public Line(int start, int end) {
            this.s = start;
            this.e = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.s - o2.s;
        }
    }

    public static int coverMaxLow(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(lines[i][0], min);
            max = Math.max(lines[i][1], max);
        }

        int cover = 0;
        for (double i = min + 0.5; i < max; i += 1) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && lines[j][1] > i) {
                    cur++;
                }
            }
            cover = Math.max(cur, cover);
        }
        return cover;
    }

    public static int coverMaxHeap(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int cover = 0;

        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把 <= cur.start 东西都弹出
            // 后续的起点如果大于之前的终点，说明两个线段肯定不重合，一直弹到有重合的位置
            while (!queue.isEmpty() && queue.peek() <= lines[i].s) {
                queue.poll();
            }
            queue.add(lines[i].e);
            cover = Math.max(cover, queue.size());
        }

        return cover;
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
