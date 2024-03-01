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
import model.Ranking;

import java.io.IOException;

public class EndController {
    @FXML
    private Label score;
    @FXML
    private Button replay;
    @FXML
    private Button quit;

    private RankingController rankingController = new RankingController();

    public void initialize() {
        score.setText("" + DataSingleton.getInstance().getJugador().getPuntuacion());
        rankingController.ordenarRanking(DataSingleton.getInstance().getJugador().getNombre(), DataSingleton.getInstance().getJugador().getPuntuacion());
    }

    @FXML
    protected void replayGame(ActionEvent actionEvent) throws IOException {
        Stage stage = DataSingleton.getInstance().getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
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
