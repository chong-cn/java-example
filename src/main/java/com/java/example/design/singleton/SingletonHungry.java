package com.java.example.design.singleton;

/**
 * 饿汉式单例模式(基于lassloder机制,避免了多线程的同步问题)
 * <p>
 * 实现：定义私有构造函数，创建静态实例对象，提供静态方法返回实例对象
 * <p>
 * 缺点：无法延迟创建对象
 *
 */
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {

    }

    public static SingletonHungry getInstance() {
        return instance;
    }


}
