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

}
