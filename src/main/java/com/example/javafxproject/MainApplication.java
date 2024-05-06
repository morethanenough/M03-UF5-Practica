package com.example.javafxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/img_2.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
