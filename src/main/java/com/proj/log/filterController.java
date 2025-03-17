package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class filterController {

    @FXML
    private DatePicker date_picker;

    @FXML
    private TableView<Flight> flightTableView;

    @FXML
    private TableColumn<Flight, Integer> flight_number;

    @FXML
    private TableColumn<Flight, String> destination;

    @FXML
    private TableColumn<Flight, String> departure_time;

    @FXML
    private TableColumn<Flight, String> source;

    // Initialize method to set up the table columns and the date picker listener
    @FXML
    public void initialize() {
        // Set up the cell value factories for each TableColumn
        flight_number.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        departure_time.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        source.setCellValueFactory(new PropertyValueFactory<>("departure"));

        // Add listener to DatePicker to fetch data when a date is selected
        date_picker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadBookedFlightsByDate(newValue);
            }
        });
    }

    // Method to load booked flights based on the selected date
    private void loadBookedFlightsByDate(LocalDate date) {
        // Use FlightDAO to fetch booked flights from the database
        ObservableList<Flight> flightList = FlightDAO.getFlightsByDate(date); // Pass LocalDate directly

        // Set the TableView with the populated list
        flightTableView.setItems(flightList);
    }
}