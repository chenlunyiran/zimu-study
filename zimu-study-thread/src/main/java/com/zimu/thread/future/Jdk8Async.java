package com.zimu.thread.future;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

/**
 * @Classname Jdk8Async
 * @Description TODO
 * @Date 2020/5/12 18:06
 * @Author jianhua.wang
 */
public class Jdk8Async {

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> System.out.println("Future Async===" + Thread.currentThread()));
        CompletableFuture.runAsync(() -> test());
        System.out.println("main===" + Thread.currentThread());


    }

    public static void test() {
        System.out.println("Future Async test===" + Thread.currentThread());
    }
}
