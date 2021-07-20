package com.shenzc.ThreadPool;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 调度型线程池,支持定时及周期性任务执行，也是一个固定长度的线程池。
 * ScheduledExecutorService exe= Executors.newScheduledThreadPool(3);
 */
public class ScheduledThreadPool {

    //newScheduledThreadPool的线程池大小只设置了3，所以一次只能执行3个线程，然后可以看到每2秒执行一次任务调度。
    public static void main(String[] args){
        ScheduledExecutorService exe= Executors.newScheduledThreadPool(3);
        for(int i=1;i<6;i++){
            final int taskID=i;
            exe.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程："+taskID+",时间："+ LocalDateTime.now()+" 执行一次");
                }
            }, 0, 2, TimeUnit.SECONDS);
        }
    }

}
