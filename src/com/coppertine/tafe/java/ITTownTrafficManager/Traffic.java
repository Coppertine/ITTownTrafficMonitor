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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The traffic object class.
 *
 * @author Coppertine
 */
public final class Traffic {

    private final ObjectProperty<LocalDateTime> time
            = new SimpleObjectProperty<>();
    private final int AVERAGE_VELOCITY_STRING_INDEX = 6;

    /**
     * The traffic constructor using the following format. {@code
     * ID,LOCATION,DATETIME,LANES,TOTAL_VEHICLES,
     *AVERAGE_PER_LANE,AVERAGE_VELOCITY
     * }
     *
     * @param input A Comma separated value string.
     */
    public Traffic(final String input) {
        this.location = new SimpleObjectProperty<>();
        String[] list = input.split(",");

        setTime(LocalDateTime.parse(list[2]));
        setLocation(new Location(Integer.parseInt(list[1]),
                list[1]));
        setNumLanes(Integer.parseInt(list[LANES_STRING_INDEX]));
        setTotalVehicle(Integer.parseInt(list[TOTAL_VEHICLE_STRING_INDEX]));
        setAveragePerLane(Integer.parseInt(list[AVERAGE_LANE_STRING_INDEX]));
        setAverageVelocity(Integer.parseInt(
                list[AVERAGE_VELOCITY_STRING_INDEX]));
    }

