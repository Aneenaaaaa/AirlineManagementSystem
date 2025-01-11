package com.proj.log;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usersignup {

    @FXML
    private Button signupSubmit;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_mail;

    @FXML
    private TextField passport;  // New field for passport number

    @FXML
    private PasswordField tf_password;

    @FXML
    private PasswordField tf_confirmPassword;

    public void signup_to_login() throws IOException {
        // Validate if all fields are filled
        if (tf_username.getText().isEmpty() || tf_mail.getText().isEmpty() ||
                passport.getText().isEmpty() || tf_password.getText().isEmpty() ||
                tf_confirmPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all the fields.");
            return;
        }

        // Check if passwords match
        if (!tf_password.getText().equals(tf_confirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match. Please try again.");
            return;
        }

        // Connect to the database
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Unable to connect to the database. Please check your connection settings.");
            return;
        }

        try {
            // Check if the user already exists
            String checkUserQuery = "SELECT * FROM userstable WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
            checkStmt.setString(1, tf_mail.getText());

            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                showAlert(Alert.AlertType.ERROR, "User Exists", "This user already exists. Please log in.");
            } else {
                // Insert new user
                String insertQuery = "INSERT INTO userstable (first_name, email, passport_no, password) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, tf_username.getText());
                insertStmt.setString(2, tf_mail.getText());
                insertStmt.setString(3, passport.getText()); // Add passport field
                insertStmt.setString(4, tf_password.getText());

                int result = insertStmt.executeUpdate();

                if (result > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "User signed up successfully!");

                    // Close current window and open login window
                    Stage stage = (Stage) signupSubmit.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("user_login.fxml"));
                    primaryStage.setTitle("Login");
                    primaryStage.setScene(new Scene(root, 1000, 500));
                    primaryStage.show();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Signup Failed", "Failed to sign up. Please try again.");
                }
            }
        } catch (SQLException e) {
            // Log the exception details for debugging
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while accessing the database: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
}
}