package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Enemic;
import model.Game;
import model.Jugador;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class PlayerController {


    ArrayList<Enemic> enemics = new ArrayList<>();
    String jsonPath = "src/main/resources/json/";
    String rivalsJsonPath = "src/main/resources/json/rivals/";
    String nombre, frase1, frase2;
    int id_foto;

    @FXML private TextField nameInput;
    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;
    @FXML private Button playGameBtn;


    private final ToggleGroup group = new ToggleGroup();



    public void initialize() {
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);
        rb1.setUserData("1");
        rb2.setUserData("2");
        rb3.setUserData("3");
        rb4.setUserData("4");
    }
    public void initRivals() throws IOException, SQLException {
        try {
            ResultSet rs = Enemic.readRivals();
            while (rs.next()) {
                nombre = rs.getString("nombre");
                frase1 = rs.getString("frase1");
                frase2 = rs.getString("frase2");
                id_foto = Integer.parseInt(rs.getString("id_foto"));
                Enemic enemic = new Enemic(nombre, frase1, frase2, id_foto);
                enemics.add(enemic);
            }
            Collections.shuffle(enemics);
            DataSingleton.getInstance().setEnemics(enemics);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void initPlayer() throws IOException {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String name = nameInput.getText();
        int playerPic = 1;
        int userId = DataSingleton.getInstance().getId_user();
        Game.createGame(userId, name, playerPic);
    }
    public void enableDisablePlayBtn() {
        if (group.getSelectedToggle() != null) {
            if (group.getSelectedToggle().isSelected() && Objects.equals(nameInput.getText(), "")) {
                playGameBtn.setDisable(true);
            } else {
                playGameBtn.setDisable(false);
            }
        }
    }
    public void startGame(ActionEvent event) throws IOException, SQLException {
        initPlayer();
        initRivals();
        ResultSet rs = Game.getGame(DataSingleton.getInstance().getGameId());
        if (rs.next()) {
            int userId = rs.getInt("id_user");
            String name = rs.getString("name");
            int id_foto = rs.getInt("id_foto");
            int win_rounds = rs.getInt("win_rounds");
            int lost_rounds = rs.getInt("lost_rounds");
            int points = rs.getInt("points");
            Game game = new Game(userId, name, id_foto, win_rounds, lost_rounds, points);
            DataSingleton.getInstance().setGame(game);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest - Playing");
        stage.setScene(scene);
        stage.show();
    }
}
