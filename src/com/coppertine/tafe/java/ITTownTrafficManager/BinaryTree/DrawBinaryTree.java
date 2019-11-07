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
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Coppertine
 */
public class DrawBinaryTree extends JPanel {

    /**
     * ArrayList of Nodes to be displayed into the tree.
     */
    private static ArrayList<BTNode> nodeList = new ArrayList<BTNode>();
    /**
     * The Oval
     */
    private static final int OVAL_HEIGHT = 40;
    private static final int OVAL_WIDTH = 40;
    private static final int DATA_STRING_OFFSET_X = 10;
    private static final int DATA_STRING_OFFSET_Y = 25;

    /**
     * Offsets for the Line of the Node.
     */
    private static final int LINE_START_OFFSET_X = 15;
    private static final int LINE_START_OFFSET_Y = 40;
    private static final int RIGHT_LINE_END_OFFSET_X = 100;
    private static final int LEFT_LINE_END_OFFSET_X = -50;
    private static final int LINE_END_OFFSET_Y = 100;

    public DrawBinaryTree(ArrayList<BTNode> nodeListInput) {
        nodeList = nodeListInput;
    }

    @Override
    public final void paintComponent(final Graphics graphics) {
        for (BTNode node : nodeList) {
            graphics.drawOval(
                    node.getPositionX(),
                    node.getPositionY(),
                    OVAL_HEIGHT, OVAL_WIDTH);
            String stringValue = String.valueOf(node.getValues());
            graphics.drawString(
                    stringValue,
                    node.getPositionX() + DATA_STRING_OFFSET_X,
                    node.getPositionY() + DATA_STRING_OFFSET_Y);
            if (node.getRight() != null) {
                graphics.setColor(Color.red);
                graphics.drawLine(
                        node.getPositionX() + LINE_START_OFFSET_X,
                        node.getPositionY() + LINE_START_OFFSET_Y,
                        node.getPositionX() + RIGHT_LINE_END_OFFSET_X,
                        node.getPositionY() + LINE_END_OFFSET_Y);
            }
            if (node.getLeft() != null) {
                graphics.setColor(Color.green);
                graphics.drawLine(
                        node.getPositionX() + LINE_START_OFFSET_X,
                        node.getPositionY() + LINE_START_OFFSET_Y,
                        node.getPositionX() + LEFT_LINE_END_OFFSET_X,
                        node.getPositionY() + LINE_END_OFFSET_Y);
            }
            graphics.setColor(Color.black);
        }
    }
}
