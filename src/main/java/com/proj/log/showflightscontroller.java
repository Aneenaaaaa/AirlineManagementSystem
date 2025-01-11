package com.proj.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class showflightscontroller {

    @FXML
    private Button addflightbutton;

    @FXML
    private TableView<Flight> flightTable;

    @FXML
    private TableColumn<Flight, Integer> flightidc;
    @FXML
    private TableColumn<Flight, String> flightnamec;
    @FXML
    private TableColumn<Flight, String> originc;
    @FXML
    private TableColumn<Flight, String> destinationc;
    @FXML
    private TableColumn<Flight, String> timec;
    @FXML
    private TableColumn<Flight, String> dayc;
    @FXML
    private TableColumn<Flight, Integer> ticketpricec;

    @FXML
    private Button deleteflightbutton;
    @FXML
    private Button editflightbutton;

    public showflightscontroller() {
    }

    @FXML
    public void initialize() {
        // Set up the columns in the TableView to map to the Flight model properties
        flightidc.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        flightnamec.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        originc.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationc.setCellValueFactory(new PropertyValueFactory<>("destination"));
        timec.setCellValueFactory(new PropertyValueFactory<>("time"));
        dayc.setCellValueFactory(new PropertyValueFactory<>("days"));
        ticketpricec.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

        // Fetch data from MySQL and populate the table
        refreshFlightList();

        // Disable the delete and edit buttons initially
        deleteflightbutton.setDisable(true);
        editflightbutton.setDisable(true);

        // Add a listener to enable the delete and edit buttons when a row is selected
        flightTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isSelected = newSelection != null;
            deleteflightbutton.setDisable(!isSelected);
            editflightbutton.setDisable(!isSelected);
        });
    }

    public void refreshFlightList() {
        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flights = flightDAO.getAllFlights();

        // Clear the current table and add the new list of flights
        flightTable.getItems().clear();
        flightTable.getItems().addAll(flights);
    }

    @FXML
    public void addFlightButtonOnAction(ActionEvent ev) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_flight.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);

            Stage stage = new Stage();
            stage.setTitle("Add Flight");
            stage.setScene(scene);
            stage.showAndWait();

            refreshFlightList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteFlightButtonOnAction(ActionEvent event) {
        Flight selectedFlight = flightTable.getSelectionModel().getSelectedItem();

        if (selectedFlight != null) {
            FlightDAO flightDAO = new FlightDAO();
            boolean success = flightDAO.deleteFlight(selectedFlight.getFlightId());

            if (success) {
                flightTable.getItems().remove(selectedFlight);
            } else {
                System.out.println("Error: Could not delete flight.");
            }
        }
    }

    @FXML
    public void editFlightButtonOnAction(ActionEvent event) {
        // Get the selected flight from the TableView
        Flight selectedFlight = flightTable.getSelectionModel().getSelectedItem();

        if (selectedFlight != null) {
            try {
                // Load the EditFlight.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditFlight.fxml"));
                Stage editStage = new Stage();
                editStage.setTitle("Edit Flight");
                editStage.setScene(new Scene(loader.load()));
                editStage.initModality(Modality.APPLICATION_MODAL);

                // Get controller and set selected flight
                EditFlightController controller = loader.getController();
                controller.setFlight(selectedFlight);

                // Show the edit window
                editStage.showAndWait();

                // Refresh the table after editing
                refreshFlightList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
