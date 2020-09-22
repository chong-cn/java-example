package com.java.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 流操作 Strean API
 * <p>
 * 1. 筛选与切片
 * 2. 映射
 */
public class StreamOptExamle {

    List<Employee> employeeList = Arrays.asList(
            new Employee(1, "张三", 20, 34.21),
            new Employee(2, "李四", 30, 44.43),
            new Employee(3, "王五", 40, 12.09),
            new Employee(4, "赵六", 50, 54.71),
            new Employee(5, "田七", 60, 24.71),
            new Employee(5, "田七", 60, 24.71),
            new Employee(5, "田七", 60, 24.71)
    );


    /**
     * 筛选与切片-filter:
     * 接收 Lambda，从流中排除某些元素
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void filterStream() {
//        Stream<Employee> stream = employeeList.stream().filter(
//                (e) -> e.getAge() > 30);
//
//        stream.forEach(System.out::println);

        // 中间操作: 不会执行任何操作
        Stream<Employee> stream = employeeList.stream().filter(
                (e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 30;
                });
        // 终止操作: 一次性执行全部内容，即"惰性求值"
        stream.forEach(System.out::println);

    }

    /**
     * 筛选与切片-limit:
     * 截断流，使其元素不超过给定数量
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void limitStream() {
        employeeList.stream()
                .filter((e) -> {
                    System.out.println("短路！");
                    return e.getAge() > 30;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * 筛选与切片-skip:
     * 跳过元素，返回了一个扔掉了前 n 个元素的流。若流中元素不够 n 个， 返回空流，与 limit 互补
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void skipStream() {
        employeeList.stream()
                .filter((e) -> e.getAge() > 40)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * 筛选与切片-distinct:
     * 通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void distinctStream() {
        employeeList.stream()
                .filter((e) -> e.getAge() > 40)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射-map:
     * 接收 Lambda，将元素转换成其他形式或提取信息。
     * 接收一个函数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void mapStream() {
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd");

        strList.stream()
                .map((e) -> e.toUpperCase())
                .forEach(System.out::println);

        employeeList.stream()
                .map((e) -> e.getName())
                .forEach(System.out::println);

        Stream<Stream<Character>> mapStream = strList.stream()
                .map(StreamOptExamle::filterCharacter);

        mapStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
    }

    /**
     * 映射-flatMap:
     * 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连成一个流
     *
     * @return:
     * @Date: 2020-08-31
     */
    public void flatMapStream() {
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd");

        Stream<Stream<Character>> flatStream = strList.stream()
                .map(StreamOptExamle::filterCharacter);

        flatStream.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> characterList = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            characterList.add(ch);
        }

        return characterList.stream();
    }

}
