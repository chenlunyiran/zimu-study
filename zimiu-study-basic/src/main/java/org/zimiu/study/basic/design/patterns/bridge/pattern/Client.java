package org.zimiu.study.basic.design.patterns.bridge.pattern;

/**
 * @Classname Client
 * @Description TODO
 * @Date 2019/12/27 11:31
 * @Author jianhua.wang
 */
public class Client {

    public static void main(String[] args) {
        House house = new House();
        HouseCompany houseCompany = new HouseCompany(house);
        houseCompany.makeMoney();

        Clothes clothes =new Clothes();
        ClothesCompany clothesCompany = new ClothesCompany(clothes);
        clothesCompany.makeMoney();

    }
}
