package com.java.example.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThreadMethod_3 implements Callable<String> {

    // 返回值类型在实现接口时指定
    public String call() throws Exception {
        System.out.println("实现Callable接口创建线程....");

        // 业务逻辑，将结果返回

        return "线程有返回值....";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CreateThreadMethod_3 threaMethod_3 = new CreateThreadMethod_3();
        FutureTask<String> stringFutureTask = new FutureTask<String>(threaMethod_3);
        new Thread(stringFutureTask).start();

        try {
            // 阻塞方法，直到返回结果
            String result = stringFutureTask.get();
            System.out.println("线程返回结果：" + result);
        } catch (Exception e) {
            System.out.println("捕捉异常....." + e);
        }


    }

}
