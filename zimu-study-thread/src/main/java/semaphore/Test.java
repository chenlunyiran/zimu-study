/**
 * Project Name:zimu-study-thread
 * File Name:Test.java
 * Package Name:semaphore
 * Date:2018年3月24日下午7:13:05
 * Copyright (c) 2018, ehking All Rights Reserved.
 *
*/

package semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * ClassName:Test <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年3月24日 下午7:13:05 <br/>
 * @author   jianhua.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Test {
    
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        int workerNum = 8;
        for (int i = 0; i < workerNum; i++) {
            new worker(i, semaphore).start();
        }
    }
    
    static class worker extends Thread {
        private int num;
        private Semaphore semaphore;
        public worker(int num, Semaphore semaphore) {
            super();
            this.num = num;
            this.semaphore = semaphore;
        }
        
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产...");
                Random random=new java.util.Random();
                int second = random.nextInt(10);
                Thread.sleep(second*1000);
                System.out.println("工人"+this.num+"  "+second+"s释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } 

}

