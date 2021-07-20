package com.shenzc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPoolTest {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Integer result = 0 ;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<FutureTask<Integer>> futureTasks = new ArrayList();

        for (int i = 1 ; i <= 10 ; i++){
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new TaskTest(i));
            executorService.submit(futureTask);
            futureTasks.add(futureTask);
        }

        for (FutureTask<Integer> futureTask : futureTasks){
            try {
                result += futureTask.get();
            }catch (Exception e){
                System.out.println(e.getMessage()+e);
            }
        }
        System.out.println(result);
        System.out.println("执行了"+(System.currentTimeMillis()-start)+"毫秒");
    }

}
