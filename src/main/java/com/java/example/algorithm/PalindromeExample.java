package com.java.example.algorithm;

/**
 * 最长回文串
 *
 * @Auth: L.C
 */
public class PalindromeExample {

    // 寻找回文串
    public static String palindrome(String str, int l, int r) {
        String res = "";
        char[] strChar = str.toCharArray();
        // 防止索引越界
        while (l >= 0 && r < strChar.length && strChar[l] == strChar[r]) {
            l--;
            r++;
        }
        if (r==l+1) {
            return res;
        }
        return str.substring(l + 1, r);
    }

    // 最长回文串
    public static String longestPalindrome(String str) {
        String res = "";
        for (int i = 1; i < str.length(); i++) {
            // 以奇数长度为中心的最长回文子串
            String s1 = palindrome(str, i, i);
            // 以偶数长度为中心的最长回文子串
            String s2 = palindrome(str, i, i + 1);

            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    public static void main(String[] args) {
        String str = "abbcddddddcbbasasaccgggg";
        String res = longestPalindrome(str);
        System.out.println("最长回文串：" + res);
    }

}
