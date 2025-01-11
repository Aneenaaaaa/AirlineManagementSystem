package com.proj.log;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Hconfirmselectedflight {

    @FXML private TextField flightNameLabel;       // fx:id="flightNameLabel"
    @FXML private TextField originLabel;           // fx:id="originLabel"
    @FXML private TextField destinationLabel;      // fx:id="destinationLabel"
    @FXML private TextField
            passengerLabel;
    // fx:id="passengerLabel"
    @FXML
    private Button Confirm_Ticket;

    @FXML
    private void onConfirmTicket() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proj/log/Hpayment.fxml"));
            Parent paymentRoot = loader.load();

            // Retrieve the Hpayments controller and pass flight details
            Hpayments paymentController = loader.getController();
            paymentController.setFlightDetails(flightName, ticketPrice);
            // Open in the same stage
            Stage currentStage = (Stage) Confirm_Ticket.getScene().getWindow();
            currentStage.setScene(new Scene(paymentRoot));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Hpayment.fxml: " + e.getMessage());
        }
    }


    // This method is called to fill the labels with flight details
    private String flightName;
    private int ticketPrice;

    // Modify the fillFlightDetails to also store these details
    public void fillFlightDetails(Hflight selectedFlight) {
        // Set flight details
        flightName = selectedFlight.getFlightName();
        ticketPrice = selectedFlight.getTicketPrice();

        // Fill UI fields
        flightNameLabel.setText(flightName);
        originLabel.setText(selectedFlight.getOrigin());
        destinationLabel.setText(selectedFlight.getDestination());

        // Set passenger name
        passengerLabel.setText("Aneena");
    }

}
