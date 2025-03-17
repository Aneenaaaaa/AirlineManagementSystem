package com.proj.log;

import javafx.beans.property.SimpleStringProperty;

public class Passenger{
    private final SimpleStringProperty passengerName;   // Property for passenger name
    private final SimpleStringProperty passportNumber;   // Property for passport number
    private final SimpleStringProperty gender;           // Property for gender

    // Constructor
    public Passenger(String passengerName, String passportNumber, String gender) {
        this.passengerName = new SimpleStringProperty(passengerName);
        this.passportNumber = new SimpleStringProperty(passportNumber);
        this.gender = new SimpleStringProperty(gender);
    }

    // Getter for passenger name property
    public SimpleStringProperty passengerNameProperty() {
        return passengerName;
    }

    // Getter for passport number property
    public SimpleStringProperty passportNumberProperty() {
        return passportNumber;
    }

    // Getter for gender property
    public SimpleStringProperty genderProperty() {
        return gender;
    }

    // Optional: Getters for the actual values (if needed)
    public String getPassengerName() {
        return passengerName.get();
    }

    public String getPassportNumber() {
        return passportNumber.get();
    }

    public String getGender() {
        return gender.get();
    }
}