package com.zimu.thread.threadlocal;

/**
 * ClassName:Run <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-20 15:59 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class Run {
    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        a.start();
        b.start();
        try {
            for (int i = 0; i < 10; i++) {
                ThreadLocalTools.t1.set("Main" + (i + 1));
                System.out.println("Main get value =" + ThreadLocalTools.t1.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
