package com.java.example.thread.call;

import java.util.concurrent.Callable;

public class CallableTaskExample implements Callable<Integer> {
    // 返回值
    private int sum;

    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算");
        Thread.sleep(2000);
        for (int i = 0; i < 5000; i++) {
            sum = sum + i;
        }
        System.out.println("Callable子线程计算结束");
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Callable-Future测试");
        FutureExample futureExample = new FutureExample();
        futureExample.callableFutureMethod();

        System.out.println("Callable-FutureTask测试");
        FutureTaskExample futureTaskExample = new FutureTaskExample();
        futureTaskExample.callableFutureTaskMethod();
    }

}
