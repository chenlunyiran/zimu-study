/**
 * Project Name:zimu-study-thread
 * File Name:ThreadContext.java
 * Package Name:com.zimu.thread.threadlocal
 * Date:2017年7月4日下午2:00:03
 * Copyright (c) 2017, ehking All Rights Reserved.
 *
*/

package com.zimu.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:ThreadContext <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年7月4日 下午2:00:03 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ThreadContext {

private Map<Object,Object> data = new HashMap<Object,Object>();
    
    public void put(Object key, Object message){
        data.put(key, message);
    }
    
    public Object get(Object key){
        return data.get(key);
    }
    
    public <T> T get(Object key,Class<T> clazz){
        return (T) data.get(key);
    }
    
    /**
     * 获取线程绑定对象上下文
     * @return
     */
    public static ThreadContext getContext(){
        return threadContext.get();
    }
    
    /**
     * 从线程对象中清空请求参数
     */
    public static void clearContext(){
        threadContext.remove();
    }

    /**
     * 线程绑定数据
     */
    private static ThreadLocal<ThreadContext> threadContext = new ThreadLocal<ThreadContext>() {
        protected ThreadContext initialValue() {
            return new ThreadContext();
        }
    };
}

