package org.zimiu.study.basic.design.patterns.composite.pattern;

import java.math.BigDecimal;

/**
 * @Classname Leaf
 * @Description 组合模式
 * @Date 2019/9/4 14:26
 * @Author jianhua.wang
 */
public class Leaf extends Component {

    private String name;

    private String relation;

    private Boolean flag;

    private BigDecimal balance;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    public Leaf(String name) {
        this.name = name;
    }

    public Leaf(String name, String relation, Boolean flag) {
        this.name = name;
        this.relation = relation;
        this.flag = flag;
    }

    public Leaf(String name, String relation, BigDecimal balance, BigDecimal minAmount, BigDecimal maxAmount) {
        this.name = name;
        this.relation = relation;
        this.balance = balance;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public Leaf(String name, String relation, Boolean flag, BigDecimal balance, BigDecimal minAmount, BigDecimal maxAmount) {
        this.name = name;
        this.relation = relation;
        this.flag = flag;
        this.balance = balance;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        System.out.println(new String(sb) + this.getName() + this.getRelation() + this.getFlag() ) ;
//        System.out.println("#####"+this.getName());
    }

    @Override
    public void add(Component component) {
        System.out.println("叶子节点不能添加构件...");
    }

    @Override
    public void remove(Component component) {
        System.out.println("叶子节点不能删除构件...");

    }

    public Component  getChild(int i){
        return null;
    }

    public Boolean compare(){
        if(this.balance.compareTo(minAmount)>=0
                && this.balance.compareTo(maxAmount)<0){
            this.flag = true;
        }else{
            this.flag = false;
        }
//        System.out.println(this.getName() + this.getRelation() + this.getFlag() ) ;
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
