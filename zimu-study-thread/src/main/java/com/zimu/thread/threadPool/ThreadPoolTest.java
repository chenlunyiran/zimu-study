/**
 * Project Name:zimu-study-thread
 * File Name:ThreadPoolTest.java
 * Package Name:com.zimu.thread.threadPool
 * Date:2018年3月26日下午6:30:37
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.threadPool;
/**
 * ClassName:ThreadPoolTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月26日 下午6:30:37 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ThreadPoolTest implements Runnable{
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

