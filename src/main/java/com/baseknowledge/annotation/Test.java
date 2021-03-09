package com.baseknowledge.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 元注解:
 * Retention: 指定所修饰的 Annotation 的生命周期，SOURCE / CLASS (默认) /
 * RUNTIME (只有声明为 RUNTIME 的注解，才能通过反射获取)
 * <p>
 * Target: 指定所修饰的 Annotation 能用于修饰程序的哪些程序元素
 * <p>
 * Documented: 表示所修饰的 Annotation 被 javadoc 解析时，保留下来
 * <p>
 * Inherited: 表示被修饰的 Annotation 具有继承性，子类自动具有父类的注解
 *
 * @author miclefengzss
 */
public class Test {

    public static void main(String[] args) {
        Filter f1 = new Filter();
        f1.setId(1);
        f1.setUserName("wxm");

        Filter f2 = new Filter();
        f2.setEmail("miclefengzss@gmail.com,miclefengzss@163.com");

        String sql1 = query(f1);
        String sql2 = query(f2);

        System.out.println(sql1);
        System.out.println(sql2);
    }

    private static String query(Object f) {
        StringBuilder sql = new StringBuilder();

        // 1、 获取到 class
        Class c = f.getClass();

        // 2、 获取到表名
        boolean tExist = c.isAnnotationPresent(Table.class);
        if (!tExist) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sql.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1");

        // 3、 获取字段名
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            // 获取数据库字段名
            boolean fExist = field.isAnnotationPresent(Column.class);
            if (!fExist) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            Object fieldValue = null;
            try {
                // 获取类方法名
                String getMethodName = "get" + uCFirst(field.getName());
                Method method = c.getMethod(getMethodName);
                // 通过反射调用方法获取属性的值
                fieldValue = method.invoke(f);
                // 去除空值
                if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sql.append(" and ").append(columnName).append("=").append(fieldValue);
        }

        return sql.toString();
    }

    /**
     * 首字母转换为大写
     *
     * @param s
     * @return
     */
    private static String uCFirst(String s) {
        char[] charArray = s.toCharArray();
        charArray[0] &= 0xdf;
        return String.valueOf(charArray);
    }
}
