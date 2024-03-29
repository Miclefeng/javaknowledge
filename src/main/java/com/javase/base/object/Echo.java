package com.javase.base.object;

import java.math.BigDecimal;

/**
 * @author miclefengzss
 * @create 2021-02-24 下午2:55
 */

public class Echo {

    int count = 0;

    void hello() {
        System.out.println("helloooo... ");
    }
}

class EchoTestDriver {

    public static void main(String[] args) {
        Echo e1 = new Echo();
        Echo e2 = new Echo();
        int x = 0;
        while (x < 4) {
            e1.hello();
            e1.count = e1.count + 1;
            if (x == 3) {
                e2.count = e2.count + 1;
            }
            if (x > 0) {
                e2.count = e1.count + e2.count;
            }
            x = x + 1;
        }
        System.out.println(e2.count);

        System.out.println(new BigDecimal("0"));
    }
}
