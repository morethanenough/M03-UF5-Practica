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

    private final String imgPath = "src/main/resources/images/";
    private Enemic rival = null;

    @FXML private ImageView playerImg = new ImageView();
    @FXML private ImageView enemyImg = new ImageView();
    @FXML private Button resultButton;
    @FXML private Label enemyLine;

    private void setPlayerData() {
        String imgTemp = imgPath + PicController.getPic(DataSingleton.getInstance().getGame().getId_foto());
        Image tempPhoto = new Image("file:" + imgTemp);
        playerImg.setImage(tempPhoto);
    }
    private void setRival(int i) {
        ArrayList<Enemic> enemics = DataSingleton.getInstance().getEnemics();
        rival = enemics.get(i);
        String tempPhotoPath = imgPath + PicController.getPic(rival.getFoto());
        Image tempPhoto = new Image("file:" + tempPhotoPath);
        enemyImg.setImage(tempPhoto);
    }

    @FXML
    public void initialize() throws IOException {
        setRival(DataSingleton.getInstance().getEnemic());
        setPlayerData();
        if (DataSingleton.getInstance().getStage().getTitle().equals("Paper, Rock and Scissor Contest - Game Won")) {
            enemyLine.setText(rival.getLineWin());
            if (DataSingleton.getInstance().getPartida() != 5) {
                DataSingleton.getInstance().setPartida(DataSingleton.getInstance().getPartida() + 1);
                resultButton.setText("Següent batalla");
                resultButton.setOnAction(e -> {
                    try {
                        nextBattle();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            } else {
                resultButton.setText("Veure puntuacions");
                resultButton.setOnAction(e -> {
                    try {
                        scoreScreen();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            }

        } else {
            enemyLine.setText(rival.getLineLose());
            resultButton.setText("Veure puntuacions");
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
        System.out.println(DataSingleton.getInstance().getEnemic());
        DataSingleton.getInstance().setEnemic(DataSingleton.getInstance().getEnemic()+1);
        Stage stage = DataSingleton.getInstance().getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
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
