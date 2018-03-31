/**
 * Project Name:zimu-study-thread
 * File Name:Test.java
 * Package Name:com.zimu.thread.threadPool
 * Date:2018年3月26日下午6:22:52
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.threadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月26日 下午6:22:52 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test implements Callable<Object>{
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Map map = new HashMap();
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            
        
        Test test = new Test();
        FutureTask<Object> futureTask = new FutureTask<Object>(test);
        try {
            Future<Object>  future = (Future<Object>) executorService.submit(futureTask);
            System.out.println("isDone:"+future.isDone());
            String result = future.get().toString();
            System.out.println("result:"+result);
            System.out.println("isDone:"+future.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally{
            executorService.shutdown();
        }
        
        }
    }
    
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" Callable run...");
        Thread.sleep(3000);
        return "aha";
    }
}

