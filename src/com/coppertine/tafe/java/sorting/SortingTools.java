/*
 * The MIT License
 *
 * Copyright 2019 nick_.
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
    
    public ArrayList<Object> SortBubble(ArrayList<Object> input)
    {
        /*
        
                for i = 1:n,
                    swapped = false
                    for j = n:i+1,
                        if a[j] < a[j-1],
                            swap a[j,j-1]
                            swapped = true
                    → invariant: a[1..i] in final position
                    break if not swapped
                end

        */
        try{
            boolean swapped = false;
            for(int index = 0; index < input.size() - 1; index++)
            {           
                swapped = false;
                for(int innerIndex = 0; innerIndex < input.size() - index - 1; innerIndex++)
                {
                    if((double)input.get(innerIndex) > (double)input.get(index + 1))
                    {
                        input = SwapValues(input, innerIndex, index + 1);
                        swapped = true;
                    }
                }
            }
            if(!swapped)
            {
                return input;
            }

        }
        catch(Exception e)
        {
            //TODO: Print Exception
            return input;
        }
        return input;
    }
    
    public ArrayList<Object> SwapValues(ArrayList<Object> input, int indexOne, int indexTwo)
    {
        try {
            
            Object tmp = input.get(indexOne);
            input.set(indexOne,input.get(indexTwo));
            input.set(indexTwo, tmp);            
        }
        catch(Exception e)
        {
            //TODO: Print error on Exception
            return null;
        }
        return input;
    }
}
