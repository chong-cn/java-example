package com.java.example.thread.create;

public class CreateThreadMethod_2 implements Runnable {
    public void run() {
        System.out.println("实现Runable接口创建线程......");
    }


    public static void main(String[] args) {
        CreateThreadMethod_2 threadImplementMethod = new CreateThreadMethod_2();
        Thread thread = new Thread(threadImplementMethod);
        thread.start();
    }
}
