package com.java.example.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式基础语法：Java 8 引入操作符 ->
 * <p>
 * 左边：Lambda 表达式的参数列表（对应接口中抽象方法的参数）
 * 右边：Lambda 表达式中所需执行的功能，即 Lambda 体（对应抽象方法的实现）
 * <p>
 * <p>
 * 二、Lambda 表达式需要"函数式接口"的支持
 * <p>
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可使用注解 @FunctionalInterface 修饰，
 * 可检查接口是否是函数式接口
 */
public class LambdaExample {

    /**
     * 语法格式一：无参数，无返回值
     * <p>
     * () -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_1() {

        // JDK 1.7 以前，必须是final； 1.8 还是final，但不需要写
        int num = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        runnable.run();

        System.out.println("-----------------------------------");
        // Lambda
        Runnable lambdaRunable = () -> System.out.println("Hello World!");
        lambdaRunable.run();

    }

    /**
     * 语法格式二：单参数，无返回值
     * <p>
     * (param) -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_2() {

        Consumer<String> con = (param) -> System.out.println(param);
        con.accept("Lambda 测试单参数无返回值语法....");

    }

    /**
     * 语法格式三：单参数(括号可省略)，无返回值
     * <p>
     * param -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_3() {

        Consumer<String> con = param -> System.out.println(param);
        con.accept("Lambda 测试单参数无返回值语法....");

    }

    /**
     * 语法格式四：多参数且 Lambda 体中有多条语句，有返回值
     * <p>
     * （param1， param2） -> {Lambda体; return};
     */
    public static void lambdaGrammar_4() {

        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口....");
            return Integer.compare(x, y);
        };
    }

    /**
     * 语法格式五：若 Lambda 体中只有一条语句，则 return 和 大括号都可以省略不屑
     * <p>
     * （param1， param2） -> Integer.compare(x, y);
     */
    public static void lambdaGrammar_5() {

        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    /**
     * 语法格式六：Lambda 表达式的参数列表数据类型可以省略，因为 JVM 编译器通过上下文推断出类型，即"类型推断"。
     * <p>
     * （Integer x, Integer y） -> Integer.compare(x, y);
     */
    public static void lambdaGrammar_6() {

        Comparator<Integer> com = Integer::compare;
    }

}
