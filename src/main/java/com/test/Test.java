package com.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/22 15:57
 */
public class Test {

    public static void main(String[] args) {
        test();

        LocalDateTime s = LocalDateTime.parse("2024-02-24 20:00:00",  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime e = LocalDateTime.parse("2024-02-26 20:00:00",  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<String> timeList = new ArrayList<>();

        for (;;) {
            if (s.compareTo(e) <= 0) {
                timeList.add(s.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                s = s.plusHours(4);
            } else {
                break;
            }
        }

        System.out.println("timeList = " + timeList);

        String str = "123456";
        System.out.println(str.substring(0, str.length() - 1));

        int flag = 0;
        flag |= 1;
        System.out.println("flag = " + flag);
        flag |= 1 << 1;
        System.out.println("flag = " + flag);
        flag |= 1 << 2;
        System.out.println("flag = " + flag);
        System.out.println((flag & 1));
        System.out.println((flag & 2));
        System.out.println((flag & 4));
        System.out.println("==========================");
        List<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        List<Integer> o = l.stream().filter(i -> i == 0).collect(Collectors.toList());
        System.out.println("o = " + o);

    }

    private static boolean test() {
        try {
            Thread.sleep(1000);
            System.out.println("normal");
            return true;
        } catch (Exception e) {
            System.out.println("exception");
        } finally {
            System.out.println("finally");
        }
        return false;
    }
}
