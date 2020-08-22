package com.java.example.design.singleton;

/**
 * 懒汉式单例模式(适合多线程)
 * <p>
 * 实现：预定义对象变量，在需要实例时进行初始化
 * <p>
 * 缺点：synchronized导致效率下降，单线程情况下可去掉synchronized
 *
 */
public class SingletonLazy {

    private static volatile SingletonLazy instance;

    private SingletonLazy() {

    }

    public static synchronized SingletonLazy getInstance() {
        if (null == instance) {
            instance = new SingletonLazy();
        }

        return instance;
    }

}
