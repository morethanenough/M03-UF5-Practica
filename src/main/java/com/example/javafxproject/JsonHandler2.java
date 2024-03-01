package com.example.javafxproject;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonHandler2 {
    public static void main(String[] args) {
        try {
            String path = "src/main/resources/json/";
            String file = "ranking.json";
            JSONObject ranking = null;
            String readDocument = new String(Files.readAllBytes(Paths.get(path + file)));
            if (!readDocument.isEmpty()) {
                ranking = new JSONObject(readDocument);
            }
            Map<String, Integer> rankingTable = new HashMap<>();
            //System.out.println(ranking.length());
            for (int i = 1; i <= ranking.length(); i++) {
                String positionKey = "position" + i;
                JSONObject playerRanking = (JSONObject) ranking.get(positionKey);
                String playerName = (String) playerRanking.get("name");
                int playerPoints = (int) playerRanking.get("points");
                //System.out.println(playerName + " " + playerPoints);
                rankingTable.put(positionKey, playerPoints);
            }
            //System.out.println(rankingTable.size());

            // Definir un comparador para comparar por valores en lugar de claves
            Comparator<String> valueComparator = new Comparator<String>() {
                public int compare(String a, String b) {
                    int valueA = rankingTable.get(a);
                    int valueB = rankingTable.get(b);
                    return Integer.compare(valueB, valueA);
                }
            };

            // Construir un TreeMap usando el comparador de valores
            /*TreeMap<String, Integer> sortedByValue = new TreeMap<>(valueComparator);
            sortedByValue.putAll(rankingTable);

            for (Map.Entry<String, Integer> entry : sortedByValue.entrySet()) {
                String playerName = entry.getKey();
                int playerPoints = entry.getValue();
                System.out.println(playerName + ": " + playerPoints);

            }*/
            // Construir un TreeMap ordenado por valores en orden descendente
            TreeMap<Integer, List<String>> sortedByValueDescending = new TreeMap<>(Comparator.reverseOrder());

            // Iterar sobre el mapa original y llenar el mapa ordenado
            for (Map.Entry<String, Integer> entry : rankingTable.entrySet()) {
                String playerName = entry.getKey();
                int playerPoints = entry.getValue();
                sortedByValueDescending.computeIfAbsent(playerPoints, k -> new ArrayList<>()).add(playerName);
            }

            // Imprimir el TreeMap ordenado por valores en orden descendente
            for (Map.Entry<Integer, List<String>> entry : sortedByValueDescending.entrySet()) {
                int playerPoints = entry.getKey();
                List<String> players = entry.getValue();
                for (String playerName : players) {
                    System.out.println(playerName + ": " + playerPoints);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
