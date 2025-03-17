package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.proj.log.DatabaseConnection.getConnection;

public class Filterall {

    @FXML
    private TableView<FlightBooking> tableview1; // Table for flight bookings
    @FXML
    private TableView<Passenger> tableview2; // Table for passenger details
    @FXML
    private TableColumn<FlightBooking, String> flightname; // Flight name column
    @FXML
    private TableColumn<FlightBooking, Integer> bookingcount; // Booking count column
    @FXML
    private TextField count; // TextField for highest booking flight name
    @FXML
    private TextField count1; // TextField for entering flight name

    @FXML
    private TableColumn<Passenger, String> passengername; // Passenger name column
    @FXML
    private TableColumn<Passenger, String> passportnumber; // Passport number column
    @FXML
    private TableColumn<Passenger, String> gender; // Gender column
// Text for displaying messages

    // Initialize method to set up the table and load data
    @FXML
    public void initialize() {
        // Set up the cell value factories for each TableColumn
        flightname.setCellValueFactory(cellData -> cellData.getValue().flightNameProperty());
        bookingcount.setCellValueFactory(cellData -> cellData.getValue().bookingCountProperty().asObject());

        // Set up the cell value factories for tableview2
        passengername.setCellValueFactory(cellData -> cellData.getValue().passengerNameProperty());
        passportnumber.setCellValueFactory(cellData -> cellData.getValue().passportNumberProperty());
        gender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        // Load initial data
        loadFlightBookingData();
        loadFlightWithHighestBookings();

        // Add listener to count1 TextField to fetch passenger details when a flight name is entered
        count1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                loadPassengerDetails(newValue);
            } else {
                tableview2.getItems().clear(); // Clear table if input is empty
            }
        });
    }

    // Method to load flight booking data into tableview1
    private void loadFlightBookingData() {
        ObservableList<FlightBooking> flightBookingList = FXCollections.observableArrayList();
        String query = "SELECT flight_name, COUNT(passenger_id) AS booking_count " +
                "FROM passenger_airline GROUP BY flight_name;";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FlightBooking flightBooking = new FlightBooking(
                        rs.getString("flight_name"),
                        rs.getInt("booking_count")
                );
                flightBookingList.add(flightBooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableview1.setItems(flightBookingList);
    }

    // Method to load the flight with the highest number of bookings
    private void loadFlightWithHighestBookings() {
        String query = "SELECT flight_name, COUNT(passenger_id) AS booking_count " +
                "FROM passenger_airline GROUP BY flight_name ORDER BY booking_count DESC LIMIT 1";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                String flightName = rs.getString("flight_name");
                count.setText(flightName); // Set the flight name with the highest bookings
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load passenger details based on the flight name entered in count1
    private void loadPassengerDetails(String flightName) {
        ObservableList<Passenger> passengerList = FXCollections.observableArrayList();
        String query = "SELECT passenger_name, passport_number, gender FROM passenger_airline WHERE flight_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, flightName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Passenger passenger = new Passenger(
                        rs.getString("passenger_name"),
                        rs.getString("passport_number"),
                        rs.getString("gender")
                );
                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableview2.setItems(passengerList);
    }
}