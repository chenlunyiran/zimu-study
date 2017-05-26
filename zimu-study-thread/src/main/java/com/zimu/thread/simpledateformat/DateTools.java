package com.zimu.thread.simpledateformat;

import java.text.SimpleDateFormat;

/**
 * ClassName:DateTools <br/>
 * Function: SimpleDateFormat非线程安全-解决办法. <br/>
 * Reason:  使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。
 *          如果对性能要求比较高的情况下，一般推荐使用这种方法. <br/>
 * Date:     2017-03-21 16:51 <br/>
 *
 * @author Administrator
 * @see
 * @since JDK 1.8
 */
public class DateTools {
    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();
    public static SimpleDateFormat getSimpleDateFormat(String datePattern){
        SimpleDateFormat sdf;
        sdf = t1.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(datePattern);
        }
        return sdf;
    }
}
