package tree;

import java.util.Objects;

public class BiTreeNode<T> {
    T data;
    BiTreeNode right;
    BiTreeNode left;

    BiTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BiTreeNode getRight() {
        return right;
    }

    public void setRight(BiTreeNode right) {
        this.right = right;
    }

    public BiTreeNode getLeft() {
        return left;
    }

    public void setLeft(BiTreeNode left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiTreeNode<?> that = (BiTreeNode<?>) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(right, that.right) &&
                Objects.equals(left, that.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, right, left);
    }
}
