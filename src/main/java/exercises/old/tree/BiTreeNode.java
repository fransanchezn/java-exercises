package exercises.old.tree;

import java.util.Objects;

public class BiTreeNode<T> {
    public T data;
    public BiTreeNode<T> right;
    public BiTreeNode<T> left;

    BiTreeNode(T data) {
        this.data = data;
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
