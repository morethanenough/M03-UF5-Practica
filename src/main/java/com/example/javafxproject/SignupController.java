package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import model.User;

import java.io.IOException;

public class SignupController {

    @FXML private TextField textField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    public void onButtonClick(ActionEvent event) throws IOException {

        if (textField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
            errorLabel.setText("Please fill all fields");
            return;
        } else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errorLabel.setText("Passwords do not match");
            return;
        } else {
            if (User.createUser(textField.getText(), passwordField.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                DataSingleton.getInstance().setStage(stage);
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 360);
                stage.setTitle("Paper, Rock and Scissor Contest - Main Menu");
                stage.setScene(scene);
                stage.show();
            } else {
                errorLabel.setText("User couldn't be created. It may already exist, so try another name." + textField.getText() + passwordField.getText() + confirmPasswordField.getText());
            }
        }
    }

    public void goToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DataSingleton.getInstance().setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Login");
        stage.setScene(scene);
        stage.show();
    }

}
