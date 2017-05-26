package com.zimu.thread.threadlocal;

/**
 * ClassName:ThreadLocalTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-20 16:06 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class ThreadLocalTest {
    private static ThreadLocal t = new ThreadLocal();
    public static void main(String[] args) {
        t.set("a");
        t.set("b");
        System.out.println(t.get());
    }
}
