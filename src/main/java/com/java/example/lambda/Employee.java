package com.java.example.lambda;

import lombok.Data;

@Data
public class Employee {

    private int id;

    private String name;

    private int age;

    private double salary;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}
