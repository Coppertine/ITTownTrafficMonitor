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
package com.coppertine.tafe.java.ITTownTrafficManager;

/**
 *
 * @author nick_
 */
public class Location {

    private int stationNum;
    private String name;

    /**
     *
     * @param stationNum The station number according to the office.
     * @param name The location name.
     */
    public Location(int stationNum, String name) {
        this.stationNum = stationNum;
        this.name = name;
    }

    /**
     * Returns the station number of the location.
     * @return Station Number according to the Office
     */
    public int getStationNum() {
        return stationNum;
    }

    /**
     * Sets the Station number of the location.
     * @param stationNum Station number of the location.
     */
    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    /**
     *
     * @return The location name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The location name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
