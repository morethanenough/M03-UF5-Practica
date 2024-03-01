package model;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ranking {

    private String position;
    private String name;
    private int points;

    public Ranking(String position, String name, int points){
        this.position = position;
        this.name = name;
        this.points = points;
    }

    // setter i getter de name
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    // setter i getter de punts
    public void setPoints(int points) { this.points = points; }
    public int getPoints() { return points; }

    // setter i getter de posició
    public void setPosition(String position) { this.position = position; }
    public String getPosition() { return position; }

}
