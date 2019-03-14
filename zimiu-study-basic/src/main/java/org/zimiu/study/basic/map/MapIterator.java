package org.zimiu.study.basic.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname MapIterator
 * @Description Map遍历
 * @Date 2019/3/14 19:39
 * @Author jianhua.wang
 */
public class MapIterator {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        // 第一种：普遍使用，二次取值（效率低）
        // 这个代码看上去更加干净；但实际上它相当慢且无效率。因为从键取值是耗时的操作（与方法三相比，在不同的Map实现中该方法慢了20%~200%）。
        // 如果你安装了FindBugs，它会做出检查并警告你关于哪些是低效率的遍历。所以尽量避免使用。
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        // 第二种
        // 该种方式看起来冗余却有其优点所在,你可以在遍历时调用iterator.remove()来删除entries.
        // 根据javadoc的说明，如果在for-each遍历中尝试使用此方法，结果是不可预测的
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        // 第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

    }
}
