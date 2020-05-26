package com.zimu.study.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        AtomicInteger i1 = new AtomicInteger(1);
        AtomicInteger i2 = new AtomicInteger(1);
        AtomicInteger i3 = new AtomicInteger(1);
        System.out.println(list);
        for (int i = 0; i < 3000; i++) {
            Collections.shuffle(list);
            if (list.get(0) == 1) {
                i1.getAndIncrement();
            }
            if (list.get(0) == 2) {
                i2.getAndIncrement();
            }
            if (list.get(0) == 3) {
                i3.getAndIncrement();
            }
        }
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
    }
}
