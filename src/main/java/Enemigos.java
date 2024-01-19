import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Enemigos {
    private String[] monstruos;

    public Enemigos(){
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
        Random attack = new Random();
        int elegir = attack.nextInt(3);
        switch (elegir) {
            case 0:
                return "piedra";

            case 1:
                return "papel";

            default:
                return "tijeras";
        }
    }

    /** Random enemigos para luchar **/
    public ArrayList<String> obtenerCincoEnemigos(){
        ArrayList<String>listaEnemigos = new ArrayList<>();
        Collections.addAll(listaEnemigos, monstruos);
        Collections.shuffle(listaEnemigos);
        return new ArrayList<>(listaEnemigos.subList(0, 5));
    }
}
