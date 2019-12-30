package org.zimiu.study.basic.design.patterns.prototype.pattern;

import java.util.ArrayList;

/**
 * @Classname Thing
 * @Description 原型模式--深拷贝(复制的对象之间没有任何的瓜葛,不会相互影响)
 * @Date 2019/11/1 11:12
 * @Author jianhua.wang
 */
public class ThingDeep implements Cloneable {
    //定义一个私有变量
    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    public ThingDeep clone() {
        ThingDeep thing = null;
        try {
            thing = (ThingDeep) super.clone();
            thing.arrayList = (ArrayList<String>) this.arrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }

    //设置HashMap的值
    public void setValue(String value) {
        this.arrayList.add(value);
    }

    //取得arrayList的值
    public ArrayList<String> getValue() {
        return this.arrayList;
    }
}
