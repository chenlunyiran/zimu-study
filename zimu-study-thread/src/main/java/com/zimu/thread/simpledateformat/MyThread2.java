package com.zimu.thread.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:MyThread <br/>
 * Function: SimpleDateFormat非线程安全-解决办法. <br/>
 * Reason:  . <br/>
 * Date:     2017-03-21 16:02 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class MyThread2 extends Thread {
    private String dateString;

    public MyThread2(String dateString) {
        super();
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = DateTools.getSimpleDateFormat("yyyy-MM-dd");
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
