package org.zimiu.study.basic.design.patterns.composite.pattern;

/**
 * @Classname Component
 * @Description 组合模式——组件
 * @Date 2019/9/4 14:24
 * @Author jianhua.wang
 */
public abstract class Component {

    private String name;

    private String relation;

    private Boolean flag;

    public abstract void display(int depth);
    //添加构件
    public void add(Component component){};
    //删除构件
    public void remove(Component component){};
    //获取成员
    public abstract Component getChild(int i);
    //比较运算
    public abstract Boolean compare();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
