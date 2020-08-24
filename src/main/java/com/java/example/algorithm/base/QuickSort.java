package com.java.example.algorithm.base;

import java.util.Arrays;

/**
 * 交换-快速排序
 */
public class QuickSort {

    /**
     * 快速排序的思路：
     * 1.选取一个基准值temp，一般以下标最小开始temp=arr[left];
     * 2.将基准值与数组【从后向前】遍历的数比较：若arr[right] >= temp,则right--，继续比较，反之，结束遍历，arr[left]=arr[right];
     * 3.将基准值与数组【从前向后】遍历的数比较：若arr[left] <= temp,则left++，继续比较，反之，结束遍历，arr[right]=arr[left];
     * 4.将基准值temp放在正确位置：arr[left] = temp;
     * 5.以基准值为界，使用递归对其两边的数组排序。
     * 注意：此处的基准值仅仅是作为一个基准，不参与数组前后数据的移动，直到一轮排序结束了才会将其插入数组中，也就是，在基准值左边的都比其小，右边的都比其大。
     *
     * @param: arr    数组
     * @param: low    起始下标
     * @param: high   结束下标
     * @return: void
     * @Date: 2019-09-25 13:58
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }

        int left = low;
        int right = high;
        // 基准，作为比较的基准值
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
        int[] arr = {5, 8, 3, 10, 1, 0, 9, 2, 4, 6, 7};
        System.out.println("Arr排序前：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Arr排序后：" + Arrays.toString(arr));

    }

}
