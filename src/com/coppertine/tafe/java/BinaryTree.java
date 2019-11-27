/*
 * The MIT License
 *
 * Copyright 2019 Coppertine.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.coppertine.tafe.java;

import java.util.ArrayList;

/**
 *
 * @author Coppertine
 */
public class BinaryTree {

    /**
     * Root {@link BTNode} of the binary tree.
     */
    private BTNode rootNode;

    /**
     * Temporary node used for adding, removing, and searching.
     */
    private BTNode tempNode;
    /**
     * The initial node amount within the tree.
     */
    private static int nodeAmount = 0;
    /**
     * The initial tree width.
     */
    private static final int TREE_WIDTH = 120;

    /**
     * The tree width, does change over time due to additional nodes.
     */
    private static int treeWidth = TREE_WIDTH;

    /**
     * The root position for the GUI in the X axis.
     */
    private static final int ROOT_POSITION_X = 500;
    /**
     * The root position for the GUI in the Y axis.
     */
    private static final int ROOT_POSITION_Y = 10;
    /**
     * The root spacing for the GUI.
     */
    private static final int NODE_OFFSET_Y = 80;
    /**
     * The width expansion constant.
     */
    private static final int WIDTH_EXPAND_5 = 5;
    /**
     * The string builder used for collecting the search values.
     */
    private StringBuilder orderStringBuilder = new StringBuilder();
    /**
     * The ArrayList used for exporting the search values.
     */
    private ArrayList<Integer> orderStringList = new ArrayList<>();

    /**
     * The constructor for the Binary Tree.
     *
     * @param rootNodeInput The BTNode object as root node.
     */
    public BinaryTree(final BTNode rootNodeInput) {
        this.rootNode = rootNodeInput;
    }

    /**
     * The constructor for the Binary Tree.
     */
    public BinaryTree() {
        this.rootNode = null;
    }

