package com.proj.log;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFlightController {

    @FXML
    private TextField flightIdField;
    @FXML
    private TextField flightNameField;
    @FXML
    private TextField originField;
    @FXML
    private TextField destinationField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField ticketPriceField;

    private Flight selectedFlight;

    public void setFlight(Flight flight) {
        this.selectedFlight = flight;
        // Prefill fields with the flight's existing data
        flightIdField.setText(String.valueOf(flight.getFlightId()));
        flightNameField.setText(flight.getFlightName());
        originField.setText(flight.getOrigin());
        destinationField.setText(flight.getDestination());
        timeField.setText(flight.getTime());
        dayField.setText(flight.getDays());
        ticketPriceField.setText(String.valueOf(flight.getTicketPrice()));
    }

    @FXML
    private void updateButtonOnAction() {
        // Gather updated data from fields
        selectedFlight.setFlightName(flightNameField.getText());
        selectedFlight.setOrigin(originField.getText());
        selectedFlight.setDestination(destinationField.getText());
        selectedFlight.setTime(timeField.getText());
        selectedFlight.setDays(dayField.getText());
        selectedFlight.setTicketPrice(Integer.parseInt(ticketPriceField.getText()));

        // Update flight in the database
        FlightDAO.updateFlight(selectedFlight);

        // Close the window after updating
        Stage stage = (Stage) flightIdField.getScene().getWindow();
        stage.close();
    }
}
