/**
 * Project Name:zimu-study-thread
 * File Name:CountDownLatch.java
 * Package Name:com.zimu.thread.latch
 * Date:2017年10月13日下午4:53:40
 * Copyright (c) 2017, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.latch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName:CountDownLatch <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年10月13日 下午4:53:40 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class CountDownLatchTest {
    
    public static void main(String[] args) throws InterruptedException {
        Map map = new HashMap();
        List list = new ArrayList();
        for(int i=0;i<100;i++){
            list.add(i);
        }
        CountDownLatch latch = new CountDownLatch(list.size());
        list.parallelStream().forEach((t)->{
            System.out.println(t);
            latch.countDown();
        });
        latch.await();
        System.out.println("end...");
    }

}

