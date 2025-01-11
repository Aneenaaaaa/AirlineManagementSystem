package com.proj.log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Home{
    @FXML
    private Button loginBtn;

    @FXML


    // This method will be linked to the button in your FXML
    public void login_page() throws IOException{
        Stage stage=(Stage) loginBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("Adminoruser.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
}
}