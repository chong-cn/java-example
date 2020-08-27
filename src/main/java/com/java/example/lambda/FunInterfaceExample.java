package com.java.example.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java 8 内置四大函数式接口
 * <p>
 * Consumer<T> : 消费型接口
 * void accept(T t);
 * <p>
 * <p>
 * Supplier<T t> : 供给型接口
 * T get();
 * <p>
 * <p>
 * Function<T, R> : 函数式接口
 * R apply(T t);
 * <p>
 * <p>
 * Predicat<T c> : 断言型接口
 * boolean test(T t);
 */
public class FunInterfaceExample {

    /**
     * Consumer<T> : 消费型接口
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void consumerMethod() {
        double money = 10000;
        consumerAccept(money, (m) -> System.out.println("你们刚哥喜欢大保健， 每次消费: " + m + "元"));
    }

    public void consumerAccept(double money, Consumer<Double> con) {
        con.accept(money);
    }

    /**
     * Supplier <T> : 供给型接口
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void supplierMethod() {
        int count = 10;
        List<Integer> numList = supplierGet(count, () -> (int) (Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> supplierGet(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }

        return list;
    }

    /**
     * Supplier <T> : 供给型接口
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void functionMethod() {
        String str = "\t\t\t 字符串处理 ";
        String newStr = functionApply(str, (strParam) -> str.trim());
        System.out.println(newStr);
    }

    public String functionApply(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * Predicate <T> : 断言型接口
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void predicateMethod() {
        List<String> list = Arrays.asList("Hello", "World", "Java", "Lambda", "OK");

        List<String> newList = predicateTest(list, (str) -> str.length() > 3);

        for (String str : newList) {
            System.out.println(str);
        }
    }

    public List<String> predicateTest(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }

        return strList;
    }
}
