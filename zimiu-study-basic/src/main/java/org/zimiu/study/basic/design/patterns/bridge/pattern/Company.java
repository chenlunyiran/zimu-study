package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname Company
 * @Description 桥梁模式——抽象类
 * @Date 2019/12/27 11:16
 * @Author jianhua.wang
 */
public abstract class Company {

    private Product product;

    public Company(Product product) {
        this.product = product;
    }

    void makeMoney(){
        product.beProducted();
        product.beSelled();
    }
}
