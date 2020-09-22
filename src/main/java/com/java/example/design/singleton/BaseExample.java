package com.java.example.design.singleton;

/**
 * 双重检查锁
 */
public class BaseExample {

    private static volatile BaseExample baseExample = null;

    private BaseExample() {

    }

    public static BaseExample getInstance() {
        if (null == baseExample) {
            synchronized (BaseExample.class) {
                if (null == baseExample) {
                    baseExample = new BaseExample();
                }
            }
        }



        return baseExample;
    }
}
