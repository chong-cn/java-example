package com.java.example.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 语言工具类
 */
public class LanguageUtils {

    /**
     * 是否为中文
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;
        }
        return false;
    }

    /**
     * 是否为中文
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static boolean isChinese(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }

        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 是否为英文
     *
     * @return:
     * @Date: 2020-08-26
     */
    public static boolean isEnglish(String str){
        if (StringUtils.isBlank(str)) {
            return true;
        }
        String regEx = "^[a-zA-Z]*";
        return str.matches(regEx);
    }

}
