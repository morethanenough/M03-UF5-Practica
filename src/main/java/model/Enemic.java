package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enemic extends Pj{
    private String frase1;
    private String frase2;

    public Enemic(String nombre, String frase1, String frase2, int foto){
        super(nombre, foto);
        this.frase1 = frase1;
        this.frase2 = frase2;
    }

    /** Elegir piedra papel o tijera**/
    public String elegirAtaque(){
        int attack = (int)(Math.random() * 3);
        switch (attack) {
            case 0:
                return "piedra";
            case 1:
                return "papel";

            case 2:
                return "tijeras";

            default:
                return "error";
        }
    }

    public String getLineWin() {
        return frase1;
    }

    public String getLineLose() {
        return frase2;
    }

    public int getFoto() {
        return id_foto;
    }

    /** Random enemigos para luchar **/
    /*public ArrayList<String> obtenerCincoEnemigos(enemigos[]) {
        ArrayList<String>listaEnemigos = new ArrayList<>();
        Collections.addAll(listaEnemigos, enemigos);
        Collections.shuffle(listaEnemigos);
        return new ArrayList<>(listaEnemigos.subList(0, 4));
    }*/

    public static ResultSet readRivals() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03","root","");
            String query = "SELECT * FROM rivals";
            ResultSet result = con.createStatement().executeQuery(query);
            if (result != null) {
                return result;
            } else {
                System.out.println("Failed to connect to the database");
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return null;
    }
}


