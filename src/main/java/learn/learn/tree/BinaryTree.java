package learn.learn.tree;

/**
 * Created by zhiyong.ma on 2016/3/10.
 */
public class BinaryTree {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        Node[] nodes = new Node[array.length];
        for (int i = 0; i < array.length; i++) {
            nodes[i] = new Node(array[i]);
        }
        for (int i = 0; i < array.length / 2 - 1; i++) {
            int leftIndex = i * 2 + 1;
            int rightIndex = leftIndex + 1;
            nodes[i].setLeftChild(nodes[leftIndex]);
            nodes[i].setRightChild(nodes[rightIndex]);
        }
        if (array.length % 2 == 1) {
            nodes[array.length / 2 - 1].leftChild = nodes[array.length - 1];
        }
        StringBuilder sb = new StringBuilder();
        printTree(sb, nodes[0]);

        System.out.println(sb.toString());

    }

    static int printTree(StringBuilder sb, Node node) {
        if (null == node) {
            sb.append("");
            return 0;
        }
        int leftSpace = printTree(sb, node.leftChild);
        int rightSpace = printTree(sb, node.rightChild);
        int space = leftSpace + 2;

        insertSpaces(sb, space);
        sb.insert(space, node.value).insert(space + String.valueOf(node.value).length(), "\n");

        return space;
    }

    static void insertSpaces(StringBuilder sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.insert(0, " ");
        }
    }


    static class Node {
        Object value;
        Node parent;
        Node leftChild;
        Node rightChild;

        Node(Object value) {
            this.value = value;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            leftChild.parent = this;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            rightChild.parent = this;
        }

    }
}
