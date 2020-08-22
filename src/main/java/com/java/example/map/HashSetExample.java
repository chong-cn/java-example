package com.java.example.map;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

    /**
     * 查找第一个重复的字符
     *
     * @param: str 字符串
     * @return: char 第一个重复的字符
     */
    public static String findFirstRepeatedChar(String str) {

        if (null == str || str.length() <= 1)
            return null;
        char[] charArr = str.toCharArray();
        Set<String> strSet = new HashSet<String>();
        for (int i = 0; i < charArr.length; i++) {
            boolean result = strSet.add(charArr[i] + "");
            if (!result) {
                return String.valueOf(charArr[i]);
            }
        }
        return null;
    }


    public static void main(String[] args) {
        String str = "abcdvcd";
        String result = findFirstRepeatedChar(str);
        System.out.println(result);
    }
}
