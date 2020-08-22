package com.java.example.interview;

public class YuxiaoerExample {


    public static int findIndexOne(int[] dist, int[] search) {
        int index = -1;
        String distStr = "";
        for (int i = 0; i < dist.length; i++) {
            distStr += dist[i];
        }
        String searchStr = "";
        for (int i = 0; i < search.length; i++) {
            searchStr += search[i];
        }

        index = distStr.indexOf(searchStr);

        return index;
    }


    public static int findIndexTwo(int[] dist, int[] search) {
        int index = -1;
        String distStr = "";
        for (int i = 0; i < dist.length; i++) {
            distStr = distStr + dist[i] + "";
        }
        String temp = distStr;
        for (int i = 0; i < search.length; i++) {
            String regex = search[i] + "";
            String[] tempArr = temp.split(regex);
            if (tempArr.length > 1) {
                temp = temp.substring(temp.indexOf(regex) + 1, temp.length());
            }
        }
        if (temp.length() >= 1) {
            index = distStr.indexOf(search[0] + "");
            return index;
        } else {
            return index;
        }

    }


    public static void main(String[] args) {
        int[] dist = {9, 3, 5, 4, 6, 4, 3, 6, 5};
        int[] search = {5, 4, 6};

//        int[] dist = {1, 5, 2, 3, 4, 5, 6};
//        int[] search = {5, 4, 6};

//        int index = findIndexOne(dist, search);
        int index = findIndexTwo(dist, search);
        System.out.println("Index：" + index);
    }


}
