package com.java.example.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * 信息脱敏工具类
 *
 * @Auther: L.C
 * @Date: 2020-03-23 14:14
 */
public class DesensitizationUtil {

    /**
     * 脱敏手机号码规则：138****1234
     *
     * @Author: L.C
     * @Date: 2019-12-31 17:56
     */
    public static String desensitizationMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        int len = mobile.length();
        if (len >= 11) {
            mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else if (len >= 7) {
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(len - 2, len);
        } else {
            return mobile;
        }
        return mobile;
    }

    /**
     * 脱敏邮箱规则：ab***@
     *
     * @Author: L.C
     * @Date: 2019-12-31 17:56
     */
    public static String desensitizationEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        return email.replaceAll("(^\\w{2})[^@]*(@.*$)", "$1****$2");
    }


    /**
     * 姓名脱敏
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static String desensitizationName(String name) {
        String newName = "";
        if (name == null || name.isEmpty()) {
            return newName;
        }
        int nameLen = name.length();
        if (name.length() == 2) {
            newName = name.substring(0, 1) + "*";
        }
        if (name.length() == 3) {
            newName = name.substring(0, 1) + "*"  + name.substring(1, 2);
        }
        if (name.length() > 3) {
            newName = name.substring(0, 2) + "**"  + name.substring(nameLen - 2, nameLen -1);
        }

        return newName;
    }

    /**
     * 身份证脱敏
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static String desensitizationCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return cardNum.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 护照脱敏
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static String desensitizationPassport(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return cardNum.substring(0, 2) + new String(new char[cardNum.length() - 5]).replace("\0", "*") + cardNum.substring(cardNum.length() - 3);

    }


    public static void main(String[] args) {
        String name = "Weda";
        String newName = desensitizationName(name);
        System.out.println("newName: " + newName);
//
//        String cardNum = "410523199005257577";
//        String passport = "D1234567";
//        cardNum = desensitizationCard(cardNum);
//        System.out.println("cardNum: " + cardNum);
//        passport = desensitizationPassport(passport);
//        System.out.println("passport: " + passport);


    }

}
