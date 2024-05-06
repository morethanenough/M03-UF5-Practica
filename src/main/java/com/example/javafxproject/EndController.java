package com.example.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Jugador;

import java.io.IOException;

public class EndController {
    @FXML
    private Label score;
    @FXML
    private Button replay;
    @FXML
    private Button quit;

    public void initialize() {
        score.setText("" + DataSingleton.getInstance().getJugador().getPuntuacion());
    }

    @FXML
    protected void replayGame(ActionEvent actionEvent) throws IOException {
        Stage stage = DataSingleton.getInstance().getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void quitApp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
