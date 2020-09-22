package com.java.example.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用: 若 Lambda 体中已有方法的实现，可使用"方法引用"
 * (可以理解为方法引用是 Lambda 表达式的另外一种表现形式)
 * <p>
 * 主要有三种语法格式：
 * <p>
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * <p>
 * 注意：
 * 1. Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * 2. 若 Lambda 参数列表中，第一个参数是实例方法的调用者， 第二个参数是实例方法的参数参数时，则可以使用 ClassName::method
 * <p>
 * <p>
 * 二、构造器引用：
 * <p>
 * 格式: ClassName::new
 * <p>
 * 注意: 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 *
 * 三、数组引用
 *
 * 格式: Type::new
 *
 *
 */
public class MethodRefExample {

    /**
     * 对象::实例方法名
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void grammar_1() {

        String str = "abc";
        PrintStream ps = System.out;

        Consumer<String> con_1 = (x) -> ps.println(x);
        con_1.accept(str);

        Consumer<String> con_2 = ps::println;
        con_2.accept(str);

        Consumer<String> con_3 = System.out::println;
        con_3.accept(str);
    }

    /**
     * 类::静态方法名
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void grammar_2() {

        Comparator<Integer> com_1 = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com_2 = Integer::compare;
    }

    /**
     * 类::实例方法名
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void grammar_3() {

        BiPredicate<String, String> bp_1 = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp_2 = String::equals;

    }

    /**
     * 构造器引用
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void grammar_4() {

        int id = 100;
        int age = 20;

        Supplier<Employee> sup_1 = Employee::new;
        sup_1.get();

        Supplier<Employee> sup_2 = Employee::new;
        sup_2.get();

        Function<Integer, Employee> fun_1 = (x) -> new Employee(x);
        fun_1.apply(id);

        Function<Integer, Employee> fun_2 = Employee::new;
        fun_2.apply(id);

        BiFunction<Integer, Integer, Employee> bp = Employee::new;
        bp.apply(id, age);
    }

    /**
     * 数组引用
     *
     * @return:
     * @Date: 2020-08-27
     */
    public void grammar_5() {

        int len = 10;

        Function<Integer, String[]> fun_1 = (x) -> new String[x];
        String[] strArr_1 = fun_1.apply(len);

        Function<Integer, String[]> fun_2 = String[]::new;
        String[] strArr_2 = fun_2.apply(len);


    }


}
