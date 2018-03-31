/**
 * Project Name:zimu-study-thread
 * File Name:NotifyDeadLockDemo.java
 * Package Name:com.zimu.thread.notify
 * Date:2018年3月25日下午7:17:42
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.notify;
/**
 * ClassName:NotifyDeadLockDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 notify方法很容易引起死锁，除非你根据自己的程序设计，确定不会发生死锁，notifyAll方法则是线程的安全唤醒方法. <br/>
 * Date:     2018年3月25日 下午7:17:42 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class NotifyDeadLockDemo {
    
    public static void main(String[] args){
        final OutTurn outTurn = new OutTurn();
        for (int i = 0; i < 100; i++)
        {
            new Thread(new Runnable()
            {
                
                @Override
                public void run()
                {
                    for (int j = 0; j < 5; j++)
                    {
                        outTurn.sub();
                    }
                    
                }
            }).start();
            
            new Thread(new Runnable()
            {
                
                @Override
                public void run()
                {
                    for (int j = 0; j < 5; j++)
                    {
                        outTurn.main();
                    }
                    
                }
            }).start();
        }
    }    
}


//OutTurn类中的sub和main方法都是同步方法，所以多个调用sub和main方法的线程都会处于阻塞状态，等待一个正在运行的线程来唤醒它们。
//下面分别分析一下使用notify和notifyAll方法唤醒线程的不同之处：
//上面的代码使用了notify方法进行唤醒，而notify方法只能唤醒一个线程，其它等待的线程仍然处于wait状态，
//假设调用sub方法的线程执行完后（即System. out .println("sub ---- " + count )执行完之后），所有的线程都处于等待状态，此时在sub方法中的线程执行了isSub=false语句后又执行了notify方法，
//这时如果唤醒的是一个sub方法的调度线程，那么while循环等于true，则此唤醒的线程也会处于等待状态，此时所有的线程都处于等待状态，那么也就没有了运行的线程来唤醒它们，这就发生了死锁。
//如果使用notifyAll方法来唤醒所有正在等待该锁的线程，那么所有的线程都会处于运行前的准备状态（就是sub方法执行完后，唤醒了所有等待该锁的状态,注：不是wait状态），那么此时，即使再次唤醒一个sub方法调度线程，while循环等于true，唤醒的线程再次处于等待状态，那么还会有其它的线程可以获得锁，进入运行状态。
