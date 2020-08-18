package com.basic.knowledge.collectionmap.maps;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author miclefengzss
 * @create 2020-07-24 4:52 下午
 */

public class MapTest {

    @Test
    public void test01() {
        /**
         *      Map: 双列集合，用来存储(Key-Value)形式的对象
         *          HashMap： 线程不安全，效率高，可以存储为 null 的 key 和 value
         *              HashMap
         *                  DEFAULT_INITIAL_CAPACITY默认容量 16，DEFAULT_LOAD_FACTOR加载因子是 0.75 ， threshold = 容量*加载因子： 16 * 0.75 = 12
         *                  TREEIFY_THRESHOLD: bucket 中链表长度> 8 ,转换为红黑树
         *                  MIN_TREEIFY_CAPACITY： bucket 中的node 被转化为红黑树是，最小的hash容量为64
         *
         *              数组+链表(jdk7<) ： Entry[]，超出临界值且添加的位置为null，扩容为原来的2倍
         *              数组+链表+红黑树(jdk8<=) ：Node[] , 当数组中某一个索引位置上的链表长度 > 8 并且 数组的长度 > 64 时，
         *                  使用红黑树存储索引位置上的数据
         *
         *              LinkedHashMap：在HashMap的基础上，添加了双向链表，对于频繁遍历的数据效率高，按照添加顺序遍历
         *                   Entry[] 结构，里面包含 增加 Entry[] before, Entry[] after , Node[]
         *          TreeMap：保证按照添加的 key-value 进行排序，可以对 key 进行自然排序或定制排序，底层使用红黑树实现
         *                  要求key是同一类的对象
         *
         *          HashTable：线程安全，效率低，不能存储为 null 的 key 和 value
         *              Properties：常用来处理配置文件，key 和 value 都是 String
         *
         *        常用方法：
         *        添加/修改:  put(k, v)
         *        删除:  remove(k)
         *        查询:  get(k)
         *        长度:  size()
         *        遍历:  keySet() / values() / entrySet()
         */

        String[] arr = new String[16];
        System.out.println(arr.length);
        System.out.println("===============");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("AA", 123);
        hashMap.put("BB", 456);
        hashMap.put("CC", 789);
        hashMap.put(678, 1234);

        // 遍历所有的key
        Set<Object> keySet = hashMap.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.println(o + " => " + hashMap.get(o));
        }

        System.out.println("==============");
        // 遍历所有的 value
        Collection<Object> values = hashMap.values();
        for (Object o1 : values) {
            System.out.println(o1);
        }

        System.out.println("==============");
        // 遍历所有key-value
        Set<Map.Entry<Object, Object>> entrySet = hashMap.entrySet();
        Iterator<Map.Entry<Object, Object>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Object, Object> objectEntry = entryIterator.next();
            System.out.println(objectEntry.getKey() + " : " + objectEntry.getValue());
        }
    }
}
