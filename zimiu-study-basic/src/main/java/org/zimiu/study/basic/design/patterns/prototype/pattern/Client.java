package org.zimiu.study.basic.design.patterns.prototype.pattern;

/**
 * @Classname Client
 * @Description 原型模式--浅拷贝
 * @Date 2019/11/1 11:13
 * @Author jianhua.wang
 */
public class Client {
    public static void main(String[] args) {
        //产生一个对象
        Thing thing = new Thing();
        //设置一个值
        thing.setValue("张三");
        //拷贝一个对象
        Thing cloneThing = thing.clone();
        cloneThing.setValue("李四");
        System.out.println(thing.getValue());
        System.out.println(cloneThing.getValue());
    }
}
