package model;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ranking {

    private String name;
    private int points;

    public Ranking(String name, int points){
        this.name = name;
        this.points = points;
    }

    // setter i getter de name
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    // setter i getter de punts
    public void setPoints(int points) { this.points = points; }
    public int getPoints() { return points; }

    public void addRanking2Json(Ranking ranking){

    }

    public Ranking[] getRankingFromJson(){
        String path = "src/main/resources/json/";
        String file = "ranking.json";
        String[] ordenedRanking = new String[]{"", ""};
        try{
            String document = new String(Files.readAllBytes(Paths.get(path + file)));
            JSONObject ranking = new JSONObject(document);
            for(int i = 1; i <= 10; i ++) {
                String positionKey = "position" + i;
                JSONObject playerRanking = (JSONObject) ranking.get(positionKey);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
