package com.java.example.design.singleton;

/**
 * 饿汉式单例模式(基于lassloder机制,避免了多线程的同步问题)，天生的线程安全
 * <p>
 * 实现：定义私有构造函数，创建静态实例对象，提供静态方法返回实例对象
 * <p>
 * 缺点：无法延迟创建对象
 *
 */
public class HungryExample {

    private static HungryExample instance = new HungryExample();

    private HungryExample() {

    }

    public static HungryExample getInstance() {
        return instance;
    }


}
