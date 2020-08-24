package com.java.example.algorithm.base;

import java.util.Arrays;

/**
 * 选择-堆排序
 */
public class HeapSort {

    public static void heapSort(int[] arr) {

        for (int n = arr.length - 1; n > 0; n--) {
            maxHeapify(arr, n);
            // 堆顶元素(第一个元素)与Kn交换
            int temp = arr[0];
            arr[0] = arr[n];
            arr[n] = temp;
        }

    }

    /**
     * 建堆
     *
     * @Author: L.C
     * @Date: 2019-09-11 13:11
     */
    public static void maxHeapify(int[] arr, int n) {
        int child;
        for (int i = (n - 1) / 2; i >= 0; i--) {
            // 左子节点位置
            child = 2 * i + 1;
            // 右子节点存在且大于左子节点，child变成右子节点
            if (child != n && arr[child] < arr[child + 1]) {
                child++;
            }
            // 交换父节点与左右子节点中的最大值
            if (arr[i] < arr[child]) {
                int temp = arr[i];
                arr[i] = arr[child];
                arr[child] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Arr排序后：" + Arrays.toString(arr));
    }

}
