package com.javase.base.lambda;

import java.util.function.Supplier;

/**
 * @author miclefengzss
 * 2022/6/22 下午8:20
 */
public class SupplierLambda {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 9, 6, 5};

        int max = getMax(() -> {
            int temp = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > temp) {
                    temp = arr[i];
                }
            }
            return temp;
        });
        System.out.println(max);
    }

    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
