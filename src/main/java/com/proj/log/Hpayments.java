package com.proj.log;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Hpayments {
    @FXML
    private TextField flightNameField;
    @FXML
    private TextField ticketPriceField;
    @FXML
    private TextField passengerField;

    // Method called to initialize the passenger name
    public void initialize() {
        passengerField.setText("Aneena");  // Setting passenger name as "Aneena"
    }

    // Method to set the flight details received from Hconfirmselectedflight
    public void setFlightDetails(String flightName, int ticketPrice) {
        flightNameField.setText(flightName);
        ticketPriceField.setText(String.valueOf(ticketPrice));
    }

    // Method to handle the "pay" action
    @FXML
    private void onPay() {
        // Show confirmation popup
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Confirm payment?", ButtonType.OK, ButtonType.CANCEL);
        confirmationAlert.setHeaderText(null);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // If "OK" is clicked, store data in the database
                storePaymentDetails();
            }
        });
    }

    // Method to store payment details in the database
    private void storePaymentDetails() {
        String username = passengerField.getText();
        String flightName = flightNameField.getText();
        String ticketPrice = ticketPriceField.getText();
        String departure = "";
        String destination = "";
        String day = "";
        String time = "";

        // SQL query to fetch origin, destination, day, and time based on flight name
        String fetchFlightDetailsSQL = "SELECT origin, destination, Day, Time FROM availableflights WHERE flightname = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "#anu@123")) {
            // Fetch flight details
            try (PreparedStatement fetchStatement = connection.prepareStatement(fetchFlightDetailsSQL)) {
                fetchStatement.setString(1, flightName);
                ResultSet resultSet = fetchStatement.executeQuery();

                if (resultSet.next()) {
                    departure = resultSet.getString("origin");
                    destination = resultSet.getString("destination");
                    day = resultSet.getString("Day");
                    time = resultSet.getString("Time");
                } else {
                    System.out.println("No flight details found for flight name: " + flightName);
                    return;
                }
            }

            // Insert payment details into passengertable
            String insertSQL = "INSERT INTO passengertable (Username, `Flight Name`, departure, destination, day, Time, Ticketprice) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, flightName);
                insertStatement.setString(3, departure);
                insertStatement.setString(4, destination);
                insertStatement.setString(5, day);
                insertStatement.setString(6, time);
                insertStatement.setString(7, ticketPrice);

                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Payment details stored successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error storing payment details: " + e.getMessage());
        }
    }
}
