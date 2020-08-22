package com.java.example.thread.call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    /**
     * 测试Future类
     *
     * @return:
     * @Date: 2020-08-22
     */
    public void callableFutureMethod() {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableTaskExample callableTaskExample = new CallableTaskExample();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(callableTaskExample);
        //关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");
            if (future.get() != null) {
                //输出获取到的结果
                System.out.println("future.get()结果: " + future.get());
            } else {
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行完成");
    }
}
