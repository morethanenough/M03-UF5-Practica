package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultsController {
    @FXML
    private Button resultButton;

    /*
    @FXML
    public void initialize() throws IOException {
        Stage stage = (Stage) resultButton.getScene().getWindow();
        if (stage.getTitle().equals("Paper, Rock and Scissor Contest - Game Won")) {
            resultButton.setText("Continuar");
            resultButton.setOnAction(e -> {
                try {
                    changeOfScene();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } else {
            resultButton.setText("Volver al menú principal");
            resultButton.setOnAction(e -> {
                try {
                    backToMenu();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

        }
    }
     */

    public void changeOfScene() throws IOException {
        Stage stage = (Stage) resultButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Playing");
        stage.setScene(scene);
        stage.show();
    }

    public void backToMenu() throws IOException {
        Stage stage = (Stage) resultButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.setScene(scene);
        stage.show();
    }
}
