package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname clothesProduct
 * @Description 桥梁模式——实现类
 * @Date 2019/12/27 11:25
 * @Author jianhua.wang
 */
public class Clothes extends Product {

    @Override
    void beProducted() {
        System.out.println("服装公司生产衣服");
    }

    @Override
    void beSelled() {
        System.out.println("服装公司出售衣服");
    }
}
