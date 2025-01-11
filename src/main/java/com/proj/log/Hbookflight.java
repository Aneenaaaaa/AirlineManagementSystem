package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Hbookflight {

    @FXML private TextField departureField;  // fx:id="departureField"
    @FXML private TextField destinationField; // fx:id="destinationField"
    @FXML private Button searchButton;       // fx:id="searchButton"

    @FXML
    private void onSearchButtonClick(ActionEvent event) {
        String departure = departureField.getText();
        String destination = destinationField.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proj/log/Hselectflight.fxml"));
            Parent root = loader.load();

            // Ensure the controller is fetched after loading the FXML
            Hselectflight controller = loader.getController();
            if (controller != null) {
                controller.loadAvailableFlights(departure, destination);

                Stage stage = (Stage) searchButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("Controller for Hselectflight.fxml is null.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Hselectflight.fxml: " + e.getMessage());
        }
    }
}
