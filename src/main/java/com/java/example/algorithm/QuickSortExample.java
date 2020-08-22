package com.java.example.algorithm;

import java.util.Arrays;

/**
 * 快速排序算法
 *
 * @Auth: L.C
 */
public class QuickSortExample {

    public static void quickSort(int[] arr, int low, int high) {
        if (low > high)
            return;

        int right = high;
        int left = low;

        // 临时存储位置
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left];

        }

        arr[left] = temp;
        System.out.println("以：" + temp + " 为基准排序后： " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);

    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 0, 1, 4, 6, 2, 5, 7, 9};
        quickSort(arr, 0, 9);
    }


}
