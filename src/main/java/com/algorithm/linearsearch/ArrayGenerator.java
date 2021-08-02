package com.algorithm.linearsearch;

import java.util.ArrayList;

/**
 * @author miclefengzss
 * 2021/8/2 下午2:38
 */
public class ArrayGenerator {

    private ArrayGenerator() {
    }

    public static Integer[] generatorOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
