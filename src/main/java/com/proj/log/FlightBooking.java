package com.proj.log;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FlightBooking {
    private final SimpleStringProperty flightName; // Flight name property
    private final SimpleIntegerProperty bookingCount; // Booking count property

    public FlightBooking(String flightName, int bookingCount) {
        this.flightName = new SimpleStringProperty(flightName);
        this.bookingCount = new SimpleIntegerProperty(bookingCount);
    }

    public SimpleStringProperty flightNameProperty() {
        return flightName;
    }

    public SimpleIntegerProperty bookingCountProperty() {
        return bookingCount;
    }
}