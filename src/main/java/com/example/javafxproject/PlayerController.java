package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Enemic;
import model.Jugador;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerController {


    ArrayList<Enemic> enemics = new ArrayList<>();
    String jsonPath = "src/main/resources/json/";
    String rivalsJsonPath = "src/main/resources/json/rivals/";
    String nombre, frase1, frase2, foto;

    @FXML private TextField nameInput;
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;


    private final ToggleGroup group = new ToggleGroup();



    public void initialize() {
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);
        rb1.setUserData("dllamas.png");
        rb2.setUserData("alejandro.png");
        rb3.setUserData("quim.png");
        rb4.setUserData("joan.png");
    }
    public void initRivals() throws IOException {
        File carpeta = new File(rivalsJsonPath);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] fitxers = carpeta.listFiles();
            if (fitxers != null) {
                for (File fitxer : fitxers) {
                    String document = new String(Files.readAllBytes(Paths.get("src/main/resources/json/rivals/" + fitxer.getName())));
                    JSONObject rival = new JSONObject(document);
                    nombre = rival.getString("nombre");
                    frase1 = rival.getString("frase1");
                    frase2 = rival.getString("frase2");
                    foto = rival.getString("foto");
                    Enemic enemic = new Enemic(nombre, frase1, frase2, foto);
                    enemics.add(enemic);
                }
            }
            Collections.shuffle(enemics);
            DataSingleton.getInstance().setEnemics(enemics);
        } else {
            System.out.println("La carpeta especificada no existe o no es un directorio.");
        }
    }

    private void initPlayer() throws IOException {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String name = nameInput.getText();
        String playerPic = (String) selectedRadioButton.getUserData();
        Jugador jugador = new Jugador(name, playerPic);
        DataSingleton.getInstance().setJugador(jugador);
        /*String document = new String(Files.readAllBytes(Paths.get(jsonPath + "player.json")));
        JSONObject user = new JSONObject(document);
        user.put("nom", name);
        user.put("foto", playerPic);*/
    }
    public void startGame(ActionEvent event) throws IOException {
        initPlayer();
        initRivals();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Playing");
        stage.setScene(scene);
        stage.show();
    }
}
