package com.java.example.thread.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateThreadMethod_4 implements Runnable {

    public void run() {
        System.out.println("线程池创建线程....");
    }

    public static void main(String[] args) {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new CreateThreadMethod_4());
            System.out.println("创建第 " + i + " 个线程");
        }

        executorService.shutdown();
    }
}
