package com.javase.base.collectionmap.collections;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author miclefengzss
 * @create 2020-07-24 10:02 下午
 */

public class SetTest {

    public static void main(String[] args) {
        /**
         *      Collection: 单列集合，用来存储一个一个的对象
         *          List: 有序的，可重复数据   --> 动态数组
         *              ArrayList : 线程不安全, Object[]
         *              LinkedList : 双向链表
         *              Vector : 线程安全, Object[]
         *          Set:  无序的，不可重复的数据
         *              HashSet : 线程不安全, 可以存储 null
         *                  LinkedHashSet :  HashSet 的子类，可以按照添加的顺序遍历
         *                      存储的元素都是通过双向链表链接，遍历的时候效率会高一些
         *                  1、无序性： 因为生成的 hash 值不同所有，顺序不同
         *                  2、不可重复性： 通过对象的 hashCode 和 equals 方法来判断是否重复，先判断对象的 hashCode
         *
         *              TreeSet : SortedSet 接口实现类，确保元素处于排序状态(自然排序、定制排序)，底层使用红黑树结构存储数据
         *                  添加的元素都是同一对象的数据
         *                  通过实现 Comparable 接口的 compareTo() 实现排序，比较标准也是通过 compareTo() 返回的结果为准
         *
         */

        TreeSet<Object> treeSet = new TreeSet<>();
        // 只能添加同一个类的对象, 默认从小到大排序
        treeSet.add(123);
        treeSet.add(234);
        treeSet.add(345);
        treeSet.add(456);

        Iterator<Object> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("==========================");
        TreeSet<Object> treeSet1 = new TreeSet<>();
        treeSet1.add(new User("Jack", 24));
        treeSet1.add(new User("Jack", 36));
        treeSet1.add(new User("Mike", 34));
        treeSet1.add(new User("Tom", 24));
        treeSet1.add(new User("Jerry", 34));
        Iterator<Object> iterator1 = treeSet1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println("==========================");
        TreeSet<Object> treeSet2 = new TreeSet<>((o1, o2) -> {
            if (o1 instanceof User && o2 instanceof User) {
                User u1 = (User) o1;
                User u2 = (User) o2;
                int compare = Integer.compare(u1.getAge(), u2.getAge());
                if (compare == 0) {
                    return u1.getName() != null && u2.getName() != null ? u1.getName().compareTo(u2.getName()) : u1.getName().compareTo(null);
                } else {
                    return compare;
                }
            } else {
                throw new RuntimeException("输入的类型不匹配");
            }
        });
        treeSet2.add(new User("Jack", 24));
        treeSet2.add(new User("Jack", 36));
        treeSet2.add(new User("Mike", 34));
        treeSet2.add(new User("Tom", 24));
        treeSet2.add(new User("Jerry", 34));
        Iterator<Object> iterator2 = treeSet2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}


class User implements Comparable {

    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (age != user.age) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            // - 表示从大到小排列
            int compare = -this.name.compareTo(user.getName());
            if (compare == 0) {
                return Integer.compare(this.age, user.getAge());
            } else {
                return compare;
            }
        } else {
            throw new RuntimeException("输入的类型不匹配");
        }
    }
}