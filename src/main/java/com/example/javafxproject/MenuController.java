package com.example.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    protected void close() throws IOException {
        Platform.exit();
    }

    @FXML
    protected void ranking() throws IOException {

    }

    public void onButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        DataSingleton.getInstance().setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("new-player-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - New Player");
        stage.setScene(scene);
        stage.show();
    }

    public void openRanking(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ranking.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Ranking");
        stage.setScene(scene);
        stage.show();
    }
}
