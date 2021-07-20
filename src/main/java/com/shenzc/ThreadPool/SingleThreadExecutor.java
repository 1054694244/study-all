package com.shenzc.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * 如果当前线程意外终止，会创建一个新线程继续执行任务，这和我们直接创建线程不同，也和newFixedThreadPool(1)不同。
 * ExecutorService exe= Executors.newSingleThreadExecutor();
 */
public class SingleThreadExecutor {

    public static void main(String[] args){

        //每个结果都是相隔0.5秒打印出来的，顺序执行下去。
        ExecutorService exe= Executors.newSingleThreadExecutor();
        for(int i=1;i<6;i++){
            final int taskID=i;
            exe.execute(new Runnable() {
                public void run() {
                    for(int j=1;j<4;j++){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程ID："+taskID+"，执行第 "+j+" 次");
                    }
                }
            });
        }
    }

}
