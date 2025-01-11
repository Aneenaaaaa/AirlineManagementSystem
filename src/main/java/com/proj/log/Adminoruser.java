package com.proj.log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;


import java.io.IOException;

public class Adminoruser {
    @FXML
    private Button btnUser;
    @FXML
    private Button adminBtn;

    // This method will be linked to the button in your FXML
    public void user_login_page() throws IOException {
        Stage stage=(Stage) btnUser.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("Userlogin.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
    }
    public void admin_login_page() throws IOException {
        Stage stage=(Stage) adminBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root,700,600));
        primaryStage.show();
}
}