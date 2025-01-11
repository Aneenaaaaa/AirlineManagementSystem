package com.proj.log;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Hticketconfirmed {
    @FXML
    private Button Payment;

    @FXML
    private Button ticketBack;
    @FXML
    public void Payment_Click() throws IOException {
        Stage stage=(Stage) Payment.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();

        Parent root= FXMLLoader.load(getClass().getResource("Payment.fxml"));
        primaryStage.setTitle("Show Flights");
        primaryStage.setScene(new Scene(root,1000,500));
        primaryStage.show();
    }

    @FXML
    public void ticketGoBack() throws IOException {
        Stage stage=(Stage) ticketBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();

        Parent root= FXMLLoader.load(getClass().getResource("FlightDetailConfirm.fxml"));
        primaryStage.setTitle("Show Flights");
        primaryStage.setScene(new Scene(root,1000,500));
        primaryStage.show();
}
}