package com.zimu.thread.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:MyThread <br/>
 * Function: SimpleDateFormat非线程安全. <br/>
 * Reason:  SimpleDateFormat类是线程不安全的,多线程下转化的日期有错误的情况. <br/>
 * Date:     2017-03-21 16:02 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class MyThread extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        super();
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {

        try {
            Date dateRef = sdf.parse(dateString);
            String newDateString = sdf.format(dateRef).toString();
            if (!newDateString.equals(dateString)) {
                System.out.println("ThreadName:" + this.getName()
                        + "报错了  日期字符串:" + dateString + " 转化后的日期字符串:" + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
