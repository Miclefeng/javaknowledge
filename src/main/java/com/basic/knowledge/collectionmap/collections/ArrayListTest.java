package com.basic.knowledge.collectionmap.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author miclefengzss
 * @create 2020-07-24 4:51 下午
 */

public class ArrayListTest {

    public static void main(String[] args) {
        /**
         *      Collection: 单列集合，用来存储一个一个的对象
         *          List: 有序的，可重复数据   --> 动态数组
         *              ArrayList / LinkedList / Vector
         *          Set:  无序的，不可重复的数据
         *              HashSet / LinkedHashSet / TreeSet
         */

        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(456);
        collection.add("milcefeng");
        collection.add(false);
        collection.add(new String[]{"123", "3456"});

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.equals(123)) {
                // remove() 要在 next() 之后调用
                iterator.remove();
            }
        }

        // 重新生成迭代器
        iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("+++++++++++++++++++++++++++++++");
        for (Object o : collection
             ) {
            System.out.println(o);
        }
    }
}
