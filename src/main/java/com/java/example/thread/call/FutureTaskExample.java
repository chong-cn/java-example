package com.java.example.thread.call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    /**
     * 测试FutureTask类
     *
     * @return:
     * @Date: 2020-08-22
     */
    public void callableFutureTaskMethod() {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableTaskExample callableTaskExample = new CallableTaskExample();
        //创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableTaskExample);
        //执行任务，注意：futureTask可以直接执行new Threak(callableTaskExample).start();
        es.submit(futureTask);
        //关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if (futureTask.get() != null) {
                //输出获取到的结果
                System.out.println("futureTask.get()结果: " + futureTask.get());
            } else {
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行完成");

    }

}
