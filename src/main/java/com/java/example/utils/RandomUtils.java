package com.java.example.utils;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符串操作类
 */
public class RandomUtils {


    public static final String NUMBERCHAR = "0123456789";

    /**
     * 返回指定长度的随机字符串
     *
     * @param length 字符串长度
     * @return:
     * @Date: 2020-04-03 15:02
     */
    public static String getRandomStr(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            int intVal = (int) (Math.random() * 26 + 97);
            result = result + (char) intVal;
        }
        return result;
    }

    /**
     * 根据奖励范围随机生成奖励
     *
     * @param start
     * @param end
     * @param scale
     * @return:
     * @Date: 2020-04-03 15:02
     */
    public static BigDecimal getRandomReward(double start, double end, int scale) {
        double doubleVal = Math.random() * end;
        if (doubleVal < start) {
            doubleVal = start;
        }
        return new BigDecimal(doubleVal).setScale(scale, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * 根据条件 生成随机数 （包含min  不包含max）
     *
     * @param min
     * @param max
     * @return:
     * @Date: 2020-04-03 15:02
     */
    public static int getRandomForIntegerBounded4(int min, int max) {
        int threadIntBound = ThreadLocalRandom.current().nextInt(min, max);
        return threadIntBound;
    }

}
