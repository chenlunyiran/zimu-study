package org.zimiu.study.basic.calssloadorder;

/**
 * @Classname TestInit
 * @Description TODO
 * @Date 2019/5/25 16:31
 * @Author jianhua.wang
 */
public class TestInit {

    private String globalSt = "";
    //类加载时为成员变量(static修饰)初始化为null
    private static String globalStr;
    //final修饰的成员变量初始化为程序赋值的值,必须初始赋值
    private final static String globalStr2 = "abc";
    private final static String globalStr3 = null;

    public void MethodInit(){
        //类加载时为局部变量初始化为程序赋值的值,必须初始赋值
        String methodStr = "def";
        System.out.println(methodStr);
    }

    public static void main(String[] args) {
        TestInit t = new TestInit();
        System.out.println(t.globalSt);
        System.out.println(globalStr);
        System.out.println(globalStr2);
        System.out.println(globalStr3);
        t.MethodInit();
    }

}
