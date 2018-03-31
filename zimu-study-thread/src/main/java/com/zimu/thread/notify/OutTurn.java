/**
 * Project Name:zimu-study-thread File Name:OutTurn.java Package Name:com.zimu.thread.notify
 * Date:2018年3月25日下午7:17:06 Copyright (c) 2018, ehking All Rights Reserved.
 *
 */

package com.zimu.thread.notify;

/**
 * ClassName:OutTurn <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年3月25日 下午7:17:06 <br/>
 * 
 * @author jianhua.wang
 * @version
 * @since JDK 1.8
 * @see
 */
public class OutTurn {
    private boolean isSub = true;
    private int count = 0;

    public synchronized void sub() {
        try {
            while (!isSub) {
                this.wait();
            }
            System.out.println("sub --- " + count);
            isSub = false;
            this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public synchronized void main() {
        try {
            while (isSub) {
                this.wait();
            }
            System.out.println("main --- " + count);
            isSub = true;
            this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
}
