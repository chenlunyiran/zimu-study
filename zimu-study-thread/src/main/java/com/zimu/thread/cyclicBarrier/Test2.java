/**
 * Project Name:zimu-study-thread
 * File Name:Test.java
 * Package Name:com.zimu.thread.cyclicBarrier
 * Date:2018年3月24日下午6:25:46
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	   所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数. <br/>
 * Date:     2018年3月24日 下午6:25:46 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test2 {
    
    public static void main(String[] args) {
        int n=4;
        CyclicBarrier barrier = new CyclicBarrier(n,new Runnable(){
            @Override
            public void run() {
                System.out.println("当前线程"+Thread.currentThread().getName());                   
            }
        });
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();;
        }
    }
    
    static class Writer extends Thread{
        private  CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        
        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Random random=new java.util.Random();
                int second = random.nextInt(10);
                Thread.sleep(second*1000); //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕"+second+"s，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
        
    }

}

