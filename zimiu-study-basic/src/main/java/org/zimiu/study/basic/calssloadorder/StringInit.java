package org.zimiu.study.basic.calssloadorder;

/**
 * @Classname StringInit
 * @Description TODO
 * @Date 2019/5/25 16:47
 * @Author jianhua.wang
 */
public class StringInit {

    //字符串拼接规则:
    //1.直接定义字符串变量的时候赋值，如果表达式右边只有字符串常量，那么就是把变量存放在常量池里面。
    //2.new出来的字符串是存放在堆里面。
    //3.对字符串进行拼接操作，也就是做"+"运算的时候，分2中情况：
    //　　i.表达式(=)右边是纯字符串常量，那么存放在栈里面。
    //　　ii.表达式(=)右边如果存在字符串引用，也就是字符串对象的句柄，那么就存放在堆里面。

    //intern用来返回常量池中的某字符串，如果常量池中已经存在该字符串，则直接返回常量池中该对象的引用。
    // 否则，在常量池中加入该对象，然后 返回引用
    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        String str6 = str1 + "bbb";
        String str7 = "aaa" + str2;
        System.out.println(str3 == str4); // false
        System.out.println(str3 == str4.intern()); // true
        System.out.println(str3 == str5);// true
        System.out.println(str3 == str6);// true
        System.out.println(str3 == str7);// false
    }

    //结果：str1、str2、str3、str5都是存在于常量池，str4由于表达式右半边有引用类型，
    // 所以str4存在于堆内存，而str5表达式右边没有引用类型，是纯字符串常量，就存放在了常量池里面。
    // 其实Integer这种包装类型的-128 ~ +127也是存放在常量池里面，
    // 比如Integer i1 = 10;Integer i2 = 10; i1 == i2结果是true，估计也是为了性能优化。
}
