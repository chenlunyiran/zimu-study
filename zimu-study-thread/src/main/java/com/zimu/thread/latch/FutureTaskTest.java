package com.zimu.thread.latch;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ClassName:FutureTaskTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:  TODO ADD REASON. <br/>
 * Date:     2017-05-23 10:48 <br/>
 *
 * @author jianhua.wang
 * @see
 * @since JDK 1.8
 */
public class FutureTaskTest {
    private final FutureTask<ProductInfo> future =
        new FutureTask<ProductInfo>(new Callable<ProductInfo>(){
            public  ProductInfo call () throws  InterruptedException{
                Thread t  = new Thread(future);
                t.sleep(5000);
                return new ProductInfo("apple","2");
            }
    });

    private Thread thread  = new Thread(future);
    public void start (){thread.start();}
    public  ProductInfo get (){
        ProductInfo p = null;
        try {
            p =  future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  p;
    }

    public boolean isDone(){
        return future.isDone();
    }

//    public  static  RuntimeException launderThrowable(Throwable t){
//        if(t instanceof RuntimeException){
//            return  (RuntimeException)t;
//        }else if (t instanceof  Error){
//            throw (Error)t;
//        }else{
//            throw  new IllegalArgumentException("not unchecked",t);
//        }
//    }

    public static void main(String[] args) {
        FutureTaskTest t = new FutureTaskTest();
        t.start();
        boolean d = t.isDone();
        ProductInfo p =t.get();
        System.out.println("isDone:"+d);
        System.out.println("ProductInfo:"+p.getName());
        d = t.isDone();
        System.out.println("isDone:"+d);
    }
}
