package hackerrank.tree;

public class Tree {

    private BiTreeNode<Integer> root;

    public Tree(BiTreeNode<Integer> root) {
        this.root = root;
    }

    public void add(Integer data) {
        var nextNode = root;
        BiTreeNode<Integer> parentNode;

        do {
            if (nextNode.getData() > data) {
                if (nextNode.getLeft() != null) {
                    nextNode = nextNode.getLeft();
                } else {
                    nextNode.setLeft(new BiTreeNode(data));
                    break;
                }
            } else {
                if (nextNode.getRight() != null) {
                    nextNode = nextNode.getRight();
                } else {
                    nextNode.setRight(new BiTreeNode(data));
                    break;
                }
            }
        } while(nextNode != null);
    }

    public BiTreeNode<Integer> getRoot() {
        return root;
    }

    public static void main(String[] args) {
        Tree t = new Tree(new BiTreeNode<>(5));
        t.add(2);
        t.add(6);

        System.out.println(t.getRoot().getLeft().getData());
        System.out.println(t.getRoot().getRight().getData());

        System.out.println(t.getRoot().getRight().getRight().getData());
    }
}
