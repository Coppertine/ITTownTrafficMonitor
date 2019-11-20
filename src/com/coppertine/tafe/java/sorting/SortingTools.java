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
     * @param type
     * @param input
     * @return
     */
    public ArrayList<Object> sortAlgorithm(SortingType type,
            ArrayList<Object> input) {
        switch (type) {
            case BubbleSort:
                return sortBubble(input);
            case Insertion:
                return sortInsertion(input);
            case QuickSort:
                return sortQuick(input, 0, input.size() - 1);
            default:
                return new ArrayList();
        }
    }

    /**
     * Bubble Sort of the ArrayList of Objects. Must be accessed using the
     * {@link sortAlgorithm()} method.
     *
     * @param input
     * @return ArrayList
     */
    private ArrayList<Object> sortBubble(final ArrayList<Object> input) {
        try {
            boolean swapped = false;
            ArrayList<Object> swapedArray = input;
            for (Object indexItem : swapedArray) {
                swapped = false;
                for (Object innerItem : swapedArray.subList(0,
                        swapedArray.size()
                        - swapedArray.indexOf(indexItem) - 1)) {
                    if ((double) indexItem > (double) swapedArray.get(
                            swapedArray.indexOf(indexItem) + 1)) {
                        swapedArray = swapValues(swapedArray,
                                swapedArray.indexOf(innerItem),
                                swapedArray.indexOf(indexItem)
                                + 1);
                        swapped = true;
                    }
                }
            }
            if (!swapped) {
                return swapedArray;
            }

        } catch (Exception e) {
            throw e;
        }
        return input;
    }

    /**
     * Uses the Insertion algorithm to sort an ArrayList of objects.
     *
     * @param input
     * @return ArrayList of Objects from the sorted array. Must be accessed
     * using the {@link sortAlgorithm()} method.
     */
    private ArrayList<Object> sortInsertion(final ArrayList<Object> input) {
        ArrayList<Object> sortedArray = input;
        sortedArray.subList(1, sortedArray.size())
                .forEach((tempValue) -> {
                    for (int i = sortedArray.indexOf((int) tempValue) - 1;
                            (i >= 0) && ((int) sortedArray.get(i) < (int) tempValue);
                            i--) {
                        sortedArray.set(i + 1, sortedArray.get(i));
                    }
                });
        return sortedArray;
    }

    /**
     * Grabs the partition integer from the ArrayList. Must be accessed using
     * the {@link sortAlgorithm()} method with type = QuickSort
     *
     * @param input
     * @param lowValue
     * @param highValue
     * @return The partition index.
     */
    private int partitionArray(final ArrayList<Object> input,
            final Object lowValue, final Object highValue) {
        ArrayList<Object> sortedArray = input;
        Object pivot = highValue;
        int selectionLow = (int) lowValue - 1;
        for (Object value : sortedArray.subList(
                (int) lowValue,
                (int) highValue)) {
            if ((int) value < (int) pivot) {
                selectionLow++;
                sortedArray = swapValues(
                        sortedArray,
                        selectionLow,
                        (int) value);
            }
        }
        swapValues(sortedArray, selectionLow + 1, (int) highValue);
        return selectionLow + 1;
    }

    /**
     * Quick Sort method utilising the recursion of the function. Must be
     * accessed using the {@link sortAlgorithm()} method.
     *
     * @param input
     * @param indexLow
     * @param indexHigh
     * @return
     */
    private ArrayList<Object> sortQuick(ArrayList<Object> input,
            int indexLow, int indexHigh) {
        if (indexLow < indexHigh) {
            int partitionIndex = partitionArray(input, indexLow, indexHigh);
            sortQuick(input, indexLow, partitionIndex - 1);
            sortQuick(input, partitionIndex - 1, indexHigh);
        } else {
            return input;
        }
        return null;
    }

    /**
     * Swaps two selected values from ArrayList. The returned array is the exact
     * same as the input array with only the index values swapped.
     *
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
