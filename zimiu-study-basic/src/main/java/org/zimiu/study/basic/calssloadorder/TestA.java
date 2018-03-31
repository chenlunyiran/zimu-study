/**
 * Project Name:zimu-study-basic
 * File Name:TestA.java
 * Package Name:org.zimiu.study.basic
 * Date:2018年1月17日上午10:31:33
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic.calssloadorder;
/**
 * ClassName:TestA <br/>
 * Function: Java中父类与子类的加载顺序. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月17日 上午10:31:33 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TestA {  
    {  
        System.out.println("hello A");  
    }  
    public TestA(){  
        System.out.println("TestA constructor!");  
    }  
    static{  
        System.out.println("TestA static");  
    }
}

