package com.java.example.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式基础语法：Java 8 引入操作符 ->
 * <p>
 * 左边：Lambda表达式的参数列表（对应接口中抽象方法的参数）
 * 右边：Lambda表达式中所需执行的功能，即 Lambda体（对应抽象方法的实现）
 */
public class LambdaDefineExample {

    /**
     * 语法格式一：无参数，无返回值
     *
     * () -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_1() {

        int num = 0; // JDK 1.7 以前，必须是final； 1.8 还是final，但不需要写
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
     *
     * (param) -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_2() {

        Consumer<String> con = (param) -> System.out.println(param);
        con.accept("Lambda 测试单参数无返回值语法....");

    }

    /**
     * 语法格式三：单参数(括号可省略)，无返回值
     *
     * param -> System.out.println("Hello World!");
     */
    public static void lambdaGrammar_3() {

        Consumer<String> con = param -> System.out.println(param);
        con.accept("Lambda 测试单参数无返回值语法....");

    }

    /**
     * 语法格式四：多参数且Lambda体中有多条语句，有返回值
     *
     * （param1， param2） -> {Lambda体; return};
     */
    public static void lambdaGrammar_4() {

        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口....");
            return Integer.compare(x, y);
        };

    }
}
