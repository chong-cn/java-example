package com.java.example.thread.create;

public class CreateThreadMethod_1 extends Thread{

    public void run() {
        System.out.println("继承Thread类创建线程....");
    }

    public static void main(String[] args) {
        CreateThreadMethod_1 threadExtendMethod = new CreateThreadMethod_1();
        threadExtendMethod.start();
    }

}
