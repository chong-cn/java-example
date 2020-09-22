package com.java.example.algorithm.eight;

import java.util.Arrays;

/**
 * 交换-冒泡排序
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }


    public static void main(String[] args) {
        int[] arr = {8, 3, 10, 1, 5, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));

    }


}
