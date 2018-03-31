/**
 * Project Name:zimu-study-basic
 * File Name:singleton.java
 * Package Name:org.zimiu.study.basic
 * Date:2018年1月30日下午5:06:04
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic.singleton;
/**
 * ClassName:singleton <br/>
 * Function: 线程不安全. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月30日 下午5:06:04 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {
        
    }
    
    public static Singleton getSingleton(){
        if(singleton == null){
            return new Singleton();
        }
        return singleton;
    }
    
}

