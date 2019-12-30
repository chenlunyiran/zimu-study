package org.zimiu.study.basic.tree;



import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ManyTreeNode
 * @Description TODO
 * @Date 2019/9/6 10:44
 * @Author jianhua.wang
 */
public class ManyTreeNode {
    private TreeNode data;

    private List<ManyTreeNode> childList;

    public ManyTreeNode(TreeNode data) {
        this.data = data;
        this.childList = new ArrayList<ManyTreeNode>();
    }

    public ManyTreeNode(TreeNode data, List<ManyTreeNode> childList) {
        this.data = data;
        this.childList = childList;
    }

    public TreeNode getData() {
        return data;
    }

    public void setData(TreeNode data) {
        this.data = data;
    }

    public List<ManyTreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<ManyTreeNode> childList) {
        this.childList = childList;
    }


}
