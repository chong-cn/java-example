package com.java.example.algorithm.eight;

import java.util.Arrays;

/**
 * 插入-希尔排序
 */
public class ShellSort {

    /**
     * 希尔排序思路：
     * 1.选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（一般初次取数组半长，之后每次再减半，【直到增量为1】）
     * 2.按增量序列个数k，对序列进行k 趟排序；
     * 3.每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 4.仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @Author: L.C
     * @Date: 2019-09-11 12:23
     */
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i + gap < arr.length; i++) {
                for (int j = 0; j + gap < arr.length; j += gap) {
                    if (arr[j] > arr[j + gap]) {
                        //交换位置
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));
    }

}
