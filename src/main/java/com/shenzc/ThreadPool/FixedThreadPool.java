package com.shenzc.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定长线程池，可控制线程最大并发数。如果当前需要执行的任务超过池大小，那么多出的任务处于等待状态，直到有空闲下来的线程执行任务。
 * 如果当前需要执行的任务小于池大小，空闲的线程也不会去销毁。
 * ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
 */

/**
 * 总结
 * 1.重用：fixedThreadPool与cacheThreadPool差不多，也是能reuse就用，但不能随时建新的线程
 * 2.固定数目：其独特之处在于，任意时间点，最多只能有固定数目的活动线程存在，此时如果有新的线程要建立，只能放在另外的队列中等待，直到当前的线程中某个线程终止直接被移出池子
 * 3.超时：和cacheThreadPool不同，FixedThreadPool没有IDLE机制（可能也有，但既然文档没提，肯定非常长，类似依赖上层的TCP或UDP IDLE机制之类的），
 * 4,使用场景：所以FixedThreadPool多数针对一些很稳定很固定的正规并发线程，多用于服务器
 */
public class FixedThreadPool {

    //创建了一个固定大小的线程池，容量为3，然后循环执行了5个任务。由输出结果可以看到，前3个任务首先执行完，
    // 然后空闲下来的线程去执行第4,5个任务。
    public static void main(String[] args){
        ExecutorService exe= Executors.newFixedThreadPool(3);
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
