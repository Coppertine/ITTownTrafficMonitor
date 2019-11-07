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
package com.coppertine.tafe.java.ITTownTrafficManager.BinaryTree;

import com.coppertine.tafe.java.BTNode;
import com.coppertine.tafe.java.BinaryTree;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * Creates <code>SpringPannel</code> of Binary Tree extending the
 * <code>BinaryTree</code>.
 *
 * @author Coppertine
 */
public class BinaryTreeView extends BinaryTree {

    private JFrame frame;
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 600;
    private ArrayList<BTNode> nodeList;

    /**
     * Creates new <code>JFrame</code> to the Binary Tree.
     */
    public BinaryTreeView() {
        frame = new JFrame();
        frame.setTitle("Binary Tree View");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        nodeList = new ArrayList<BTNode>();
    }

    /**
     * Includes the <code>BTNode</code> into the tree.
     *
     * @param node The BTNode to be added.
     */
    public final void include(final BTNode node) {
        nodeList.add(node);
        System.out.println((int) node.getValues());
        if (node.getLeft() != null) {
            System.out.println((int) node.getLeft().getValues());
            include(node.getLeft());
        }
        if (node.getRight() != null) {
            System.out.println((int) node.getRight().getValues());

            include(node.getRight());
        }
    }

    public final void run() {
        frame.setContentPane(new DrawBinaryTree(nodeList));
        frame.setVisible(true);
        System.out.println("Running Frame");
    }
}
