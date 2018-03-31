/**
 * Project Name:zimu-study-basic
 * File Name:TestB.java
 * Package Name:org.zimiu.study.basic
 * Date:2018年1月17日上午10:34:19
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic.calssloadorder;
/**
 * ClassName:TestB <br/>
 * Function: Java中父类与子类的加载顺序. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月17日 上午10:34:19 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TestB extends TestA{  
    {  
        System.out.println("hello B");  
    }  
    public TestB(){  
        System.out.println("TestB constructor!");  
    }  
    static{  
        System.out.println("TestB static");  
    }
    public static void main(String[] args) {
        TestB b=new TestB();
    }
}  
