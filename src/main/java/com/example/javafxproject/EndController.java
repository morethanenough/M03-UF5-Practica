package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Jugador;

public class EndController {
    @FXML
    private Label score;

    public void initialize() {
        score.setText("" + DataSingleton.getInstance().getJugador().getPuntuacion());

    }
}
