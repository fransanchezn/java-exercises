package hackerrank.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    T data;
    TreeNode parent;
    List<TreeNode> children;

    TreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    TreeNode(T data, TreeNode parent) {
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public TreeNode<T> addChild(TreeNode child) {
        children.add(child);
        return child;
    }

    public List<TreeNode> addChildren(List<TreeNode> children) {
        this.children.addAll(children);
        return this.children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
