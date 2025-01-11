package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AddFlightController {

    @FXML
    private TextField flightIDField;
    @FXML
    private TextField flightNumberField;
    @FXML
    private TextField originField;
    @FXML
    private TextField destinationField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField ticketPriceField; // Field for ticket price
    @FXML
    private Label warningLabel;

    @FXML
    public void addFlightOnAction(ActionEvent event) {
        try {
            // Get values from TextFields
            String flightIDText = flightIDField.getText();
            String flightNumber = flightNumberField.getText();
            String origin = originField.getText();
            String destination = destinationField.getText();
            String day = dayField.getText();
            String time = timeField.getText();
            String ticketPriceText = ticketPriceField.getText(); // Get ticket price input

            // Check if any fields are empty
            if (flightIDText.isEmpty() || flightNumber.isEmpty() || origin.isEmpty() ||
                    destination.isEmpty() || day.isEmpty() || time.isEmpty() || ticketPriceText.isEmpty()) {
                warningLabel.setText("Fill all the details!!!");
                warningLabel.setStyle("-fx-text-fill: red;");
                return; // Exit the method
            }

            // Convert flightID and ticketPrice from String to Integer
            int flightID = Integer.parseInt(flightIDText);
            int ticketPrice = Integer.parseInt(ticketPriceText); // Convert ticket price to integer

            // Create a new Flight object
            Flight newFlight = new Flight(flightID, flightNumber, origin, destination, time, day, ticketPrice);

            // Add the flight to the database
            FlightDAO flightDAO = new FlightDAO();
            flightDAO.addFlight(newFlight); // Ensure this method handles ticket price

            // Optionally, reset the fields or show success message
            warningLabel.setText("Flight added successfully!");
            warningLabel.setStyle("-fx-text-fill: green;");
            clearFormFields(); // Reset fields after successful addition

            // Close the window after adding the flight
            closeWindow(event);

        } catch (NumberFormatException e) {
            warningLabel.setText("Flight ID and Ticket Price must be valid integers!!!");
            warningLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions
        }
    }

    // Method to clear the form fields after adding the flight
    private void clearFormFields() {
        flightIDField.clear();
        flightNumberField.clear();
        originField.clear();
        destinationField.clear();
        dayField.clear();
        timeField.clear();
        ticketPriceField.clear(); // Clear ticket price field
    }

    // Method to close the current window
    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
