package com.algorithm.linearsearch;

/**
 * @author miclefengzss
 * 2021/7/30 下午4:00
 */
public class LinearSearch {

    private LinearSearch() {
    }

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] data = {24, 18, 38, 93, 49, 66, 51, 78, 80};
        final int search = LinearSearch.search(data, 49);
        System.out.println(search);
        final int search1 = LinearSearch.search(data, 200);
        System.out.println(search1);

        Student[] students = {new Student("micle"), new Student("wdj"), new Student("zss"), new Student("wxm")};
        final int wdj = LinearSearch.search(students, new Student("wdj"));
        System.out.println(wdj);

        int[] dataSize = {1000000, 10000000};
        for (int i = 0; i < dataSize.length; i++) {
            final Integer[] orderedArray = ArrayGenerator.generatorOrderedArray(dataSize[i]);
            long startTime = System.nanoTime();
            for (int j = 0; j < 100; j++) {
                LinearSearch.search(orderedArray, dataSize[i]);
            }
            long endTime = System.nanoTime();
            double useTime = (endTime - startTime) / 1000000000.0;
            System.out.println("n = " + dataSize[i] + ", 100 runs, time = " + useTime + " s");
        }
    }
}
