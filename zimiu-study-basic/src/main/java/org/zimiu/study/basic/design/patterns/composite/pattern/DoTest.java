package org.zimiu.study.basic.design.patterns.composite.pattern;

import java.math.BigDecimal;

/**
 * @Classname DoTest
 * @Description 组合模式
 * @Date 2019/9/6 14:54
 * @Author jianhua.wang
 */
public class DoTest {
    public static void main(String[] args) {
        Composite g5 = new Composite("g5", "and");

        Composite g3 = new Composite("g3", "and");
        Leaf g4 = new Leaf("g4", "and", new BigDecimal("6"), new BigDecimal("1"), new BigDecimal("5"));
        Leaf g2 = new Leaf("g2", "and", new BigDecimal("3"), new BigDecimal("1"), new BigDecimal("5"));
        Leaf g1 = new Leaf("g1", "and", new BigDecimal("3"), new BigDecimal("1"), new BigDecimal("5"));

        g3.add(g1);
        g3.add(g2);

        g5.add(g3);
        g5.add(g4);

        g5.compare();
        g5.display(0);
    }
}
