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
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class Usersignup {

    @FXML
    private Button signupSubmit;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private PasswordField tfconfirmpassword;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_passportno;

    @FXML
    private TextField tf_dob; // Assume the date format is YYYY-MM-DD

    @FXML
    private TextField tf_phonenumber;

    public void signup_to_login() throws IOException {
        // Validate if all fields are filled
        if (tf_username.getText().isEmpty() || tf_email.getText().isEmpty() ||
                tf_passportno.getText().isEmpty() || tfpassword.getText().isEmpty() ||
                tfconfirmpassword.getText().isEmpty() || tf_dob.getText().isEmpty() ||
                tf_phonenumber.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill all the fields.");
            return;
        }

        // Check if passwords match
        if (!tfpassword.getText().equals(tfconfirmpassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match. Please try again.");
            return;
        }

        // Calculate age from date of birth
        LocalDate dob;
        int age;
        try {
            dob = LocalDate.parse(tf_dob.getText()); // Ensure input format is YYYY-MM-DD
            age = Period.between(dob, LocalDate.now()).getYears();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date Format", "Please enter a valid date of birth (YYYY-MM-DD).");
            return;
        }

        // Connect to the database
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Unable to connect to the database. Please check your connection settings.");
            return;
        }

        try {
            // Auto-generate user_id
            String getMaxIdQuery = "SELECT MAX(user_id) AS max_id FROM user_airline";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getMaxIdQuery);
            int newUserId = 1; // Default for first user
            if (rs.next() && rs.getInt("max_id") > 0) {
                newUserId = rs.getInt("max_id") + 1;
            }

            // Check if the user already exists
            String checkUserQuery = "SELECT * FROM user_airline WHERE user_emailid = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
            checkStmt.setString(1, tf_email.getText());

            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                showAlert(Alert.AlertType.ERROR, "User Exists", "This email is already registered. Please log in.");
            } else {
                // Insert new user
                String insertQuery = "INSERT INTO user_airline (user_id, user_name, user_password, user_emailid, user_dob, user_age, passport_no, user_phonenumber) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setInt(1, newUserId);
                insertStmt.setString(2, tf_username.getText());
                insertStmt.setString(3, tfpassword.getText());
                insertStmt.setString(4, tf_email.getText());
                insertStmt.setDate(5, Date.valueOf(dob)); // Convert LocalDate to SQL Date
                insertStmt.setInt(6, age);
                insertStmt.setString(7, tf_passportno.getText());
                insertStmt.setString(8, tf_phonenumber.getText());

                int result = insertStmt.executeUpdate();

                if (result > 0) {
                    // Show success alert and wait for the user to click OK
                    showAlert(Alert.AlertType.INFORMATION, "Success", "User signed up successfully!");

                    // Close current window (sign-up page)
                    Stage stage = (Stage) signupSubmit.getScene().getWindow();
                    stage.close();

                    // Open the login page after successful signup
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Userlogin.fxml"));
                    primaryStage.setTitle("Login");
                    primaryStage.setScene(new Scene(root, 1000, 500));
                    primaryStage.show();  // This will display the login window
                } else {
                    showAlert(Alert.AlertType.ERROR, "Signup Failed", "Failed to sign up. Please try again.");
                }
            }
        } catch (SQLException e) {
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
