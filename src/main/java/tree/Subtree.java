package tree;

public class Subtree {

    public static void main(String[] args) {
        // T
        BiTreeNode<Integer> s = new BiTreeNode<>(3);
        BiTreeNode<Integer> node1 = new BiTreeNode<>(4);
        BiTreeNode<Integer> node2 = new BiTreeNode<>(5);
        s.setLeft(node1);
        s.setRight(node2);
        BiTreeNode<Integer> node3 = new BiTreeNode<>(1);
        BiTreeNode<Integer> node4 = new BiTreeNode<>(2);
        BiTreeNode<Integer> node5 = new BiTreeNode<>(0);
        node4.setLeft(node5);
        node1.setLeft(node3);
        node1.setRight(node4);

        // S
        BiTreeNode<Integer> t = new BiTreeNode<>(4);
        BiTreeNode<Integer> s1 = new BiTreeNode<>(1);
        BiTreeNode<Integer> s2 = new BiTreeNode<>(2);
        t.setLeft(s1);
        t.setRight(s2);

        System.out.println(isSubTree(s, t));
    }

    static <T> boolean isSubTree(BiTreeNode<T> s, BiTreeNode<T> t) {
        if (s == null) {
            return false;
        }

        if (s.equals(t)) {
            return true;
        }

        return isSubTree(s.getLeft(), t) || isSubTree(s.getRight(), t);
    }
}
