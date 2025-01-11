package com.proj.log;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hselectflight {

    @FXML
    private TableView<Hflight> flightsTableView;
    @FXML
    private TableColumn<Hflight, Integer> flightIdColumn;
    @FXML
    private TableColumn<Hflight, String> flightNameColumn;
    @FXML
    private TableColumn<Hflight, String> originColumn;
    @FXML
    private TableColumn<Hflight, String> destinationColumn;
    @FXML
    private TableColumn<Hflight, String> timeColumn;
    @FXML
    private TableColumn<Hflight, String> dayColumn;
    @FXML
    private TableColumn<Hflight, Integer> ticketPriceColumn;

    @FXML
    public void initialize() {
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
    }

    public void loadAvailableFlights(String departure, String destination) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM availableflights WHERE origin = ? AND destination = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, departure);
            statement.setString(2, destination);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hflight flight = new Hflight(
                        resultSet.getInt("flightid"),
                        resultSet.getString("flightname"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getString("Time"),
                        resultSet.getString("Day"),
                        resultSet.getInt("ticketprice")
                );
                flightsTableView.getItems().add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentScreen(Hflight selectedFlight) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dummy/Payment.fxml"));
            Parent root = loader.load();

            Hpayments paymentController = loader.getController();
            paymentController.setFlightDetails(selectedFlight.getFlightName(), selectedFlight.getTicketPrice());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onFlightSelected(MouseEvent event) {
        // Get selected flight
        Hflight selectedFlight = flightsTableView.getSelectionModel().getSelectedItem();

        if (selectedFlight != null) {
            loadConfirmSelectedFlight(selectedFlight);
        }
    }

    private void loadConfirmSelectedFlight(Hflight selectedFlight) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/proj/log/Hconfirmselectedflight.fxml"));
            Parent root = loader.load();

            // Pass selected flight details to Hconfirmselectedflight controller
            Hconfirmselectedflight controller = loader.getController();
            controller.fillFlightDetails(selectedFlight); // Pass selected flight

            Stage stage = (Stage) flightsTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Hconfirmselectedflight.fxml: " + e.getMessage());
        }
    }

}
