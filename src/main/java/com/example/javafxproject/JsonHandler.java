package com.example.javafxproject;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    public static void main(String[] args) {
        try {
            String document = new String(Files.readAllBytes(Paths.get("src/main/resources/json/user.json")));
            JSONObject user = new JSONObject(document);
            JSONObject jsonObject = new JSONObject(user.toString());
            JSONObject monster = new JSONObject();

            /* String nombre = jsonObject.getString("nombre");
            String edad = jsonObject.getString("edad");
            String ciudad = jsonObject.getString("ciudad");

            System.out.println("Nombre: " + nombre);
            System.out.println("Edad: " + edad);
            System.out.println("Ciudad: " + ciudad); */

            String nombre = "Juan";
            String edad = "25";
            String ciudad = "Bogotá";

            user.put("nombre", nombre);
            user.put("edad", edad);
            user.put("ciudad", ciudad);

            String monsterName = "Goblin";
            String monsterHealth = "100";
            String monsterAttack = "10";
            String monsterDefense = "5";

            jsonObject.put("monsterName", monsterName);
            jsonObject.put("monsterHealth", monsterHealth);
            jsonObject.put("monsterAttack", monsterAttack);
            jsonObject.put("monsterDefense", monsterDefense);

            user.put("monster", jsonObject);

            try (FileWriter file = new FileWriter("src/main/resources/json/user.json")) {
                file.write(user.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            monster = user.getJSONObject("monster");
            System.out.println("Monster: " + monster.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
