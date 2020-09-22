package com.java.example.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExample {

    public static void subListTest() {
        List<Integer> insertList = new ArrayList<>();

        for (int i = 0; i < 483; i++) {
            insertList.add(i);
        }

        if (insertList.size() > 0) {
            int size = insertList.size();
            int insertInit = 0;
            while (insertInit <= size) {
                int temp = insertInit + 20;
                if (temp >= size) {
                    temp = size;
                }

                if (insertInit == temp) {
                    break;
                }
                List<Integer> tempList = insertList.subList(insertInit, temp);
                System.out.println("tempList: " + Arrays.toString(tempList.toArray()));
                insertInit = temp;
                System.out.println("temp: " + temp);
            }

        }
    }


    public static void main(String[] args) {
        subListTest();
    }


}
