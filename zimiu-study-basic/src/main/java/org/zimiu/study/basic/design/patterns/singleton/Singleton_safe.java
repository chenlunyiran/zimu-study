/**
 * Project Name:zimu-study-basic
 * File Name:Singleton_safe.java
 * Package Name:org.zimiu.study.basic.design.patterns.singleton
 * Date:2018年1月30日下午5:58:53
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package org.zimiu.study.basic.design.patterns.singleton;
/**
 * ClassName:Singleton_safe <br/>
 * Function: 双重检查锁   线程安全. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年1月30日 下午5:58:53 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Singleton_safe {
    private static volatile Singleton_safe singletonSafe = null;
    private Singleton_safe(){
        
    }
    public static Singleton_safe getSingletonSafe(){
        if(singletonSafe == null){
            synchronized (Singleton_safe.class) {
                if(singletonSafe == null){
                    return new Singleton_safe();
                }
            }
            
        }
        return singletonSafe;
    }
    
    //这种写法被称为“双重检查锁”，顾名思义，就是在getSingleton()方法中，进行两次null检查。
    //看似多此一举，但实际上却极大提升了并发度，进而提升了性能。为什么可以提高并发度呢？就像上文说的，在单例中new的情况非常少，绝大多数都是可以并行的读操作。
    //因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了
    
    
    
    /*public class Singleton {
        private static volatile Singleton singleton = null;
      
        private Singleton(){}
      
        public static Singleton getSingleton(){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
            return singleton;
        }    
    }*/
    
  //虽然上面这种写法是可以正确运行的，但是其效率低下，还是无法实际应用。因为每次调用getSingleton()方法，都必须在synchronized这里进行排队，而真正遇到需要new的情况是非常少的。
}

