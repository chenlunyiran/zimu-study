package org.zimiu.study.basic.design.patterns.composite.pattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Composite
 * @Description 组合模式
 * @Date 2019/9/4 14:25
 * @Author jianhua.wang
 */
public class Composite extends Component{

    private String name;

    private String relation;

    private Boolean flag;

    private BigDecimal balance;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private List<Component> componentList;

    public Composite(String name) {
        this.name = name;
        this.componentList = new ArrayList<Component>();
    }

    public Composite(String name, String relation) {
        this.name = name;
        this.relation = relation;
        this.componentList = new ArrayList<Component>();
    }

    public Composite(String name, String relation, Boolean flag) {
        this.name = name;
        this.relation = relation;
        this.flag = flag;
    }

    @Override
    public void display(int depth) {
        StringBuffer strBuf = new StringBuffer("");
        for (int i = 0; i < depth; i++) {
            strBuf.append("--");
        }

        System.out.println(new String(strBuf) + this.getName() + this.getRelation() + this.getFlag());

        for (Component c : componentList) {
            //递归显示
            c.display(depth+2);
        }


    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        componentList.remove(component);
    }

    public Component  getChild(int i){
        return componentList.get(i);
    }

    public Boolean compare(){
        Boolean flag =true;
        for (Component c : componentList) {
            //递归运算
            c.compare();

            if(c.getFlag() ==null){
                continue;
            }
            flag = flag && c.getFlag();
//            System.out.println("===="+this.getName() + this.getRelation() + this.getFlag() ) ;
        }
        this.setFlag(flag);

        return flag;
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
