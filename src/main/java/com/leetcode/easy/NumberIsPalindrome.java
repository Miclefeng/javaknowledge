package com.leetcode.easy;

public class NumberIsPalindrome {

    public static void main(String[] args) {
        NumberIsPalindrome numberIsPalindrome = new NumberIsPalindrome();
        System.out.println(numberIsPalindrome.isPalindrome(1221));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

//        StringBuilder res = new StringBuilder();
        int origin = x;
        int revertedNumber = 0;
        while (origin > 0) {
            revertedNumber = revertedNumber * 10 + origin % 10;
//            res.append(origin % 10);
            origin /= 10;
        }

//        if (x == Long.parseLong(res.toString())) {
        return x == revertedNumber;
    }
}
