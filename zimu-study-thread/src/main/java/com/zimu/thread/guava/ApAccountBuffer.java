/**
 * Project Name:zimu-study-thread
 * File Name:ApAccountBuffer.java
 * Package Name:com.zimu.thread.guava
 * Date:2017年6月29日下午3:18:36
 * Copyright (c) 2017, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * ClassName:ApAccountBuffer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月29日 下午3:18:36 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ApAccountBuffer {
    
    private static final Cache<String,LinkedBlockingQueue<ApAccount>> 
    CACHE=CacheBuilder.newBuilder().
    expireAfterAccess(5, TimeUnit.MINUTES).build();//有效时长5分钟(缓存内没有操作后)
    
    private static final String CACHE_NAME="cache";
    private static final Callable<LinkedBlockingQueue<ApAccount>> CACHE_PRODUCER=LinkedBlockingQueue<ApAccount>::new;
    
    /*
     * 缓存开始前清空缓存
     * */
    public static void start(long adminLogId) throws ExecutionException{
        get(adminLogId).clear();
    }
    
    /*
     * 缓存结束后清空缓存
     * */
    public static void end(long adminLogId) throws ExecutionException{
        get(adminLogId).clear();
    }
    
    /*
     * 获取指定数据
     * */
    private static LinkedBlockingQueue<ApAccount> get(long adminLogId) throws ExecutionException{
        return CACHE.get(CACHE_NAME+":"+adminLogId, CACHE_PRODUCER);
    }
    
    public static boolean isEmpty(long adminLogId) throws ExecutionException{
        return get(adminLogId).isEmpty();
    }
    
    public static boolean isNotEmpty(long adminLogId) throws ExecutionException{
        return !isEmpty(adminLogId);
    }
    
    /*
     * 存数据到缓存
     * */
    public static void push(ApAccount account,long adminLogId) throws ExecutionException{
        get(adminLogId).offer(account);
    }
    
    /*
     * 获取缓存中的数据并删除
     * */
    public static ApAccount pop(long adminLogId) throws ExecutionException{
        return get(adminLogId).poll();
    }
    
    /*
     * 遍历缓存中的数据
     * */
    public static void each (Consumer<ApAccount> action,long adminLogId) throws ExecutionException{
        get(adminLogId).forEach(action);
    }
    
    /*
     * 遍历缓存中的数据并清空缓存,串行执行
     * */
    public static void eachThenEnd(Consumer<ApAccount> action,long adminLogId) throws ExecutionException{
        try{
            get(adminLogId).forEach(action);
        }finally{
            end(adminLogId);
        }
    }
    
    /*
     * 遍历缓存中的数据,并行执行
     * */
    public static void parallelEach(Consumer<ApAccount> action,long adminLogId) throws ExecutionException,InterruptedException{
        CountDownLatch latch=new CountDownLatch(bufferSize(adminLogId));
        get(adminLogId).parallelStream().forEach((t)->{
            action.accept(t);
            latch.countDown();
        });
        latch.await();
    }
    
    /*
     * 遍历缓存中的数据并清空缓存,并行执行
     * */
    public static void parallelEachThenEnd(Consumer<ApAccount> action,long adminLogId) throws ExecutionException,InterruptedException{
        try{
            parallelEach(action,adminLogId);
        }finally{
            end(adminLogId);
        }
    }
    
    /*
     * 缓存长度
     * */
    public static int bufferSize(long adminLogId) throws ExecutionException{
        return get(adminLogId).size();
    }
    
}

