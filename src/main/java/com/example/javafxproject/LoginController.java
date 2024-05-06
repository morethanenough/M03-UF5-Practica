package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    public void onButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DataSingleton.getInstance().setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Main Menu");
        stage.setScene(scene);
        stage.show();
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
