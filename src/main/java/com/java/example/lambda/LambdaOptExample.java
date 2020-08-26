package com.java.example.lambda;

import com.java.example.model.PersonModel;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流和Lambda表达式处理集合
 *
 * @Auther: L.C
 * @Date: 2020-03-26 17:06
 */
public class LambdaOptExample {

    public static void main(String[] args) {
        List<PersonModel> list = new ArrayList<>();
        PersonModel p1 = new PersonModel("张1", 1, 1);
        PersonModel p101 = new PersonModel("张101", 101, 101);
        PersonModel p2 = new PersonModel("张2", 2, 2);
        PersonModel p3 = new PersonModel("张3", 3, 3);
        PersonModel p4 = new PersonModel("张4", 4, 4);
        PersonModel p5 = new PersonModel("张5", 5, 5);
        PersonModel p6 = new PersonModel("张6", 6, 6);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p101);

        /**
         * 方式一：forEach()进行遍历集合
         * item：可以是任意值。类似于for循环中的循环值
         *
         */
        list.forEach(item -> {
            //设置值
            item.setName(item.getName() + "测试");
            //输出语句
            System.out.println(item.toString());
        });

        /**
         * 方式二：stream()流操作
         *
         */
        // 2.1. 去重 distinct() 去重；collect(Collectors.toList())。封装成集合
        List<PersonModel> distinctList = list.stream().distinct().collect(Collectors.toList());
        // 2.2 排序  sorted((第一个对象，第二个对象)->返回值)  （升降序看是第几个对象与第几个对象比较）
        List<PersonModel> sortedList = list.stream().sorted((o1, o2) -> o1.getAge() - o2.getAge()).collect(Collectors.toList());
        // 2.3 过滤 ， filter(item->{})   item为每一项。 按照自己的需求来筛选list中的数据
        List<PersonModel> filterList = list.stream().filter(item -> item.getAge() > 3).collect(Collectors.toList());
        // 2.4 map(), 提取对象中的某一元素.  用每一项来获得属性（也可以直接用  对象::get属性()）
        List<String> mapList1 = list.stream().map(PersonModel::getName).collect(Collectors.toList());
        List<String> mapList2 = list.stream().map(PersonModel::getName).collect(Collectors.toList());
        // 2.5 统计 sum() 。mapToDouble() 转换成double。还有其他类型转换。可以自己研究。max(),min(),average()
        double sum = list.stream().mapToDouble(PersonModel::getAge).sum();
        // 2.6 分组   Collectors.groupingBy(属性名)
        Map<Integer, List<PersonModel>> map = list.stream().collect(Collectors.groupingBy(PersonModel::getAge));
        // 2.7 多重分组 Collectors.groupingBy(属性，Collectors.groupingBy(属性))
        Map<String, Map<Integer, List<PersonModel>>> map2 = list.stream().collect(Collectors.groupingBy(PersonModel::getName, Collectors.groupingBy(PersonModel::getAge)));
        // 2.8 分组并计算综合        Collectors.summarizingLong()
        Map<String, Map<Integer, LongSummaryStatistics>> map3 = list.stream().collect(Collectors.groupingBy(PersonModel::getName, Collectors.groupingBy(PersonModel::getAge, Collectors.summarizingLong(PersonModel::getSize))));

        /**
         * 方式三： 集合比较的简写方式
         *
         */
        list.sort((o1, o2) -> {
            return o1.getAge() - o2.getAge();
        });
    }

}
