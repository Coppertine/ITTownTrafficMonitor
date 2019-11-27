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

import com.coppertine.tafe.java.ITTownTrafficManager.Traffic;
import java.util.ArrayList;

/**
 *
 * @author nick_
 */
public class SortingTools {

    /**
     * Sorts the list of {@link Traffic} Objects using selected
     * {@link SortingType}.
     *
     * @param type The type of sorting method to utilize.
     * @param input The list of traffic objects to parse through.
     * @return The sorted list of traffic objects. If no sorting type is found,
     * an empty list is given instead.
     * @see Traffic
     */
    public final ArrayList<Traffic> sortAlgorithm(final SortingType type,
            final ArrayList<Traffic> input) {
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
     * {@link sortAlgorithm()} method. Relies on the structure of
     * {@link Traffic}
     *
     * @param input The list of Traffic objects
     * @return ArrayList of Objects from the sorted array. Must be accessed
     * using the {@link sortAlgorithm()} method.
     */
    private ArrayList<Traffic> sortBubble(final ArrayList<Traffic> input) {
        try {

            boolean swapped = false;
            ArrayList<Traffic> swapedArray = (ArrayList<Traffic>) input;
            for (Traffic indexItem : swapedArray) {
                swapped = false;
                for (Traffic innerItem : swapedArray.subList(0,
                        swapedArray.size()
                        - swapedArray.indexOf(indexItem) - 1)) {
                    if (indexItem.getLocation() > (double) swapedArray.get(
                            swapedArray.indexOf(indexItem) + 1).getLocation()) {
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
     * @param input The list of Traffic Objects
     * @return ArrayList of Objects from the sorted array. Must be accessed
     * using the {@link sortAlgorithm()} method.
     */
    private ArrayList<Traffic> sortInsertion(final ArrayList<Traffic> input) {
        ArrayList<Traffic> sortedArray = input;
        sortedArray.subList(1, sortedArray.size())
                .forEach((tempValue) -> {
                    for (int i = sortedArray.indexOf(
                            (int) tempValue.getTotalVehicle()) - 1;
                            (i >= 0) && ((int) sortedArray.get(i)
                                    .getTotalVehicle()
                            < (int) tempValue.getTotalVehicle());
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
     * @param input The list of objects.
     * @param lowValue The low index integer.
     * @param highValue The high index integer.
     * @return The partition index.
     */
    private int partitionArray(final ArrayList<Traffic> input,
            final Integer lowValue, final Integer highValue) {
        ArrayList<Traffic> sortedArray = input;
        int pivot = sortedArray.get(highValue).getAverageVelocity();
        int selectionLow = (int) lowValue - 1;
        for (Traffic value : sortedArray.subList(
                (int) lowValue,
                (int) highValue)) {
            if ((int) value.getAverageVelocity()
                    < (int) pivot) {
                selectionLow++;
                sortedArray = swapValues(
                        sortedArray,
                        selectionLow,
                        (int) value.getAverageVelocity());
            }
        }
        swapValues(sortedArray, selectionLow + 1, (int) highValue);
        return selectionLow + 1;
    }

    /**
     * Quick Sort method utilizing the recursion of the function. Must be
     * accessed using the {@link sortAlgorithm()} method.
     *
     * @param input The list of Traffic objects.
     * @param indexLow The low index of the selection.
     * @param indexHigh The high index of the selection.
     * @return The list of sorted traffic objects.
     */
    private ArrayList<Traffic> sortQuick(final ArrayList<Traffic> input,
            final int indexLow, final int indexHigh) {
        if (indexLow < indexHigh) {
            int partitionIndex = partitionArray(input, indexLow, indexHigh);
            sortQuick(input, indexLow, partitionIndex - 1);
            sortQuick(input, partitionIndex + 1, indexHigh);
        } else {
            return input;
        }
        return input;
    }

    /**
     * Swaps two selected values from ArrayList. The returned array is the exact
     * same as the input array with only the index values swapped.
     *
     * @param input Traffic list
     * @param indexOne index one to swap.
     * @param indexTwo index two to swap.
     * @return ArrayList
     */
    public final ArrayList<Traffic> swapValues(final ArrayList<Traffic> input,
            final int indexOne, final int indexTwo) {
        try {
            Traffic tmp = input.get(indexOne);
            input.set(indexOne, input.get(indexTwo));
            input.set(indexTwo, tmp);
        } catch (Exception e) {
            throw e;
        }
        return input;
    }

}
