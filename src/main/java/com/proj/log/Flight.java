package com.proj.log;

import java.time.LocalDate;

public class Flight {
    private int flightId;          // Corresponds to flight_id in the database
    private String flightName;     // Corresponds to flight_name in the database
    private String departure;      // Corresponds to departure in the database
    private String destination;    // Corresponds to destination in the database
    private LocalDate date;        // Corresponds to date in the database
    private String departureTime;  // Corresponds to departure_time in the database

    // Constructor
    public Flight(int flightId, String flightName, String departure, String destination, LocalDate date, String departureTime) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
    }

    // Getters and Setters
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}