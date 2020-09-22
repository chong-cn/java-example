package com.java.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建 Stream API
 */
public class StreamCreateExample {


    /**
     * 创建 Stream
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void createStream() {

        // 第一种: 通过 Collection 系列集合提供的 stream() 或 parallelStream ()
        List<String> list = new ArrayList<>();
        Stream<String> listStream = list.stream();

        // 第二种: 通过 Arrays 中的静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> arrayStream = Arrays.stream(emps);

        // 第三种: 通过 Stream 类中的静态方法 of() 获取流
        Stream<String> stream = Stream.of("aa", "bb", "cc");

        // 第四种: 创建无限流(迭代和生成两种方式)
        int seed = 0;
        int limit = 10;
        Stream<Integer> iterateStream = Stream.iterate(seed, (x) -> x + 2);
        iterateStream.limit(limit).forEach(System.out::println);

        Stream<Double> generateStream = Stream.generate(Math::random);
        generateStream.limit(limit).forEach(System.out::println);

    }




}
