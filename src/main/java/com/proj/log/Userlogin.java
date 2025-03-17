package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userlogin {

    @FXML
    private Hyperlink signup;

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label loginMessageLabel;

    // Initialize the DatabaseConnection
    private DatabaseConnection connectNow = new DatabaseConnection();

    // Method for login button action
    public void loginButtonOnAction(ActionEvent e) {
        // Check if the username and password fields are blank
        if (tf_username.getText().isBlank() || tf_password.getText().isBlank()) {
            showAlert("Input Error", "Please enter both username and password.");
            return; // Exit the method if fields are empty
        }

        loginMessageLabel.setText("Attempting to login...");
        validateLogin();  // Call login validation
    }

    public void validateLogin() {
        Connection connectDB = connectNow.getConnection();

        if (connectDB == null) {
            showAlert("Database Error", "Unable to connect to the database.");
            return;
        }

        // SQL query to validate username and password
        String verifyLogin = "SELECT * FROM user_airline WHERE user_name = ? AND user_password = ?";

        try (PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin)) {
            preparedStatement.setString(1, tf_username.getText());
            preparedStatement.setString(2, tf_password.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                loginMessageLabel.setText("Login successful! Welcome, " + queryResult.getString("user_name") + ".");
                loadUserDashboard(); // Call method to load the User Dashboard
            } else {
                loginMessageLabel.setText("Invalid login. Please try again.");
                showAlert("Login Failed", "Incorrect username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print detailed error
            showAlert("Database Error", "An error occurred: " + e.getMessage());
        }
    }

    // Method to load the UserDashboard
    private void loadUserDashboard() {
        try {
            // Get the current stage
            Stage currentStage = (Stage) button_login.getScene().getWindow();

            // Load the UserDashboard FXML
            Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));

            // Create a new scene with the loaded root
            Scene userDashboardScene = new Scene(root, 1000, 700);

            // Set the scene to the current stage
            currentStage.setScene(userDashboardScene);
            currentStage.setTitle("User Dashboard"); // Optionally set the title
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Loading Error", "Could not load the User Dashboard.");
        }
    }

    // Method to show an alert in case of invalid login
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to redirect to the signup page
    public void user_signup_page() throws IOException {
        Stage stage = (Stage) signup.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Usersignup.fxml"));
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }
}
