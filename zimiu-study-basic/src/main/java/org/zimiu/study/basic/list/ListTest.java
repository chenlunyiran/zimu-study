package org.zimiu.study.basic.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Classname ListTest
 * @Description TODO
 * @Date 2019/11/23 14:23
 * @Author jianhua.wang
 */
public class ListTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.subList(0,2));

    }
}
