package com.java.example.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {

    public static void main(String[] args) {

        int corePoolSize = 10;
        int maximumPoolSize = 15;
        long keepAliveTime = 120;
        TimeUnit unit = TimeUnit.MINUTES;
        BlockingQueue<Runnable> workQueue = null;
        ThreadFactory threadFactory = null;
        RejectedExecutionHandler handler = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);

        threadPoolExecutor.execute(null);

    }
}
