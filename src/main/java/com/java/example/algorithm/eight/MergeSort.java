package com.java.example.algorithm.eight;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    // 归并所需的辅助数组
    private static int[] aux;

    public static void mergeSort(int[] arr) {
        // 一次性分配空间
        aux = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        // 将左半边排序
        sort(arr, low, mid);
        // 将右半边排序
        sort(arr, mid + 1, high);
        // 归并
        merge(arr, low, mid, high);

    }

    /**
     * 归并排序思路：
     * 先将所有元素复制到aux[]中，然后在归并回arr[]中。方法在归并时(第二个for循环)进行了4个条件判断：
     * - 左半边用尽(取右半边的元素)
     * - 右半边用尽(取左半边的元素)
     * - 右半边的当前元素小于左半边的当前元素(取右半边的元素)
     * - 右半边的当前元素大于等于左半边的当前元素(取左半边的元素)
     *
     * @Author: L.C
     * @Date: 2019-09-11 15:13
     */
    public static void merge(int[] arr, int low, int mid, int high) {

        // 将arr[low..mid]和arr[mid+1..high]归并
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > high) {
                arr[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));
    }


}
