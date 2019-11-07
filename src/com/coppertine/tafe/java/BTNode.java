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
public class BTNode {

    /** <code>Object</code> of value.
     *
     * @see Object
     */
    private Object values;
    /**
     * Binary Tree Node for the left side.
     */
    private BTNode left;
    /**
     * Binary Tree Node for the Right side.
     */
    private BTNode right;

    private int positionX;
    private int positionY;

    /**
     * Creates a Binary Tree Node.
     *
     * @param inputData <code>Object</code> of data.
     * @param inputLeft Binary Tree Node of the left side.
     * @param inputRight Binary Tree Node of the right side.
     */
    public BTNode(final Object inputData, final BTNode inputLeft,
            final BTNode inputRight) {
        this.values = inputData;
        this.left = null;
        this.right = null;
    }

    public BTNode(final Object inputData) {
        this.values = inputData;
        this.left = null;
        this.right = null;

    }

    /**
     * Creates String value of Node's Value.
     *
     * @param n <code>BTNode</code> of BinaryTree.
     * @see Object.toString()
     */
    public final void displayNode(final BTNode n) {
        System.out.print(n.values.toString() + " ");
    }

    /**
     *
     * @return
     */
    public Object getValues() {
        return values;
    }

    /**
     *
     * @param values
     */
    public void setValues(Object values) {
        this.values = values;
    }

    /**
     *
     * @return
     */
    public BTNode getLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(BTNode left) {
        this.left = left;
    }

    /**
     *
     * @return Binary Tree on the Right side of the node.
     */
    public BTNode getRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(BTNode right) {
        this.right = right;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

}
