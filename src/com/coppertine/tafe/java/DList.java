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
 * Doubly Linked List to store Nodes of Data.
 * @author Coppertine
 * @param <K>
 * @param <V>
 */
public class DList<K, V> {
    // Using Generic Type K for Key and V for Value of Key

    /** Head Node to store all data placed into list. **/
    private Node head;

    /**
     *
     * @param inputKey
     * @param inputValue
     */
    public DList(final K inputKey, final V inputValue) {
        head = new Node(inputKey, inputValue);
    }
    
    public Node findNode(Object inputSearch) {
       return null; 
    }
    
    public Node find(int i) {
        return null;
    }
    
    @Override
    public String toString() {
        return "";
    }

}
