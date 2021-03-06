package com.java.example.design.singleton;

/**
 * 静态内部类单例模式(线程安全)
 * <p>
 * 实现：内部类中创建实例对象，因为静态内部类只会被加载一次
 * <p>
 * 推荐使用✨
 */
public class InnerExample {

    private static class Holder {
        private static InnerExample singleton = new InnerExample();
    }

    private InnerExample() {

    }

    public static InnerExample getInstance() {
        return Holder.singleton;
    }

}
