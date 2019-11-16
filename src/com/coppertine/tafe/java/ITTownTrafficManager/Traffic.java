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
package com.coppertine.tafe.java.ITTownTrafficManager;

import com.coppertine.tafe.java.FileIO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Coppertine
 */
public class Traffic {

    private final ObjectProperty<LocalDateTime> time = new SimpleObjectProperty<>();

    /**
     *
     * @param input
     */
    public Traffic(String input) {
        String[] list = input.split(",");

        setTime(LocalDateTime.parse(list[2]));
        setLocation(new Location(Integer.parseInt(list[1]),
                list[1]));
        setNumLanes(Integer.parseInt(list[LANES_STRING_INDEX]));
        setTotalVehicle(Integer.parseInt(list[TOTAL_VEHICLE_STRING_INDEX]));
        setAveragePerLane(Integer.parseInt(list[AVERAGE_LANE_STRING_INDEX]));
        setAverageVelocity(Integer.parseInt(list[7]));
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time.get().format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     *
     * @param value
     */
    public void setTime(LocalDateTime value) {
        time.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty timeProperty() {
        return time;
    }
    private ObjectProperty<Location> location = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public String getLocation() {
        return location.get().getName();
    }

    /**
     *
     * @param value
     */
    public void setLocation(Location value) {
        location.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty locationProperty() {
        return location;
    }
    private final IntegerProperty numLanes = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getNumLanes() {
        return numLanes.get();
    }

    /**
     *
     * @param value
     */
    public void setNumLanes(int value) {
        numLanes.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty numLanesProperty() {
        return numLanes;
    }
    private final IntegerProperty totalVehicle = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getTotalVehicle() {
        return totalVehicle.get();
    }

    /**
     *
     * @param value
     */
    public void setTotalVehicle(int value) {
        totalVehicle.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty totalVehicleProperty() {
        return totalVehicle;
    }
    private final IntegerProperty averagePerLane = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getAveragePerLane() {
        return averagePerLane.get();
    }

    /**
     *
     * @param value
     */
    public void setAveragePerLane(int value) {
        averagePerLane.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty averagePerLaneProperty() {
        return averagePerLane;
    }

    /**
     *
     */
    private final IntegerProperty averageVelocity = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public Integer getAverageVelocity() {
        return averageVelocity.get();
    }

    /**
     *
     * @param value
     */
    public void setAverageVelocity(Integer value) {
        averageVelocity.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty averageVelocityProperty() {
        return averageVelocity;
    }

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
    /**
     *
     */
    private static final int LOCATION_STRING_INDEX = 2;
    /**
     *
     */
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
        setTime(time);
        setLocation(location);
        setNumLanes(numLanes);
        setTotalVehicle(totalVehicle);
        setAveragePerLane(averagePerLane);
        setAverageVelocity(averageVelocity);
    }

    /**
     *
     */
    public Traffic() {
        this.setTime(LocalDateTime.now());
        this.setLocation(new Location(1, ""));
        this.setNumLanes(DEFAULT_LANES);
        this.setTotalVehicle(DEFAULT_VEHICLE);
        this.setAverageVelocity(DEFAULT_VEHICLE_PER_LANE);
        this.setAveragePerLane(DEFAULT_VEHICLE_VELOCITY);
    }

    /**
     * {@inheritDoc. }
     *
     * @return
     */
    @Override
    public String toString() {
        return FileIO.formatCSV(new String[]{
            getTime().toString(),
            getLocation(),
            String.valueOf(getNumLanes()),
            String.valueOf(getTotalVehicle()),
            String.valueOf(getAveragePerLane()),
            String.valueOf(getAverageVelocity())
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
        // 06:00:00 AM
        temp.setLocation(
                new Location(Integer.parseInt(line[LOCATION_STRING_INDEX]),
                        line[LOCATION_STRING_INDEX])
        );
        temp.setNumLanes(Integer.parseInt(line[LANES_STRING_INDEX]));
        temp.setTotalVehicle(
                Integer.parseInt(line[TOTAL_VEHICLE_STRING_INDEX])
        );
        temp.setAverageVelocity(
                Integer.parseInt(line[AVERAGE_LANE_STRING_INDEX])
        );

        System.out.println("Time: " + temp.getTime()
                + "\nLocation: " + temp.getLocation()
                + "\nNumber Lanes: " + String.valueOf(temp.getNumLanes())
                + "\nTotal Vechicles: " + String.valueOf(temp.getTotalVehicle())
                + "\nAverage Velocity: "
                + String.valueOf(temp.getAverageVelocity()));
        return temp;
    }

    /**
     *
     * @param lineInput
     * @return
     */
    public ArrayList<Traffic> parseTrafficLines(ArrayList<String> lineInput) {
        ArrayList<Traffic> temp = new ArrayList<>();

        lineInput.subList(1, lineInput.size()).forEach((line) -> {
            System.out.println(line);
            temp.add(parseTrafficLine(line));
        });
        return temp;
    }
}
