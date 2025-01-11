package com.proj.log;

public class Hflight {

    private int flightId;
    private String flightName;
    private String origin;
    private String destination;
    private String time;
    private String day;
    private int ticketPrice;

    // Constructor with all fields
    public Hflight(int flightId, String flightName, String origin, String destination, String time, String day, int ticketPrice) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.day = day;
        this.ticketPrice = ticketPrice;
    }

    // Getters
    public int getFlightId() { return flightId; }
    public String getFlightName() { return flightName; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getTime() { return time; }
    public String getDay() { return day; }
    public int getTicketPrice() { return ticketPrice; }
}
