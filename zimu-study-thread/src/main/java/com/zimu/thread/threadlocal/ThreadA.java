package com.zimu.thread.threadlocal;

/**
 * ClassName:ThreadA <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-20 15:44 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                ThreadLocalTools.t1.set("ThreadA" + (i + 1));
                System.out.println("ThreadA get value = " +  ThreadLocalTools.t1.get());
                Thread.sleep(200);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
