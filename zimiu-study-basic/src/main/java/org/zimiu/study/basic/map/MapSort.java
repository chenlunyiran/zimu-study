package org.zimiu.study.basic.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MapSort
 * @Description TODO
 * @Date 2019/3/14 19:09
 * @Created by jianhua.wang
 */
public class MapSort {

    public static void main(String[] args) {
        MapSort.MapSortByKey();
        MapSort.MapSortByValue();
    }
    /**
     * Map按照Key排序
     */
    public static void MapSortByKey() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("d", "ccccc");
        map.put("b", "bbbbb");
        map.put("a", "eeeee");
        map.put("c", "ddddd");
        // 将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 升序排序
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }

    /**
     * Map按照Value排序
     */
    public static void MapSortByValue() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("d", "ccccc");
        map.put("b", "bbbbb");
        map.put("a", "eeeee");
        map.put("c", "ddddd");
        // 将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, String> mapping : list) {
            System.out.println("key:"+mapping.getKey() + "  value:" + mapping.getValue());
        }
    }
}
