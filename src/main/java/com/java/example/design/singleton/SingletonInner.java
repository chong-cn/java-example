package com.java.example.design.singleton;

/**
 * 静态内部类单例模式(线程安全)
 *
 * 实现：内部类中创建实例对象，因为静态内部类只会被家在一次
 *
 */
public class SingletonInner {

    private static class Holder {
        private static SingletonInner singleton = new SingletonInner();
    }

    private SingletonInner () {

    }

    public static SingletonInner getInstance() {
        return Holder.singleton;
    }

}
