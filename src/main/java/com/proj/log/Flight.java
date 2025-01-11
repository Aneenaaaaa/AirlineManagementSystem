package com.proj.log;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Flight {
    private Integer flightId;
    private String flightName;
    private String origin;
    private String destination;
    private String time;
    private String days;
    private final IntegerProperty ticketPrice; // Use JavaFX IntegerProperty

    // Constructor with parameters
    public Flight(Integer flightId, String flightName, String origin, String destination, String time, String days, int ticketPrice) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.days = days;
        this.ticketPrice = new SimpleIntegerProperty(ticketPrice); // Initialize ticketPrice
    }

    // No-argument constructor for retrieval
    public Flight() {
        this.ticketPrice = new SimpleIntegerProperty(); // Initialize with a default value
    }

    // Getters and Setters
    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getTicketPrice() {
        return ticketPrice.get(); // Return the value of the property
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice.set(ticketPrice); // Set the value of the property
    }

    public IntegerProperty ticketPriceProperty() { // Add this method for property binding
        return ticketPrice;
    }

  /*  public  getAirline() {
    }*/
}
