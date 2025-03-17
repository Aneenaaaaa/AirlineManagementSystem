package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.proj.log.DatabaseConnection.getConnection;

public class FlightDAO {

    // Method to retrieve flights by date from the database
    public static ObservableList<Flight> getFlightsByDate(LocalDate date) {
        ObservableList<Flight> flightsList = FXCollections.observableArrayList();
        String query = "SELECT flight_id, flight_name, departure, destination, date, departure_time FROM flight_airline WHERE date = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the date parameter to filter by the selected date
            pstmt.setDate(1, java.sql.Date.valueOf(date));

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and create Flight objects
            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flight_name"),
                        rs.getString("departure"),
                        rs.getString("destination"),
                        date,  // Use the LocalDate passed to the method
                        rs.getString("departure_time")
                );
                flightsList.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightsList;
    }
}