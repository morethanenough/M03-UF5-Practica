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
import model.Game;
import model.Jugador;
import model.Ranking;

import java.io.IOException;
import java.sql.ResultSet;

public class EndController {
    @FXML
    private Label score;
    @FXML
    private Button replay;
    @FXML
    private Button quit;

    private RankingController rankingController = new RankingController();

    public void initialize() {
        int gameId = DataSingleton.getInstance().getGameId();
        int points = 0;
        ResultSet rs = Game.getGame(gameId);
        try {
            if (rs.next()) {
                points = rs.getInt("points");
            } else {
                System.out.println("No se ha encontrado el juego");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        score.setText("" + points);
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
