package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname House
 * @Description 桥梁模式——实现类
 * @Date 2019/12/27 11:27
 * @Author jianhua.wang
 */
public class House extends Product {
    @Override
    void beProducted() {
        System.out.println("房地产公司生产房子");
    }

    @Override
    void beSelled() {
        System.out.println("房地产公司出售房子");
    }
}
