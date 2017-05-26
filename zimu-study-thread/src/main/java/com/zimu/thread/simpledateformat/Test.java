package com.zimu.thread.simpledateformat;

import java.text.SimpleDateFormat;

/**
 * ClassName:Test <br/>
 * Function: SimpleDateFormat非线程安全 测试. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-03-21 16:25 <br/>
 *
 * @author Administrator
 * @see
 * @since JDK 1.8
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] DateStringArray = new String[]{"2017-03-21", "2017-03-22", "2017-03-23", "2017-03-24", "2017-03-25"};
        Thread[] threadArray = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threadArray[i] = new MyThread(sdf, DateStringArray[i]);
        }
        for (int i = 0; i < 5; i++) {
            threadArray[i].start();
        }
    }
}
