package exercises.tree;

public class Tree {

    private BiTreeNode<Integer> root;

    public Tree(BiTreeNode<Integer> root) {
        this.root = root;
    }

    public void add(Integer data) {
        var nextNode = root;
        BiTreeNode<Integer> parentNode;

        do {
            if (nextNode.data > data) {
                if (nextNode.left != null) {
                    nextNode = nextNode.left;
                } else {
                    nextNode.left = new BiTreeNode(data);
                    break;
                }
            } else {
                if (nextNode.right != null) {
                    nextNode = nextNode.right;
                } else {
                    nextNode.right = new BiTreeNode(data);
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

        System.out.println(t.getRoot().left.data);
        System.out.println(t.getRoot().right.data);

        System.out.println(t.getRoot().right.right.data);
    }
}