    /**
     * Inserts the {@link BTNode} into the tree structure.
     *
     * @param data The integer value of the Node for the tree.
     */
    public final void addNode(final Integer data) {
        BTNode node = new BTNode(data);

        if (rootNode == null) {
            this.rootNode = node;
            this.rootNode.setPositionX(ROOT_POSITION_X);
            this.rootNode.setPositionY(ROOT_POSITION_Y);
            nodeAmount++;
        } else {
            boolean continueLoop = true;
            tempNode = rootNode;
            while (continueLoop) {
                if ((int) tempNode.getValues() > data) {
                    if (tempNode.getLeft() == null) {
                        tempNode.setLeft(node);
                        tempNode.getLeft().setPositionX(
                                tempNode.getPositionX() - treeWidth);
                        tempNode.getLeft().setPositionY(
                                tempNode.getPositionY() + NODE_OFFSET_Y);
                        nodeAmount++;
                        treeWidth -= WIDTH_EXPAND_5;
                        continueLoop = false;
                    } else {
                        tempNode = tempNode.getLeft();
                    }
                } else {
                    if (tempNode.getRight() == null) {
                        tempNode.setRight(node);
                        tempNode.getRight().setPositionX(
                                tempNode.getPositionX() + treeWidth);
                        tempNode.getRight().setPositionY(
                                tempNode.getPositionY() + NODE_OFFSET_Y);
                        nodeAmount++;
                        treeWidth -= WIDTH_EXPAND_5;
                        continueLoop = false;
                    } else {
                        tempNode = tempNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * A recursive method to collect the array list value using the Pre Order
     * method.
     *
     * @param currentNode The current Binary Tree Node.
     */
    private void preOrderArrayList(final BTNode currentNode) {
        if (currentNode != null) {
            orderStringList.add(Integer.parseInt(
                    currentNode.getValues().toString()));
            preOrderTraverseTree(currentNode.getLeft());
            preOrderTraverseTree(currentNode.getRight());
        }

    }

    /**
     * A recursive method to collect the string representation of the Pre Order
     * method for the binary tree.
     *
     * @param currentNode The current Binary Tree Node.
     */
    private void preOrderTraverseTree(final BTNode currentNode) {
        if (currentNode != null) {
            orderStringBuilder
                    .append(currentNode.getValues().toString())
                    .append(",");
            preOrderTraverseTree(currentNode.getLeft());
            preOrderTraverseTree(currentNode.getRight());
        }
    }

    /**
     * Returns the ArrayList of integers from the Pre order collection of the
     * Binary Tree.
     *
     * @return The sorted list of values.
     */
    public final ArrayList<Integer> getPreOrderArrayList() {
        orderStringList = new ArrayList();
        preOrderArrayList(this.rootNode);
        return orderStringList;
    }

    /**
     * Collects the representation of the pre order traversal of the binary
     * tree.
     *
     * @return String representation of binary tree.
     */
    public final String getPreOrder() {
        orderStringBuilder = new StringBuilder("Pre-Order: ");
        preOrderTraverseTree(this.rootNode);
        return orderStringBuilder.toString();
    }

    /**
     * A recursive method to collect the string representation of the In Order
     * method for the binary tree.
     *
     * @param currentNode The current Binary Tree Node.
     */
    private void inOrderTraverseTree(final BTNode currentNode) {
        if (currentNode != null) {
            inOrderTraverseTree(currentNode.getLeft());
            orderStringBuilder
                    .append(currentNode.getValues().toString())
                    .append(",");
            inOrderTraverseTree(currentNode.getRight());
        }
    }

    /**
     * A recursive method to collect the array list value using the In Order
     * method.
     *
     * @param currentNode The current Binary Tree Node.
     */
    private void inOrderArrayList(final BTNode currentNode) {
        if (currentNode != null) {
            inOrderTraverseTree(currentNode.getLeft());
            orderStringList.add(Integer.parseInt(
                    currentNode.getValues().toString()));
            inOrderTraverseTree(currentNode.getRight());
        }

    }

    /**
     * Returns the ArrayList of integers from the In order collection of the
     * Binary Tree.
     *
     * @return The sorted list of values.
     */
    public final ArrayList<Integer> getInOrderArrayList() {
        orderStringList = new ArrayList();
        inOrderArrayList(this.rootNode);
        return orderStringList;
    }

    /**
     * Collects the representation of the pre order traversal of the binary
     * tree.
     *
     * @return String representation of binary tree.
     */
    public final String getInOrder() {
        orderStringBuilder = new StringBuilder();
        inOrderTraverseTree(this.rootNode);
        return orderStringBuilder.toString();
    }

    private void postOrderTraverseTree(final BTNode currentNode) {
        if (currentNode != null) {
            postOrderTraverseTree(currentNode.getLeft());
            postOrderTraverseTree(currentNode.getRight());
            orderStringBuilder
                    .append(currentNode.getValues().toString())
                    .append(",");
        }
    }

    /**
     * A recursive method to collect the array list value using the Post Order
     * method.
     *
     * @param currentNode The current Binary Tree Node.
     */
    private void postOrderArrayList(final BTNode currentNode) {
        if (currentNode != null) {
            postOrderTraverseTree(currentNode.getLeft());
            postOrderTraverseTree(currentNode.getRight());
            orderStringList.add(Integer.parseInt(currentNode.getValues().toString()));

        }

    }

    /**
     * Returns the ArrayList of integers from the Post order collection of the
     * Binary Tree.
     *
     * @return The sorted list of values.
     */
    public final ArrayList<Integer> getPostOrderArrayList() {
        orderStringList = new ArrayList();
        postOrderArrayList(this.rootNode);
        return orderStringList;
    }

    /**
     * Collects the representation of the pre order traversal of the binary
     * tree.
     *
     * @return String representation of binary tree.
     */
    public final String getPostOrder() {
        orderStringBuilder = new StringBuilder();
        postOrderTraverseTree(this.rootNode);
        return orderStringBuilder.toString();
    }

    /**
     * Returns the integer representation of the Binary Tree node amount.
     * @return The node amount within the tree.
     */
    public static final int getNodeAmount() {
        return nodeAmount;
    }

    /**
     * Sets the integer representation of the amount of nodes within the
     * Binary Tree.
     * @param nodeAmountInput The amount of nodes within the tree.
     */
    public static final void setNodeAmount(final int nodeAmountInput) {
        BinaryTree.nodeAmount = nodeAmountInput;
    }

    /**
     * Returns the root node found in the tree.
     * @return The root node object.
     */
    public final BTNode getRootNode() {
        return this.rootNode;
    }

    /**
     * Sets the root node of the binary tree
     * @param rootNodeInput The root node object.
     */
    public final void setRootNode(final BTNode rootNodeInput) {
        this.rootNode = rootNodeInput;
    }

}
