package com.java.example.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 回文数
 *
 * @author L.C
 */
@Slf4j
public class IsPalindromeExample {


    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        String numStr = String.valueOf(x);
        int len = numStr.length();
        for (int i = 0; i <= len / 2; i++) {
            String start = String.valueOf(numStr.charAt(i));
            String end = String.valueOf(numStr.charAt(len - 1 - i));
            if (!start.equals(end)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int num = 1233221;
        boolean status = isPalindrome(num);
        log.info("status = {}", status);
    }


}
