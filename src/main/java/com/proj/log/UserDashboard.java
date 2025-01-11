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

public class UserDashboard {
    @FXML
    private Button cancelticketbutton;
    @FXML
    private Button bookticketbutton;
    // Ensure this is annotated with @FXML

    @FXML
    private Button showticketbutton;

    @FXML
    public void Onclickusershowticket(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userticket.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1000, 700);

            Stage stage = new Stage();
            stage.setTitle("Cancel Ticket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load the Cancel Ticket window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    public void handleCancelTicketButton(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Usercancelticket.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1000, 700);

            Stage stage = new Stage();
            stage.setTitle("Cancel Ticket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load the Cancel Ticket window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void OnActionbookticket(ActionEvent ev) {  // Action method for booking ticket
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hbookticket.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1000, 700);  // Set dimensions as needed

            Stage stage = new Stage();
            stage.setTitle("Book Ticket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load the Book Ticket window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handleSignOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText(null);
        alert.setContentText("You have signed out.");
        alert.showAndWait();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Userlogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Could not load User Dashboard.");
            alertError.setContentText(e.getMessage());
            alertError.showAndWait();
        }
    }

    // Other methods (commented out for future use)
    /*
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
    public void exitOnAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    */
}
