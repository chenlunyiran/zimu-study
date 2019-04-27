/**
 * Project Name:zimu-study-basic
 * File Name:Test.java
 * Package Name:org.zimiu.study.basic
 * Date:2018年1月17日上午10:52:44
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic;

import java.util.Map;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月17日 上午10:52:44 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test {
    
    public static void main(String[] args) {
        Integer a =127;
        Integer b =127;
        Integer c =new Integer(127);
        Integer d =new Integer(127);
        Integer e =128;
        Integer f =128;
        System.out.println(a==b);
        System.out.println(c==d);
        System.out.println(b==c);
        System.out.println(e==f);//Integer e = 128,在此涉及了装箱过程，即为Integer e = Integer.valueOf(128),所以就不等了。

        System.out.println(Math.round(-1.5));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE+1);

    }
    
    /*public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }*/


}

