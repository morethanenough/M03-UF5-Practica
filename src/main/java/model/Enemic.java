package model;

import java.util.ArrayList;
import java.util.Collections;


public class Enemic extends Pj{
    private String[] monstruos;

    public Enemic(){
        monstruos = new String[]{
                "monstruo_1",
                "monstruo_2",
                "monstruo_3",
                "monstruo_4",
                "monstruo_5",
                "monstruo_6",
                "monstruo_7",
                "monstruo_8",
                "monstruo_9",
                "monstruo_10"
        };
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

    /** Random enemigos para luchar **/
    public ArrayList<String> obtenerCincoEnemigos(){
        ArrayList<String>listaEnemigos = new ArrayList<>();
        Collections.addAll(listaEnemigos, monstruos);
        Collections.shuffle(listaEnemigos);
        return new ArrayList<>(listaEnemigos.subList(0, 1));
    }
}


