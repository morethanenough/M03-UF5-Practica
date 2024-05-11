package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Ranking;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.sql.ResultSet;
import model.Game;

public class RankingController {
    String path = "src/main/resources/json/";
    String file = "ranking.json";
    //ArrayList<Ranking> resultats = new ArrayList<>();

    @FXML private Label position1name;
    @FXML private Label position1points;
    @FXML private Label position2name;
    @FXML private Label position2points;
    @FXML private Label position3name;
    @FXML private Label position3points;
    @FXML private Label position4name;
    @FXML private Label position4points;
    @FXML private Label position5name;
    @FXML private Label position5points;
    @FXML private Label position6name;
    @FXML private Label position6points;
    @FXML private Label position7name;
    @FXML private Label position7points;
    @FXML private Label position8name;
    @FXML private Label position8points;
    @FXML private Label position9name;
    @FXML private Label position9points;
    @FXML private Label position10name;
    @FXML private Label position10points;

    @FXML
    public void initialize() {
        position1name.setText(null);
        position1points.setText(null);
        position2name.setText(null);
        position2points.setText(null);
        position3name.setText(null);
        position3points.setText(null);
        position4name.setText(null);
        position4points.setText(null);
        position5name.setText(null);
        position5points.setText(null);
        position6name.setText(null);
        position6points.setText(null);
        position7name.setText(null);
        position7points.setText(null);
        position8name.setText(null);
        position8points.setText(null);
        position9name.setText(null);
        position9points.setText(null);
        position10name.setText(null);
        position10points.setText(null);
        try {
            ResultSet rs = Game.readGames(); // Asumimos que CRUD.readGames() es accesible y correcto.
            int i = 1; // Comienza desde 1 para alinear con tus cases.
            while (rs.next() && i <= 10) { // Asegura que solo lee los primeros 10 registros
                String name = rs.getString("name");
                int points = rs.getInt("points");
                switch (i) {
                    case 1:
                        position1name.setText(name);
                        position1points.setText(String.valueOf(points));
                        break;
                    case 2:
                        position2name.setText(name);
                        position2points.setText(String.valueOf(points));
                        break;
                    case 3:
                        position3name.setText(name);
                        position3points.setText(String.valueOf(points));
                        break;
                    case 4:
                        position4name.setText(name);
                        position4points.setText(String.valueOf(points));
                        break;
                    case 5:
                        position5name.setText(name);
                        position5points.setText(String.valueOf(points));
                        break;
                    case 6:
                        position6name.setText(name);
                        position6points.setText(String.valueOf(points));
                        break;
                    case 7:
                        position7name.setText(name);
                        position7points.setText(String.valueOf(points));
                        break;
                    case 8:
                        position8name.setText(name);
                        position8points.setText(String.valueOf(points));
                        break;
                    case 9:
                        position9name.setText(name);
                        position9points.setText(String.valueOf(points));
                        break;
                    case 10:
                        position10name.setText(name);
                        position10points.setText(String.valueOf(points));
                        break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ordenarRanking(String name, int points) {
        try {
            // Creem un array d'objectes Ranking
            ArrayList<Ranking> rankingArrayList = new ArrayList<>();
            // Creem un objecte JSON amb les dades del ranking
            String path = "src/main/resources/json/";
            String file = "ranking.json";
            JSONObject ranking = null;
            String readDocument = new String(Files.readAllBytes(Paths.get(path + file)));
            if (!readDocument.isEmpty()) {
                ranking = new JSONObject(readDocument);
            }
            // Creem un mapa amb les dades del ranking per a poder manipular-les i endreçar-les
            Map<String, Integer> rankingTable = new HashMap<>();
            for (int i = 1; i <= ranking.length(); i++) {
                String positionKey = "position" + i;
                JSONObject playerRanking = (JSONObject) ranking.get(positionKey);
                String playerName = (String) playerRanking.get("name");
                int playerPoints = (int) playerRanking.get("points");
                rankingArrayList.add(i - 1, new Ranking(positionKey, playerName, playerPoints));
                rankingTable.put(positionKey, playerPoints);
            }
            // Afegim el nou jugador al ranking
            rankingArrayList.add(new Ranking("position11", name, points));
            rankingTable.put("position11", points);
            // Definim un comparador per a comparar pels valors en lloc de les claus
            Comparator<String> valueComparator = new Comparator<String>() {
                public int compare(String a, String b) {
                    int valueA = rankingTable.get(a);
                    int valueB = rankingTable.get(b);
                    return Integer.compare(valueB, valueA);
                }
            };
            // Construim un TreeMap endressat pels valors de forma descendent
            TreeMap<Integer, List<String>> sortedByValueDescending = new TreeMap<>(Comparator.reverseOrder());
            // Iterar sobre el mapa original y llenar el mapa ordenado
            for (Map.Entry<String, Integer> entry : rankingTable.entrySet()) {
                String playerName = entry.getKey();
                int playerPoints = entry.getValue();
                sortedByValueDescending.computeIfAbsent(playerPoints, k -> new ArrayList<>()).add(playerName);
            }
            List<String> players = new ArrayList<>();
            // Imprimir el TreeMap ordenado por valores en orden descendente
            int a = 1;
            for (Map.Entry<Integer, List<String>> entry : sortedByValueDescending.entrySet()) {
                int playerPoints = entry.getKey();
                players = entry.getValue();
                for (String playerPosition : players) {
                    System.out.println("Position " + a + ": " + playerPosition + " with " + playerPoints + " points");
                    String newKey = "position" + a;
                    int ok = 0;
                    while (ok == 0) {
                        for (int i = 0; i < rankingArrayList.size(); i++) {
                            if (rankingArrayList.get(i).getPosition().equals(playerPosition)) {
                                ranking.put(newKey, new JSONObject().put("name", rankingArrayList.get(i).getName()).put("points", playerPoints));
                                ok = 1;
                            }
                        }
                    }
                    a++;
                }
            }
            // Escriure el ranking ordenat en el fitxer ranking.json
            Files.write(Paths.get(path + file), ranking.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void backToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.setScene(scene);
        stage.show();
    }

}
