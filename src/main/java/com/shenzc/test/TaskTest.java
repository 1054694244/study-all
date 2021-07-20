package com.shenzc.test;


import java.util.concurrent.Callable;

public class TaskTest implements Callable {

    private Integer i;

    public TaskTest(int i){
        this.i = i;
    }

    public Object call() throws Exception {
        Thread.sleep(1000);
        System.out.println("假装有一秒的逻辑");
        return i * i;
    }
}
