package com.java.example.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HuaiweiExample {

    public static String outSubStr(String str, int index) {
        String result = null;
        if (str.length() > 1000) {
            return result;
        }
        char[] charArr = str.toCharArray();
        List<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < charArr.length; i++) {
            if (';' == charArr[i]) {
                indexList.add(i);
            }
        }

        int size = indexList.size();
        if (size > 0) {
            if (index > size) {
                return null;
            }
            if (0 == index) {
                int target = indexList.get(index);
                result = str.substring(0, target);
            } else {
                int target1 = indexList.get(index - 1);
                int target2 = indexList.get(index);
                result = str.substring(target1 + 1, target2);
            }
        }
        return result;

    }

    public static boolean judegeStr(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        if (len1 < len2 || len1 < 5 || len2 < 5)
            return false;
        char[] charArr2 = str2.toCharArray();
        for (int i = 0; i < len2; i++) {
            int index = str1.indexOf(charArr2[i] + "");
            if (index >= 0 && index < len1) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        String str = "ncX;32Ba;djjhu;kjkk";
//        Scanner sc = new Scanner(System.in);
//        String str = sc.next();
//        int index = sc.nextInt();
//        String result = outSubStr(str, index);
//        System.out.println("结果是：" + result);

        //        Scanner sc = new Scanner(System.in);
//        String str1 = sc.next();
//        String str2 = sc.next();
        String str1 = "BBDDCFFEL";
        String str2 = "LCEFB";
        boolean result = judegeStr(str1, str2);
        System.out.println("结果是：" + result);
    }
}
