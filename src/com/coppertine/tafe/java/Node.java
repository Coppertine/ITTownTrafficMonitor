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
 * Node to be used for DList.
 * @author Coppertine
 * @see DList
 * @see TrafficNode
 */
public class Node {
    /** Previous Node in DList. **/
    private Node prev;
    /** Next Node in DList. **/
    private Node next;

    /** Traffic Data for each Node in DList. **/
    private TrafficNode trafficData;

    public Node(Object key, Object value) {
        
    }
    
    /**
     * Appends <code>Node</code> to
     * either the next or previous <code>Node</code> of <code>DList</code>.
     * @param newNode
     */
    public void appendNode(Node newNode) {
        
    }

    /**
     *
     * @return
     */
    public Node getPrev() {
        return prev;
    }

    /**
     *
     * @param prev
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     *
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(Node next) {
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
