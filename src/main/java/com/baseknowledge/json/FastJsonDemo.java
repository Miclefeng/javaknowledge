package com.baseknowledge.json;

import com.alibaba.fastjson.JSON;

import java.util.Calendar;
import java.util.Date;

/**
 * @author miclefengzss
 * 2021/3/19 下午5:42
 */
public class FastJsonDemo {

    public static void main(String[] args) {
        Date time = Calendar.getInstance().getTime();
        Employee employee = new Employee(1, "micheal", "developer", time, 25000.00f, "jishu");
        String s = JSON.toJSONString(employee);
        System.out.println(s);

        Employee employee1 = JSON.parseObject(s, Employee.class);
        System.out.println(employee1);
    }
}
