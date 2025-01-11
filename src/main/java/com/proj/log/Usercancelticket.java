package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usercancelticket {

    @FXML
    private Button cancel;
    @FXML
    private Button goback;

    @FXML
    private TextField nameField;
    @FXML
    private TextField flightField;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private TextField priceField;

    // Initialize and load latest data
    public void initialize() {
        loadLatestPassengerData();
    }

    private void loadLatestPassengerData() {
        String query = "SELECT Username, `Flight Name`, departure, destination, Ticketprice FROM passengertable ORDER BY Time DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "#anu@123");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("Username"));
                flightField.setText(resultSet.getString("Flight Name"));
                fromField.setText(resultSet.getString("departure"));
                toField.setText(resultSet.getString("destination"));
                priceField.setText(resultSet.getString("Ticketprice"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading latest passenger data: " + e.getMessage());
        }
    }

    @FXML
    public void handleCancelTicket(ActionEvent event) {
        deleteLatestTicket();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancellation");
        alert.setHeaderText(null);
        alert.setContentText("Ticket has been cancelled successfully.");
        alert.showAndWait();

        // Close the current window
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    private void deleteLatestTicket() {
        String selectQuery = "SELECT Username, `Flight Name`, departure, destination, day, Time, Ticketprice FROM passengertable ORDER BY Time DESC LIMIT 1";
        String deleteQuery = "DELETE FROM passengertable WHERE Username = ? AND `Flight Name` = ? AND departure = ? AND destination = ? AND day = ? AND Time = ? AND Ticketprice = ? LIMIT 1";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "#anu@123");
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {

            if (resultSet.next()) {
                // Retrieve values of the latest entry
                String username = resultSet.getString("Username");
                String flightName = resultSet.getString("Flight Name");
                String departure = resultSet.getString("departure");
                String destination = resultSet.getString("destination");
                String day = resultSet.getString("day");
                String time = resultSet.getString("Time");
                String ticketPrice = resultSet.getString("Ticketprice");

                // Prepare delete statement with the retrieved values
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                    deleteStatement.setString(1, username);
                    deleteStatement.setString(2, flightName);
                    deleteStatement.setString(3, departure);
                    deleteStatement.setString(4, destination);
                    deleteStatement.setString(5, day);
                    deleteStatement.setString(6, time);
                    deleteStatement.setString(7, ticketPrice);

                    // Execute the delete operation
                    deleteStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting latest ticket: " + e.getMessage());
        }
    }

    @FXML
    public void OnActiongoback(ActionEvent event) {
        Stage currentStage = (Stage) goback.getScene().getWindow();
        currentStage.close();
    }
}
