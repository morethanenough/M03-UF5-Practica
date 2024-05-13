package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class LoginController {

    @FXML private TextField textField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;


    public void onButtonClick(ActionEvent event) throws IOException {

        if (textField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorLabel.setText("Please fill all fields");
            return;
        } else {
            if (User.login(textField.getText(), passwordField.getText()) != 0) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                DataSingleton.getInstance().setStage(stage);
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 360);
                stage.setTitle("Paper, Rock and Scissor Contest - Main Menu");
                stage.setScene(scene);
                stage.show();
            } else {
                errorLabel.setText("The name or password is incorrect");
                /*if (!textField.getText().equals(User.readUser())) {
                    errorLabel.setText("Username do not match");
                    return;
                } else {
                    errorLabel.setText("Password is incorrect");
                    return;
                }*/
            }
        }
    }

    public void goToSignup(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DataSingleton.getInstance().setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Sign Up");
        stage.setScene(scene);
        stage.show();
    }

}
