package com.algorithm.system.code06_heap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/11 15:59
 */
public class Test {

    public static void main(String[] args) {

        Set<String> passengerNameList = new HashSet<>(Arrays.asList("ZHANG/SAN".split("\\|")));
        Set<String> flightNumberList = Arrays.stream("VIE-BCN/VY8715|BCN-LGW/VY6010|STN-VIE/FR730".split("\\|")).map((item)->{
            String[] split = item.split("/");
            return split.length > 1 ? split[1] : split[0];
        }).collect(Collectors.toSet());

        System.out.println(passengerNameList);
        System.out.println(flightNumberList);

    }
}
