package com.java.example.algorithm.classic;

/**
 * 贪心算法：
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。
 * 你购入了 numBottles 瓶酒。如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * 请你计算最多能喝到多少瓶酒?
 */
public class GreedyAlgorithm {

    public static int greedyAlgorithm(int numBottles, int numExchange) {

        // 已喝到的酒数
        int sumBottles = numBottles;
        // 空酒瓶数
        int sumEmpty = numBottles;
        while ((sumEmpty / numExchange) != 0) {
            // 新换的酒数
            int tempEmpty = sumEmpty / numExchange;
            // 换到酒喝掉
            sumBottles += tempEmpty;
            // 空酒瓶数量
            sumEmpty = tempEmpty + sumEmpty % numExchange;
        }
        return sumBottles;
    }

    public static void main(String[] args) {
        int numBottles = 11;
        int numExchange = 3;
        int count = greedyAlgorithm(numBottles, numExchange);

        System.out.println("喝到的酒数量为：" + count);
    }
}
