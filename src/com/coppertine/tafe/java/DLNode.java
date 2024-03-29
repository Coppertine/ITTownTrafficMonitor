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

import com.coppertine.tafe.java.ITTownTrafficManager.TrafficNode;

/**
 * DLNode to be used for DList.
 *
 * @author Coppertine
 * @see DList
 * @see TrafficNode
 */
public class DLNode {

    /**
     * Previous DLNode in DList. *
     */
    private DLNode prev;
    /**
     * Next DLNode in DList. *
     */
    private DLNode next;

    /**
     * Traffic Data for each DLNode in DList. *
     */
    private TrafficNode trafficData;

    /**
     *
     */
    public DLNode() {
        this.prev = this;
        this.next = this;
    }

    /**
     *
     * @param value
     */
    public DLNode(Object value) {
        this.prev = null;
        this.next = null;
        this.trafficData = new TrafficNode(value);
    }

    /**
     * Appends <code>DLNode</code> to either the next or previous
     * <code>DLNode</code> of <code>DList</code>.
     *
     * @param newNode
     */
    public void appendNode(DLNode newNode) {
        newNode.prev = this;
        newNode.next = next;

        if (next != null) {
            next.prev = newNode;
        }
        next = newNode;
        System.out.println("Node with data " + newNode.trafficData.toString()
                + " appended after Node with data " + trafficData.toString());
    }

    /**
     *
     * @param newNode
     */
    public void insertNode(DLNode newNode) {
        newNode.prev = prev;
        newNode.next = this;
        prev.next = newNode;
        prev = newNode;
        System.out.println("Node with data " + newNode.trafficData.toString()
                + " inserted before Node with data " + trafficData.toString());
    }

    /**
     *
     */
    public void remove() {
        next.prev = prev;
        prev.next = next;
        System.out.println("Node with data " + trafficData.toString()
                + " removed");
    }
    
    @Override
    /** {@inheritDoc} */
    public final String toString() {
        return this.trafficData.toString();
    }

    /**
     *
     * @return
     */
    public DLNode getPrev() {
        return prev;
    }

    /**
     *
     * @param prev
     */
    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    /**
     *
     * @return
     */
    public DLNode getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(DLNode next) {
        this.next = next;
    }

    /**
     *
     * @return
     */
    public TrafficNode getTrafficData() {
        return trafficData;
    }

    /**
     *
     * @param trafficData
     */
    public void setTrafficData(TrafficNode trafficData) {
        this.trafficData = trafficData;
    }

}