    /**
     * Returns the {@link LocalDateTime }
     * represented as string in ISO Date Time.
     *
     * @return The time represented as string value.
     */
    public String getTime() {
        return time.get().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Sets the time from {@link LocalDateTime}.
     *
     * @param value The time of occurrence.
     */
    public void setTime(final LocalDateTime value) {
        time.set(value);
    }

    /**
     * The time property used for JavaFX Table parsing.
     *
     * @return The object property of the {@link LocalDateTime}.
     */
    public ObjectProperty timeProperty() {
        return time;
    }
    /**
     * The location Object property within the report.
     */
    private final ObjectProperty<Location> location;

    /**
     * The integer representation of the location of report.
     *
     * @return The location number.
     */
    public int getLocation() {
        return location.get().getStationNum();
    }

    /**
     * Sets the location value of report.
     *
     * @param value The location number.
     */
    public void setLocation(final Location value) {
        location.set(value);
    }

    /**
     * The location property used for JavaFX Table parsing.
     *
     * @return The object property of the {@link Location}
     */
    public ObjectProperty locationProperty() {
        return location;
    }
    /**
     * The integer property for the number of lanes within the report.
     */
    private final IntegerProperty numLanes = new SimpleIntegerProperty();

    /**
     * The integer representation of the current number of lanes used in the
     * report.
     *
     * @return The number of lanes.
     */
    public int getNumLanes() {
        return numLanes.get();
    }

    /**
     * Sets the number of lanes within the traffic report.
     *
     * @param value The number of lanes.
     */
    public void setNumLanes(final int value) {
        numLanes.set(value);
    }

    /**
     * The {@link IntegerProperty} utilized for JavaFX table parsing.
     *
     * @return The Integer Property for the number of lanes.
     */
    public IntegerProperty numLanesProperty() {
        return numLanes;
    }
    /**
     * The integer property of the total amount of vehicles within the report.
     */
    private final IntegerProperty totalVehicle = new SimpleIntegerProperty();

    /**
     * The integer representation of the total amount of vehicles in the report.
     *
     * @return The total amount of vehicles.
     */
    public int getTotalVehicle() {
        return totalVehicle.get();
    }

    /**
     * Sets the total amount of vehicles in the report.
     *
     * @param value The integer value of the total amount of vehicles.
     */
    public void setTotalVehicle(final int value) {
        totalVehicle.set(value);
    }

    /**
     * The {@link IntegerProperty} utilized for JavaFX table parsing.
     *
     * @return The Integer Property for the total amount of vehicles.
     */
    public IntegerProperty totalVehicleProperty() {
        return totalVehicle;
    }
    /**
     * The integer property of the average number of vehicles per lane within
     * the report.
     */
    private final IntegerProperty averagePerLane = new SimpleIntegerProperty();

    /**
     * The integer representation of the amount of vehicles per lane.
     *
     * @return The average number of vehicles per lane.
     */
    public int getAveragePerLane() {
        return averagePerLane.get();
    }

    /**
     * Sets the average number of vehicles per lane.
     *
     * @param value The average number of vehicles per lane.
     */
    public void setAveragePerLane(final int value) {
        averagePerLane.set(value);
    }

    /**
     * The {@link IntegerProperty} utilized for JavaFX table parsing.
     *
     * @return The Integer property of the average number of vehicles per lane.
     */
    public IntegerProperty averagePerLaneProperty() {
        return averagePerLane;
    }

    /**
     * The average velocity represented as a SimpleIntegerProperty.
     */
    private final IntegerProperty averageVelocity = new SimpleIntegerProperty();

    /**
     * The integer representation of the average velocity of each vehicle.
     *
     * @return The average velocity for the report.
     */
    public Integer getAverageVelocity() {
        return averageVelocity.get();
    }

    /**
     * Sets the number for the average velocity of each vehicle.
     *
     * @param value The average velocity of each vehicle.
     */
    public void setAverageVelocity(final Integer value) {
        averageVelocity.set(value);
    }

    /**
     * The {@link IntegerProperty} utilized for JavaFX table parsing.
     *
     * @return The integer property for the average velocity of all vehicles.
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
    /**
     * The string index for the comma separated value for Time.
     */
    private static final int TIME_STRING_INDEX = 1;
    /**
     * The string index for the comma separated value for location.
     */
    private static final int LOCATION_STRING_INDEX = 2;
    /**
     * The string index for the comma separated value for number of lanes.
     */
    private static final int LANES_STRING_INDEX = 3;
    /**
     * The string index for the comma separated value for total number of
     * vehicles.
     */
    private static final int TOTAL_VEHICLE_STRING_INDEX = 4;
    /**
     * The string index for the comma separated value for the average number of
     * vehicles per lane.
     */
    private static final int AVERAGE_LANE_STRING_INDEX = 5;

    /**
     * The constructor for the Traffic object.
     *
     * @param timeInput The situated time for Traffic Recording.
     * @param locationInput The monitoring office utilized for recording the
     * report.
     * @param numLanesInput The total number of lanes in the report.
     * @param totalVehicleInput The total number of vehicles in the report.
     * @param averagePerLaneInput The average number of vehicles per lane in the
     * report.
     * @param averageVelocityInput The average velocity in km/h.
     */
    public Traffic(final LocalDateTime timeInput, final Location locationInput,
            final int numLanesInput, final int totalVehicleInput,
            final int averagePerLaneInput,
            final int averageVelocityInput) {
        this.location = new SimpleObjectProperty<>();
        setTime(timeInput);
        setLocation(locationInput);
        setNumLanes(numLanesInput);
        setTotalVehicle(totalVehicleInput);
        setAveragePerLane(averagePerLaneInput);
        setAverageVelocity(averageVelocityInput);
    }

    /**
     * The constructor for the Traffic object.
     */
    public Traffic() {
        this.location = new SimpleObjectProperty<>();
        this.setTime(LocalDateTime.now());
        this.setLocation(new Location(1, ""));
        this.setNumLanes(DEFAULT_LANES);
        this.setTotalVehicle(DEFAULT_VEHICLE);
        this.setAverageVelocity(DEFAULT_VEHICLE_PER_LANE);
        this.setAveragePerLane(DEFAULT_VEHICLE_VELOCITY);
    }

    @Override
    public String toString() {
        return FileIO.formatCSV(new String[]{
            "0",
            String.valueOf(getLocation()),
            getTime(),
            String.valueOf(getNumLanes()),
            String.valueOf(getTotalVehicle()),
            String.valueOf(getAveragePerLane()),
            String.valueOf(getAverageVelocity())
        });
    }

    /**
     * Parses the comma separated value string into a Traffic Object.
     *
     * @param lineInput The string representation of the CSV.
     * @return The traffic object from the CSV string.
     */
    public Traffic parseTrafficLine(final String lineInput) {
        String[] line = lineInput.split(",");
        Traffic temp = new Traffic();

        temp.setTime(LocalDateTime.parse(line[TIME_STRING_INDEX]));

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
     * Parses all lines from array list of comma separated values into the
     * Traffic objects.
     *
     * @param lineInput The list of CSV lines.
     * @return The list of traffic objects.
     */
    public ArrayList<Traffic> parseTrafficLines(
            final ArrayList<String> lineInput) {
        ArrayList<Traffic> temp = new ArrayList<>();

        lineInput.subList(1, lineInput.size()).forEach((line) -> {
            System.out.println(line);
            temp.add(parseTrafficLine(line));
        });
        return temp;
    }
}
