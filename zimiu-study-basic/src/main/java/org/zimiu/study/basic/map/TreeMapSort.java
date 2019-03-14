package org.zimiu.study.basic.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Classname TreeMapSort
 * @Description TODO
 * @Date 2019/3/14 19:03
 * @Created by jianhua.wang
 */
public class TreeMapSort {

    public static void main(String[] args) {
        TreeMapSortByKey();
        TreeMapSortByValue();
    }

    /**
     * TreeMap按照key进行排序
     */
    public static void TreeMapSortByKey() {
        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
//            @Override
            public int compare(String o1, String o2) {
                // 降序排列
                return o2.compareTo(o1);
            }
        });
        map.put("c", "ccccc");
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("d", "ddddd");
        for (String key : map.keySet()) {
            System.err.println("key:" + key + "  value:" + map.get(key));
        }
    }

    /**
     * TreeMap按照value排序
     */
    public static void TreeMapSortByValue() {
        Map<String, String> map = new TreeMap<String, String>();
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
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }
}
