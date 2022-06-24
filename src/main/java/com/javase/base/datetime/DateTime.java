package com.javase.base.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author miclefengzss
 * @create 2020-07-23 1:50 下午
 */

public class DateTime {

    public static void main(String[] args) throws ParseException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        System.out.println(time);

        String birth = "1992-03-31";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat1.parse(birth);

        java.sql.Date date2 = new java.sql.Date(date1.getTime());
        System.out.println(date2);

        System.out.println("================================");

        /**
         * LocalDate / LocalTime / LocalDateTime
         */
        // now(): 获取当前的日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        System.out.println("======================");
        // of(): 设置指定时间
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 7, 23, 21, 10, 51);
        System.out.println(localDateTime1);

        // getXxx():
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonthValue());

    }
}
