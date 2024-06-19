package com.designpattern.pattern.behavioral.oldobserver;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/6/15 15:48
 */
public class Test {

    public static void main(String[] args) {
        LotteryServiceImpl lotteryService = new LotteryServiceImpl();
        Lottery lottery = lotteryService.lotteryAndMessage("11213141415");
        System.out.println(lottery);
    }
}
