package org.zimiu.study.basic.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MapTest
 * @Description TODO
 * @Date 2019/5/16 14:33
 * @Author jianhua.wang
 */
public class MapTest {
    public static void main(String[] args) {
        // initialCapacity = (需要存储的元素个数/负载因子)+1
        Map map = new HashMap(2);
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        System.out.println(map);
    }
}
