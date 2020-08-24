package com.java.example.algorithm.base;

import java.util.Arrays;

/**
 * 选择-选择排序
 */
public class SelectSort {

    /**
     * 选择排序思路：
     * 1.从待排序数组中，找到其中最小的数；
     * 2.如果最小数不是待排序序列的第一个元素，将其和第一个数互换；
     * 3.从余下的 N - 1 个数中，找出关键字最小的元素；
     * 4.重复1～3，直到排序结束。
     *
     * @Author: L.C
     * @Date: 2019-09-11 12:45
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int var = arr[i];
                arr[i] = arr[min];
                arr[min] = var;
            }


        }

    }

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));
    }

}
