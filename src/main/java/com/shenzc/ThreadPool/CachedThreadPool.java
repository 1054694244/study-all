package com.shenzc.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 缓存型线程池，先查看池中有没有以前建立的线程，如果有，就重用，如果没有，就建一个新的线程加入池中。
 * 如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * ExecutorService executorService = Executors.newCachedThreadPool();
 */

/**
 * 总结：
 * 1.重用：缓存型池子，先查看池中有没有以前建立的线程，如果有，就reuse；如果没有，就建一个新的线程加入池中
 * 2.使用场景：缓存型池子通常用于执行一些生存期很短的异步型任务，因此在一些面向连接的daemon型SERVER中用得不多。
 * 3.超时：能reuse的线程，必须是timeout IDLE内的池中线程，缺省timeout是60s，超过这个IDLE时长，线程实例将被终止及移出池。
 * 4.结束：注意，放入CachedThreadPool的线程不必担心其结束，超过TIMEOUT不活动，其会自动被终止。
 */
public class CachedThreadPool {

    //可以看到执行结果是5个任务在交替进行的
    public static void main(String[] args) {
        ExecutorService exe= Executors.newCachedThreadPool();
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
                        System.out.println("线程ID："+taskID+",执行第 "+j+" 次");
                    }
                }
            });
        }
    }

}
