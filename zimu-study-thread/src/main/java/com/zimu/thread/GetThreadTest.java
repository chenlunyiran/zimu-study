/**
 * Project Name:zimu-study-thread
 * File Name:Test.java
 * Package Name:com.zimu.thread.ConcurrentHashMap
 * Date:2017年6月29日下午3:54:25
 * Copyright (c) 2017, ehking All Rights Reserved.
 *
*/

package com.zimu.thread;

import java.util.Map;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月29日 下午3:54:25 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class GetThreadTest {
    
    public static void main(String[] args) {
        /**
         * 获取当前机器cpu核数
         * */
        int num = Runtime.getRuntime().availableProcessors(); 
        System.out.println(num);
        
        /**
         * 
         * 获取所有线程堆栈，你可以通过maps中的keySet()中获取Thread对象，并调用它的interrupt方法中止。上
         * */
        Map<Thread, StackTraceElement[]> maps = Thread.getAllStackTraces();
        maps.forEach((key,value)->{
            System.out.println(key +" : "+value);
        });
    }
    
    

}

