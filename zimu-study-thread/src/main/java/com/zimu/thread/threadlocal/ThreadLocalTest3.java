package com.zimu.thread.threadlocal;

/**
 * ClassName:ThreadLocalTest2 <br/>
 * Function: 在main线程中，没有先set，直接get的话，运行时会报空指针异常。
 *           但是如果改成下面这段代码，即重写了initialValue方法. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-20 16:41 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class ThreadLocalTest3 {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

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
        final ThreadLocalTest3 test = new ThreadLocalTest3();

//        test.set();
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
