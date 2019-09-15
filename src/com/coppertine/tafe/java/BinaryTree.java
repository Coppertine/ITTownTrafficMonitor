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

/**
 *
 * @author Coppertine
 */
public class BinaryTree {
    /** Root Node of the binary tree. */
    private BTNode rootNode;
    private BTNode tempNode;
    private static int nodeAmmount = 0;
    private static int treeWidth = 120;
    private static final int ROOT_POSITION_X = 500;
    private static final int ROOT_POSITION_Y = 10;
    private static final int NODE_OFFSET_Y = 80;
    private static final int WIDTH_EXPAND_5 = 5;
    

    public BinaryTree(BTNode rootNode) {
        this.rootNode = rootNode;
    }

    public BinaryTree() {
        this.rootNode = null;
    }
    
    public void addNode(Integer data) {
        BTNode node = new BTNode(data);
        
        if(node == null) {
            this.rootNode = node;
            this.rootNode.setPositionX(ROOT_POSITION_X);
            this.rootNode.setPositionY(ROOT_POSITION_Y);
            nodeAmmount++;            
        } else {
            boolean continueLoop = true;
            tempNode = rootNode;
            while (continueLoop) {
                if ((int)tempNode.getValues() > data) {
                    if(tempNode.getLeft() == null) {
                        tempNode.setLeft(node);
                        tempNode.getLeft().setPositionX(
                                tempNode.getPositionX() - treeWidth);
                        tempNode.getLeft().setPositionY(
                                tempNode.getPositionY() + NODE_OFFSET_Y);
                        nodeAmmount++;
                        treeWidth -= WIDTH_EXPAND_5;
                        continueLoop = false;
                    } else {
                        tempNode = tempNode.getLeft();
                    }
                } else {
                    if(tempNode.getRight() == null) {
                        tempNode.setRight(node);
                        tempNode.getRight().setPositionX(
                            tempNode.getPositionX() + treeWidth);
                        tempNode.getRight().setPositionY(
                            tempNode.getPositionY() + NODE_OFFSET_Y);
                        nodeAmmount++;
                        treeWidth -= WIDTH_EXPAND_5;
                        continueLoop = false;
                    } else {
                        tempNode = tempNode.getRight();
                    }
                }
            }
        }
    }

    public static int getNodeAmmount() {
        return nodeAmmount;
    }

    public static void setNodeAmmount(int nodeAmmount) {
        BinaryTree.nodeAmmount = nodeAmmount;
    }
    
    
    
}