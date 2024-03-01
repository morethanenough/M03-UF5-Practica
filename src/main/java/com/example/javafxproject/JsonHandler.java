package com.example.javafxproject;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class JsonHandler {
    public static void main(String[] args) throws IOException {

            /*String document = new String(Files.readAllBytes(Paths.get("src/main/resources/json/user.json")));
            JSONObject user = new JSONObject(document);
            JSONObject jsonObject = new JSONObject(user.toString());

            String nombre = "Juan";
            String edad = "25";
            String ciudad = "Bogotá";

            user.put("nombre", nombre);
            user.put("edad", edad);
            user.put("ciudad", ciudad);


            try (FileWriter file = new FileWriter("src/main/resources/json/user.json")) {
                file.write(user.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            monster = user.getJSONObject("monster");
            System.out.println("Monster: " + monster.toString());

            String ruta = "src/main/resources/json/";
            String slot1 = "slot1.json";
            File slot = new File(ruta + slot1);
            if (slot.createNewFile()) {
                System.out.println("Archivo creado correctamente: " + slot.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
            JSONObject jsonObject = null;
            String path = "src/main/resources/json/";
            String file = "rivals.json";
            String filePath = path + file;
            try (FileReader jsonReader = new FileReader(filePath)) {
                StringBuilder rivalsList = new StringBuilder();
                int character;
                while ((character = jsonReader.read()) != -1) {
                    rivalsList.append((char) character);
                    jsonObject = new JSONObject(rivalsList.toString());
                }
                String nombreRival = "rival3";
                JSONObject rivalSeleccionado = jsonObject.getJSONObject(nombreRival);
                // Leer los datos del rival seleccionado
                String nombre = rivalSeleccionado.getString("nombre");
                String frase1 = rivalSeleccionado.getString("frase1");
                String frase2 = rivalSeleccionado.getString("frase2");
                String foto = rivalSeleccionado.getString("foto");

                // Mostrar los datos del rival seleccionado
                System.out.println("Nombre: " + nombre);
                System.out.println("Frase 1: " + frase1);
                System.out.println("Frase 2: " + frase2);
                System.out.println("Foto: " + foto);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

    }
}
