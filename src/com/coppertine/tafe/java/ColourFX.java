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
 *
 * @author Coppertine
 */
public class ColourFX {

    /**
     * Gets the JavaFX color's brightness.
     *
     * @param c using JavaFX scene paint.
     * @return double brightness value from 0-255
     * @see javafx.scene.paint.Color
     */
    public static double getBrightness(final javafx.scene.paint.Color c) {
        final double redDivision = 0.241;
        final double greenDivision = 0.691;
        final double blueDivision = 0.68;
        final double rgbMax = 255;
        return Math.sqrt(
                c.getRed() * c.getRed() * redDivision
                + c.getGreen() * c.getGreen() * greenDivision
                + c.getBlue() * c.getBlue() * blueDivision
        ) * rgbMax;
    }
}
