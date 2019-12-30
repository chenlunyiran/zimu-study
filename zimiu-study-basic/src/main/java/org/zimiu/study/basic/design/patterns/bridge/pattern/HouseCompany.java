package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname HouseCompany
 * @Description 桥梁模式——实现类
 * @Date 2019/12/27 11:20
 * @Author jianhua.wang
 */
public class HouseCompany extends Company{

    public HouseCompany(Product product) {
        super(product);
    }

    @Override
    void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚钱了！！！");
    }
}
