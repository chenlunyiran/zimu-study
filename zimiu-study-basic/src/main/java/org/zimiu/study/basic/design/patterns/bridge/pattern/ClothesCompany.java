package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname ClothesCompany
 * @Description 桥梁模式——实现类
 * @Date 2019/12/27 11:41
 * @Author jianhua.wang
 */
public class ClothesCompany extends Company {
    public ClothesCompany(Product product) {
        super(product);
    }

    @Override
    public void makeMoney(){
        super.makeMoney();
        System.out.println("服装公司赚钱啦！！！");
    }
}
