package org.zimiu.study.basic.design.patterns.strategy.pattern;

/**
 * @Classname Client
 * @Description TODO
 * @Date 2019/12/30 14:57
 * @Author jianhua.wang
 */
public class Client {
    public static void main(String[] args) {
        Pay pay = new WechatPay();
        pay.getPayMode();
    }
}
