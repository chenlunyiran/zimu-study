/**
 * Project Name:zimu-study-thread
 * File Name:Test1.java
 * Package Name:com.zimu.thread
 * Date:2018年3月25日下午8:45:05
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread;
/**
 * ClassName:Test1 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月25日 下午8:45:05 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test1 implements Runnable{
    public Test1() {
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"   run...");
    }
    public static void main(String[] args) {
        Thread t = new Thread(new CopyOfTest());
        
        try {
            t.start();
            t.join();
//            Thread.sleep(1000);
            t.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

