package com.example.javafxproject;

import javafx.stage.Stage;
import model.Enemic;
import model.Jugador;

import java.util.ArrayList;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();

    private String userName;
    private Jugador jugador;
    private ArrayList<Enemic> enemics;
    private Stage stage;
    private Integer enemic = 0;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public ArrayList<Enemic> getEnemics() {
        return enemics;
    }

    public void setEnemics(ArrayList<Enemic> enemics) {
        this.enemics = enemics;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }
    public void setEnemic(Integer enemic) {
        this.enemic = enemic;
    }
    public Integer getEnemic() {
        return enemic;
    }
}
