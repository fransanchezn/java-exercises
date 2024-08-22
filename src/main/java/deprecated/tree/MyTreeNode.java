package deprecated.tree;

import java.util.ArrayList;
import java.util.List;

public class MyTreeNode<T> {
    T data;
    MyTreeNode parent;
    List<MyTreeNode> children;

    MyTreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    MyTreeNode(T data, MyTreeNode parent) {
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>();
    }

    public MyTreeNode<T> addChild(MyTreeNode child) {
        children.add(child);
        return child;
    }

    public List<MyTreeNode> addChildren(List<MyTreeNode> children) {
        this.children.addAll(children);
        return this.children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyTreeNode getParent() {
        return parent;
    }

    public void setParent(MyTreeNode parent) {
        this.parent = parent;
    }

    public List<MyTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MyTreeNode> children) {
        this.children = children;
    }
}
