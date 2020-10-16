package com.java.example.design.singleton;

/**
 * 枚举实现单例模式，无法实现懒加载
 */
public enum EnumExample {

    INSTANCE;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
