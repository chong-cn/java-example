package com.java.example.lambda;

import lombok.Data;

/**
 * 示例类型
 */
@Data
public class PersonModel {

    private String name;
    private int age;
    private int size;

    @Override
    public String toString() {
        return "PersonModel [name=" + name + ", age=" + age + ", size=" + size + "]";
    }

    public PersonModel(String name, int age, int size) {
        super();
        this.name = name;
        this.age = age;
        this.size = size;
    }

    public PersonModel() {
        super();
    }

}
