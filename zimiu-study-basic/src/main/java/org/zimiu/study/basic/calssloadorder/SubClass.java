/**
 * Project Name:zimu-study-basic
 * File Name:SubClass.java
 * Package Name:org.zimiu.study.basic
 * Date:2018年1月17日上午10:42:51
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic.calssloadorder;
/**
 * ClassName:SubClass <br/>
 * Function: Java中父类与子类的加载顺序. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月17日 上午10:42:51 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SubClass extends Parent {
    // 静态变量
    public static String s_StaticField = "子类--静态变量";
    // 变量
    public String s_Field = "子类--变量";
    // 静态初始化块
    static {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }
    // 构造器
    public SubClass() {
        //super();
        System.out.println("子类--构造器");
    }
    // 程序入口
    public static void main(String[] args) {
        System.out.println("*************in main***************");
        new SubClass();
        System.out.println("*************second subClass***************");
        new SubClass();
    }
    
    /*
     * 结果分析：
                        很显然在加载main方法后，静态变量不管父类还是子类的都执行了，然后才是父类和子类的的普通变量和构造器。
                        这是因为，当要创建子类这个对象时，发现这个类需要一个父类，所以把父类的.class加载进来，然后依次初始化其普通变量和初始化代码块，最后其构造器，然后可以开始子类的工作，把子类的.class加载进来，在做子类的工作。
                        另外在Java中子类中都会有默认的调用父类的默认构造函数即super(),当仅仅有默认构造函数里
       Java替你做了，我们可以做个实验，如果在父类中注释掉默认构造函数，加一个有参的构造函数时，如果
                        子类中不加super(argument),此时会报语法错误
                        如果我们把Main方法中的内容全注释掉，你会发现只会输出

    　　　　    父类--静态变量
    　　　　    父类--静态初始化块
    　　　　    子类--静态变量
                        子类--静态初始化块
      */
}
