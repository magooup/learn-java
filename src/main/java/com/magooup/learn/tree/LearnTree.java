package com.magooup.learn.tree;

/**
 * Created by zhiyong.ma on 2016/3/10.
 */
public class LearnTree {

    public static void main(String[] args) {
        binaryTree();
    }


    static void binaryTree() {
        BinaryNode node = createBinaryTree();
        printBinaryTree(node);
    }

    static void printBinaryTree(BinaryNode node) {
        if (null == node) {
            return;
        }
        printBinaryTree(node.leftChild);
        System.out.println(node.value);
        printBinaryTree(node.rightChild);
    }

    static BinaryNode createBinaryTree() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        BinaryNode[] nodes = new BinaryNode[array.length];
        for (int i = 0; i < array.length; i++) {
            nodes[i] = new BinaryNode(array[i]);
        }
        for (int i = 0; i < array.length / 2; i++) {
            int leftIndex = i * 2 + 1;
            int rightIndex = leftIndex + 1;
            nodes[i].leftChild = nodes[leftIndex];
            if (rightIndex < array.length) {
                nodes[i].rightChild = nodes[rightIndex];
            }
        }
        return nodes[0];
    }

    static class BinaryNode {
        Object value;
        BinaryNode leftChild;
        BinaryNode rightChild;

        BinaryNode(Object value) {
            this.value = value;
        }

    }
}
