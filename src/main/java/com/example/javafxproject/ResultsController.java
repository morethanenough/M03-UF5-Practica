package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Enemic;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsController {

    private ArrayList<Enemic> enemics = new ArrayList<>();
    private String imgPath = "src/main/resources/images/";
    private Enemic rival = null;

    @FXML private ImageView playerImg = new ImageView();
    @FXML private ImageView enemyImg = new ImageView();
    @FXML
    private Button resultButton;
    @FXML
    private Label enemyLine;


    private void setPlayerData() {
        String imgTemp = imgPath + DataSingleton.getInstance().getJugador().getPhotos();
        Image tempPhoto = new Image("file:" + imgTemp);
        playerImg.setImage(tempPhoto);
    }
    private void setRival(int i) {
        enemics = DataSingleton.getInstance().getEnemics();
        rival = enemics.get(i);
        String tempPhotoPath = imgPath + rival.getPhotos();
        Image tempPhoto = new Image("file:" + tempPhotoPath);
        enemyImg.setImage(tempPhoto);
    }

    @FXML
    public void initialize() throws IOException {
        setRival(0);
        setPlayerData();
        if (DataSingleton.getInstance().getStage().getTitle().equals("Paper, Rock and Scissor Contest - Game Won")) {
            enemyLine.setText(rival.getLineWin());
            resultButton.setText("Siguiente batalla");
            resultButton.setOnAction(e -> {
                try {
                    nextBattle();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } else {
            enemyLine.setText(rival.getLineLose());
            resultButton.setText("Ver puntuación");
            resultButton.setOnAction(e -> {
                try {
                    scoreScreen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

        }
    }

    public void nextBattle() throws IOException {
        Stage stage = DataSingleton.getInstance().getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Playing");
        stage.setScene(scene);
        stage.show();
    }

    public void scoreScreen() throws IOException {
        Stage stage = DataSingleton.getInstance().getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("end-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Paper, Rock and Scissor Contest - Scores");
        stage.setScene(scene);
        stage.show();
    }
}
