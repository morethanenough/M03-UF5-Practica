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
    public void initialize() throws IOException {
        try{
            String document = new String(Files.readAllBytes(Paths.get(path + file)));
            JSONObject ranking = new JSONObject(document);
            for(int i = 1; i <= 10; i ++) {
                String positionKey = "position" + i;
                JSONObject playerRanking = (JSONObject) ranking.get(positionKey);
                switch (i){
                    case 1:
                        position1name.setText((String) playerRanking.get("name"));
                        position1points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 2:
                        position2name.setText((String) playerRanking.get("name"));
                        position2points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 3:
                        position3name.setText((String) playerRanking.get("name"));
                        position3points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 4:
                        position4name.setText((String) playerRanking.get("name"));
                        position4points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 5:
                        position5name.setText((String) playerRanking.get("name"));
                        position5points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 6:
                        position6name.setText((String) playerRanking.get("name"));
                        position6points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 7:
                        position7name.setText((String) playerRanking.get("name"));
                        position7points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 8:
                        position8name.setText((String) playerRanking.get("name"));
                        position8points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 9:
                        position9name.setText((String) playerRanking.get("name"));
                        position9points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                    case 10:
                        position10name.setText((String) playerRanking.get("name"));
                        position10points.setText((String.valueOf(playerRanking.get("points"))));
                        break;
                }
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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.setScene(scene);
        stage.show();
    }

}
