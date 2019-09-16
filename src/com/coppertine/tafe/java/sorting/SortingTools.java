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
package com.coppertine.tafe.java.sorting;

import java.util.ArrayList;

/**
 *
 * @author nick_
 */
public class SortingTools {

    /**
     *
     * @param input
     * @return ArrayList
     */
    public final ArrayList<Object> sortBubble(ArrayList<Object> input) {
        try {
            boolean swapped = false;
            for (int index = 0; index < input.size() - 1; index++) {
                swapped = false;
                for (int innerIndex = 0;
                        innerIndex < input.size() - index - 1;
                        innerIndex++) {
                    if ((double) input.get(innerIndex)
                            > (double) input.get(index + 1)) {
                        input = swapValues(input, innerIndex, index + 1);
                        swapped = true;
                    }
                }
            }
            if (!swapped) {
                return input;
            }

        } catch (Exception e) {
            throw e;
        }
        return input;
    }
    
    public final ArrayList<Object> sortInsertion(final ArrayList<Object> input) {
        ArrayList<Object> sortedArray = input;
        
        return sortedArray;
    }
    

    /**
     * Swaps two selected values from ArrayList.
     * @param input <code>ArrayList</code>
     * @param indexOne index one to swap.
     * @param indexTwo index two to swap.
     * @return ArrayList
     */
    public final ArrayList<Object> swapValues(final ArrayList<Object> input,
            final int indexOne, final int indexTwo) {
        try {
            Object tmp = input.get(indexOne);
            input.set(indexOne, input.get(indexTwo));
            input.set(indexTwo, tmp);
        } catch (Exception e) {
            throw e;
        }
        return input;
    }
}
