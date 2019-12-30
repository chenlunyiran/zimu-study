package org.zimiu.study.basic.design.patterns.prototype.pattern;

import java.util.ArrayList;

/**
 * @Classname Thing
 * @Description 原型模式--浅拷贝
 * @Date 2019/11/1 11:12
 * @Author jianhua.wang
 */
public class Thing implements Cloneable {
    //定义一个私有变量
    private ArrayList<String> arrayList = new ArrayList<String>();
    @Override
    public Thing clone(){
        Thing thing=null;
        try {
            thing = (Thing)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return thing;
    }
    //设置HashMap的值
    public void setValue(String value){
        this.arrayList.add(value);
    }
    //取得arrayList的值
    public ArrayList<String> getValue(){
        return this.arrayList;
    }
}
