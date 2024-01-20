package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField nameInput;

    DataSingleton data = DataSingleton.getInstance();

    private DataSingleton nombreJugador = DataSingleton.getInstance();

    @FXML
    protected void onButtonClick() throws IOException {
        nombreJugador.setUserName(nameInput.getText());
        Stage stage = (Stage) nameInput.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }
}