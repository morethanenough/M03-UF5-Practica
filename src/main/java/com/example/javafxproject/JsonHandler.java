package com.example.javafxproject;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    public static void main(String[] args) {
        try {
            String path = "src/main/resources/json/rivals";
            File carpeta = new File(path);
            if (carpeta.exists() && carpeta.isDirectory()) {
                // Obtenim els fitxers de la carpeta
                File[] fitxers = carpeta.listFiles();
                // Iterem sobre els fitxers de la carpeta
                if (fitxers != null) {
                    for (File fitxer : fitxers) {
                        // Imprime el nombre de cada archivo o subdirectorio
                        System.out.println(fitxer.getName());
                        String document = new String(Files.readAllBytes(Paths.get("src/main/resources/json/rivals/" + fitxer.getName())));
                        JSONObject rival = new JSONObject(document);
                        System.out.println(rival.getString("nombre"));
                        System.out.println(rival.getString("frase1"));
                        System.out.println(rival.getString("frase2"));
                        System.out.println(rival.getString("foto"));
                    }
                }
            } else {
                System.out.println("La carpeta especificada no existe o no es un directorio.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
