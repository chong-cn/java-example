package com.java.example.algorithm.eight;

import java.util.Arrays;

/**
 * 插入-直接插入排序
 */
public class InsertSort {

    /**
     * 直接插入排序思路：
     * 1.从数组的第一个开始，认为该数已经被排序；
     * 2.取出数组的下一个数，对已经排序的部分数组【从后向前】遍历: 若新取出的数大于其前一个数，结束当前遍历，反之，将其与其前一个数交换位置；
     * 3.重复步骤2，直到找到已排序的数小于或者等于新数的位置
     * 4.将新数插入到该位置后
     * 5.重复步骤2~4
     *
     * @Author: L.C
     * @Date: 2019-09-11 12:25
     */
    public static void insertSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] > arr[j - 1]) {
                    break;
                } else {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }

            System.out.println("第 " + (i + 1) + "次排序后：" + Arrays.toString(arr));

        }

    }


    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));
    }

}
