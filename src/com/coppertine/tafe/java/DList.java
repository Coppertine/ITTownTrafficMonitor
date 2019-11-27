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
 *
 * @author Coppertine
 * @param <K>
 */
public class DList<K> {
    // Using Generic Type K for Key and V for Value of Key

    /**
     * Head DLNode to store all data placed into list.
     */
    @SuppressWarnings("FieldMayBeFinal")
    private DLNode head;

    /**
     *
     * @param inputKey
     */
    public DList(final K inputKey) {
        head = new DLNode(inputKey);
    }

    /**
     *
     * @param inputSearch
     * @return
     */
    public DLNode findNode(K inputSearch) {
        for (DLNode current = head.getNext();
                current != head; current = current.getNext()) {
            if (current.getTrafficData().equals(inputSearch)) {
                return current;
            }
        }
        return null;
    }

    /**
     * Returns the value from index.
     *
     * @param i Index value to object to find.
     * @throws ArrayIndexOutOfBoundsException If index is less than 0 or current
     * value is not found.
     * @return List Node from Index.
     */
    public final DLNode getFromIndex(final int i)
            throws ArrayIndexOutOfBoundsException {
        DLNode current = this.head;
        if (i < 0 || current == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (i > 0) {
            current = current.getNext();
            if (current == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return current;
    }

    @Override
    public final String toString() {

        if (head.getNext() == head) { // List Empty, only head node
            return "List Empty";
        }
        StringBuilder str = new StringBuilder("List Content = ");
        for (DLNode current = head.getNext();
                current != head;
                current = current.getNext()) {
            if (current == null) {
                break;
            }
            str.append(current.toString());
        }
        return str.toString();
    }

    public DLNode getHead() {
        return head;
    }

    public void setHead(DLNode head) {
        this.head = head;
    }

}
