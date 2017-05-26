package com.zimu.thread.threadlocal;

/**
 * ClassName:ThreadLocalTest2 <br/>
 * Function: 明通过ThreadLocal能达到在每个线程中创建变量副本的效果. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-20 16:41 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class ThreadLocalTest2 {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest2 test = new ThreadLocalTest2();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
