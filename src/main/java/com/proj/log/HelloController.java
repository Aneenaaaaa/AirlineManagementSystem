package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button showflightsbutton;

    @FXML
    public void showflightsbuttonOnAction(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showflights.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);

            Stage stage = new Stage();
            stage.setTitle("Show Flights");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void passengerbuttonOnAction(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passengerlist.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 705, 505);

            Stage stage = new Stage();
            stage.setTitle("Passenger List");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openAddFlightWindow(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addflight.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 705, 505);

            Stage stage = new Stage();
            stage.setTitle("Add Flight");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signoutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText(null);
        alert.setContentText("You have signed out.");
        alert.showAndWait();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminlogin.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void filterbuttonOnAction(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filter_flight_basedondate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 705, 505);

            Stage stage = new Stage();
            stage.setTitle("Filter Flights Based on Date");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New function to open filterall.fxml
    @FXML
    public void filterallOnAction(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("filterall.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 705, 505);

            Stage stage = new Stage();
            stage.setTitle("Filter All Flights");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
