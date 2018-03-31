/**
 * Project Name:zimu-study-thread
 * File Name:Test2.java
 * Package Name:com.zimu.thread.threadPool
 * Date:2018年3月26日下午6:30:03
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:Test2 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月26日 下午6:30:03 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test2 {
    public static void main(String[] args)
    {
        LinkedBlockingQueue<Runnable> queue =
            new LinkedBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue);
        for (int i = 0; i < 16 ; i++)
        {
            threadPool.execute(
                new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0)
            {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }
}

