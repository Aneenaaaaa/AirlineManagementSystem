package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.proj.log.DatabaseConnection.getConnection;

public class FlightDAO {


    // Method to retrieve all flights from the database
    public static ObservableList<Flight> getFlights() {
        ObservableList<Flight> flightsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM availableflights";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flightid"),
                        rs.getString("flightname"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("Time"),
                        rs.getString("Day"),
                        rs.getInt("ticketprice") // Added ticketprice
                );
                flightsList.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightsList;
    }


    // If you want a method that returns a List of flights (non-Observable)
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM availableflights";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flightid"),
                        rs.getString("flightname"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("Time"),
                        rs.getString("Day"),
                        rs.getInt("ticketprice") // Added ticketprice
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }



    // Method to insert a new flight into the database
    public static void addFlight(Flight flight) {
        String query = "INSERT INTO availableflights (flightid, flightname, origin, destination, Time, Day, ticketprice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flight.getFlightId());
            pstmt.setString(2, flight.getFlightName());
            pstmt.setString(3, flight.getOrigin());
            pstmt.setString(4, flight.getDestination());
            pstmt.setString(5, flight.getTime());
            pstmt.setString(6, flight.getDays());
            pstmt.setInt(7, flight.getTicketPrice()); // Added ticketprice

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to delete a flight based on flight ID
    public boolean deleteFlight(int flightId) {
        String deleteSQL = "DELETE FROM availableflights WHERE flightid = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setInt(1, flightId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to update an existing flight's details
    // Method to update an existing flight's details
    public static void updateFlight(Flight flight) {
        String query = "UPDATE availableflights SET flightname = ?, origin = ?, destination = ?, Time = ?, Day = ?, ticketprice = ? WHERE flightid = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, flight.getFlightName());
            pstmt.setString(2, flight.getOrigin());
            pstmt.setString(3, flight.getDestination());
            pstmt.setString(4, flight.getTime());
            pstmt.setString(5, flight.getDays());
            pstmt.setInt(6, flight.getTicketPrice()); // Added ticketprice
            pstmt.setInt(7, flight.getFlightId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to get a flight by its ID (optional, if needed for pre-filling)
    public Flight getFlightById(int flightId) {
        String query = "SELECT * FROM availableflights WHERE flightid = ?";
        Flight flight = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, flightId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    flight = new Flight(
                            rs.getInt("flightid"),
                            rs.getString("flightname"),
                            rs.getString("origin"),
                            rs.getString("destination"),
                            rs.getString("Time"),
                            rs.getString("Day"),
                            rs.getInt("ticketprice")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flight;
    }


}
