/**
 * Project Name:zimu-study-thread
 * File Name:Test.java
 * Package Name:com.zimu.thread
 * Date:2018年3月25日下午7:52:47
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread;
/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月25日 下午7:52:47 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test extends Thread{
    
    @Override
    public void run() { 
        
        System.out.println(Thread.currentThread().getName()+"run ...");
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName()+"run ...");
                
            }
        });
        
        Thread t2 = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName()+"run ...");
                
            }
        });
        Thread t3 = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName()+"run ...");
                
            }
        });
        
        
        try {
            t1.start();
            t1.run();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        }
    }

}

