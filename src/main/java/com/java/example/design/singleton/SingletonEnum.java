package com.java.example.design.singleton;

/**
 * 枚举实现单例模式
 */
public enum SingletonEnum {

    INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
