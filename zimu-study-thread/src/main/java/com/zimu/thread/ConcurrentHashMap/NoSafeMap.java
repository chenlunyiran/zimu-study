/**
 * Project Name:zimu-study-thread
 * File Name:HashMap.java
 * Package Name:com.zimu.thread.ConcurrentHashMap
 * Date:2017年6月29日下午2:48:07
 * Copyright (c) 2017, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.ConcurrentHashMap;

import java.util.HashMap;
import java.util.UUID;

/**
 * ClassName:HashMap <br/>
 * Function: 因为多线程环境下，使用HashMap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年6月29日 下午2:48:07 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class NoSafeMap extends Thread{
    public static void main(String[] args) {
        
        final HashMap<String, String> map = new HashMap<String, String>(2);
        try {
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    new Thread(new Runnable() {
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                            System.out.println(System.currentTimeMillis());
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        
            t.join();
        } catch (InterruptedException e) {
            
            e.printStackTrace();
            
        }
        
        
        map.forEach((key,value) -> System.out.println(key +" : "+value));  
    }

}

