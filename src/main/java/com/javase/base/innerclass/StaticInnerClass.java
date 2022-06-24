package com.javase.base.innerclass;

/**
 * 使用内部类实现类似方法多返回值
 */
public class StaticInnerClass {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < 20; i++) {
            d[i] = 100 * Math.random();
        }

        ArrayAlg.Pair pair = ArrayAlg.minmax(d);
        System.out.println("min = " + pair.getFirst());
        System.out.println("max = " + pair.getSecond());
    }
}

class ArrayAlg {

    public static class Pair {

        private double first;
        private double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    public static Pair minmax(double[] values) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }

        return new Pair(min, max);
    }
}
