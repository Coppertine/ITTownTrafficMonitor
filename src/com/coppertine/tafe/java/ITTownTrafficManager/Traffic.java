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

import com.coppertine.tafe.java.FileIO;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Coppertine
 */
public class Traffic {

    /**
     * *
     */
    private LocalDateTime time;
    /**
     * *
     */
    private Location location;
    /**
     * *
     */
    private int numLanes;
    /**
     * *
     */
    private int totalVehicle;
    /**
     * *
     */
    private int averagePerLane;
    /**
     * *
     */
    private int averageVelocity;

    /**
     * Default value of lanes per report.
     */
    private static final int DEFAULT_LANES = 5;

    /**
     * Default value of total vehicles.
     */
    private static final int DEFAULT_VEHICLE = 100;

    /**
     * Default value of average vehicles per lane.
     */
    private static final int DEFAULT_VEHICLE_PER_LANE = 20;

    /**
     * Default value of average vehicle velocity.
     */
    private static final int DEFAULT_VEHICLE_VELOCITY = 10;

    private static final int TIME_STRING_INDEX = 1;
    private static final int LOCATION_STRING_INDEX = 2;
    private static final int LANES_STRING_INDEX = 3;
    private static final int TOTAL_VEHICLE_STRING_INDEX = 4;
    private static final int AVERAGE_LANE_STRING_INDEX = 5;

    /**
     *
     * @param time
     * @param location
     * @param numLanes
     * @param totalVehicle
     * @param averagePerLane
     * @param averageVelocity
     */
    public Traffic(LocalDateTime time, Location location,
            int numLanes, int totalVehicle, int averagePerLane,
            int averageVelocity) {
        this.time = time;
        this.location = location;
        this.numLanes = numLanes;
        this.totalVehicle = totalVehicle;
        this.averagePerLane = averagePerLane;
        this.averageVelocity = averageVelocity;
    }

    /**
     *
     */
    public Traffic() {
        this.time = LocalDateTime.now();
        this.location = new Location(1, "");
        this.numLanes = DEFAULT_LANES;
        this.totalVehicle = DEFAULT_VEHICLE;
        this.averageVelocity = DEFAULT_VEHICLE_PER_LANE;
        this.averagePerLane = DEFAULT_VEHICLE_VELOCITY;
    }

    /**
     *
     * @return
     */
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

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return FileIO.formatCSV(new String[]{
            time.toString(),
            location.toString(),
            String.valueOf(numLanes),
            String.valueOf(totalVehicle),
            String.valueOf(averagePerLane),
            String.valueOf(averageVelocity)
        });
    }

    /**
     *
     * @param lineInput
     * @return
     */
    public Traffic parseTrafficLine(String lineInput) {
        String[] line = lineInput.split(",");
        Traffic temp = new Traffic();

        temp.setTime(LocalDateTime.parse(line[TIME_STRING_INDEX]));
        temp.setLocation(
                new Location(Integer.parseInt(line[LOCATION_STRING_INDEX]), "")
        );
        temp.setNumLanes(Integer.parseInt(line[LANES_STRING_INDEX]));
        temp.setTotalVehicle(
                Integer.parseInt(line[TOTAL_VEHICLE_STRING_INDEX])
        );
        temp.setAverageVelocity(
                Integer.parseInt(line[AVERAGE_LANE_STRING_INDEX])
        );
        return temp;
    }

    /**
     *
     * @param lineInput
     * @return
     */
    public ArrayList<Traffic> parseTrafficLines(ArrayList<String> lineInput) {
        ArrayList<Traffic> temp = new ArrayList<>();

        lineInput.forEach((line) -> {
            temp.add(parseTrafficLine(line));
        });
        return temp;
    }
}
