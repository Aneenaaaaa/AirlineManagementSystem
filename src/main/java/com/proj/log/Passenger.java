package com.proj.log;

public class Passenger {
    private String username;
    private String flightName;
    private String departure;
    private String destination;
    private String day;
    private String time;
    private String ticketPrice;

    public Passenger(String username, String flightName, String departure, String destination, String day, String time, String ticketPrice) {
        this.username = username;
        this.flightName = flightName;
        this.departure = departure;
        this.destination = destination;
        this.day = day;
        this.time = time;
        this.ticketPrice = ticketPrice;
    }

    public String getUsername() {
        return username;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }
}
