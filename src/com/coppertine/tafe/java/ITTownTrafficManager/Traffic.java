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

import java.time.LocalDateTime;


/**
 *
 * @author Coppertine
 */
public class Traffic {

    /** **/
    private LocalDateTime time;
    /** **/
    private Location location;
    /** **/
    private int numLanes;
    /** **/
    private int totalVehicle;
    /** **/
    private int averagePerLane;
    /** **/
    private int averageVelocity;

    /**
     *
     * @param time
     * @param location
     * @param numLanes
     * @param totalVehicle
     * @param averagePerLane
     * @param averageVelocity
     */
    public Traffic(LocalDateTime time, Location location, int numLanes, int totalVehicle, int averagePerLane, int averageVelocity) {
        this.time = time;
        this.location = location;
        this.numLanes = numLanes;
        this.totalVehicle = totalVehicle;
        this.averagePerLane = averagePerLane;
        this.averageVelocity = averageVelocity;
    }

    public Traffic() {
        this.time = LocalDateTime.now();
        this.location = new Location(1,"");
        this.numLanes = 5;
        this.totalVehicle = 100;
        this.averageVelocity = 20;
        this.averagePerLane = 10;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getNumLanes() {
        return numLanes;
    }

    public void setNumLanes(int numLanes) {
        this.numLanes = numLanes;
    }

    public int getTotalVehicle() {
        return totalVehicle;
    }

    public void setTotalVehicle(int totalVehicle) {
        this.totalVehicle = totalVehicle;
    }

    public int getAveragePerLane() {
        return averagePerLane;
    }

    public void setAveragePerLane(int averagePerLane) {
        this.averagePerLane = averagePerLane;
    }

    public int getAverageVelocity() {
        return averageVelocity;
    }

    public void setAverageVelocity(int averageVelocity) {
        this.averageVelocity = averageVelocity;
    }



    
}
