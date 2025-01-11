package com.proj.log;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userticket {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField flightField;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private TextField price;

    public void initialize() {
        loadLatestPassengerData();
    }

    private void loadLatestPassengerData() {
        String query = "SELECT Username, `Flight Name`, departure, destination, day, Time, Ticketprice FROM passengertable ORDER BY Time DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "#anu@123");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                usernameField.setText(resultSet.getString("Username"));
                flightField.setText(resultSet.getString("Flight Name"));
                fromField.setText(resultSet.getString("departure"));
                toField.setText(resultSet.getString("destination"));
                price.setText(resultSet.getString("Ticketprice"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading latest passenger data: " + e.getMessage());
        }
    }
}
