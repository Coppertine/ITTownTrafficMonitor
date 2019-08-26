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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


/**
 *
 * @author Coppertine
 */
public class Traffic {

    private ObjectProperty<LocalDateTime> time =
            new SimpleObjectProperty<>(this, "time");
    private ObjectProperty<Location> location =
            new SimpleObjectProperty<>(this, "location");
    private IntegerProperty numLanes;
    private IntegerProperty totalVehicle;
    private IntegerProperty averagePerLane;
    private IntegerProperty averageVelocity;

    public LocalDateTime getTime() {
        return time.get();
    }

    /**
     *
     * @param time
     */
    public final void setTime(LocalDateTime time) {
        this.time.set(time);
    }

    public final Location getLocation() {
        return location.get();
    }

    public final void setLocation(Location location) {
        this.location.set(location);
    }

    public final int getNumLanes() {
        return numLanes.get();
    }

    public final void setNumLanes(int numLanes) {
        this.numLanes.set(numLanes);
    }

    public final int getTotalVehicle() {
        return totalVehicle.get();
    }

    public final void setTotalVehicle(int totalVehicle) {
        this.totalVehicle.set(totalVehicle);
    }

    public int getAveragePerLane() {
        return averagePerLane.get();
    }

    public void setAveragePerLane(int averagePerLane) {
        this.averagePerLane.set(averagePerLane);
    }

    public int getAverageVelocity() {
        return averageVelocity.get();
    }

    public void setAverageVelocity(int averageVelocity) {
        this.averageVelocity.set(averageVelocity);
    }
    
}
