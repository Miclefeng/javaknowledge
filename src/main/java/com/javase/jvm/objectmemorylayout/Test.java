package com.javase.jvm.objectmemorylayout;

import com.javase.jvm.Worker;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/30 09:49
 */
public class Test {

    public static void main(String[] args) {
        Worker worker = new Worker();

        System.out.println(worker.hashCode());

        System.out.println(ClassLayout.parseInstance(worker).toPrintable());
    }
}
