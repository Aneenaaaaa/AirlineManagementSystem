package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Passenger data model to store each row’s data
public class PassengerController {
    @FXML
    private TableView<Passenger> passengerTable;
    @FXML
    private TableColumn<Passenger, String> passengerNameColumn;
    @FXML
    private TableColumn<Passenger, String> flightNameColumn;
    @FXML
    private TableColumn<Passenger, String> departureColumn;
    @FXML
    private TableColumn<Passenger, String> destinationColumn;
    @FXML
    private TableColumn<Passenger, String> dayColumn;
    @FXML
    private TableColumn<Passenger, String> timeColumn;
    @FXML
    private TableColumn<Passenger, String> ticketPriceColumn;
    @FXML
    private Button closepassenger;

    private ObservableList<Passenger> passengerList = FXCollections.observableArrayList();

    public void initialize() {
        // Set up the columns to match the Passenger properties
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        // Load data from the database
        loadPassengerData();
    }

    // Method to load data from passengertable and add it to the TableView
    private void loadPassengerData() {
        String query = "SELECT Username, `Flight Name`, departure, destination, day, Time, Ticketprice FROM passengertable";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "#anu@123");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Passenger passenger = new Passenger(
                        resultSet.getString("Username"),
                        resultSet.getString("Flight Name"),
                        resultSet.getString("departure"),
                        resultSet.getString("destination"),
                        resultSet.getString("day"),
                        resultSet.getString("Time"),
                        resultSet.getString("Ticketprice")
                );
                passengerList.add(passenger);
            }

            passengerTable.setItems(passengerList);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading passenger data: " + e.getMessage());
        }
    }
    @FXML
    public void closepassengerOnAction( ActionEvent event) {
        // Close the current window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
