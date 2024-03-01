package model;

public class Enemic extends Pj{
    private String frase1;
    private String frase2;

    public Enemic(String nombre, String frase1, String frase2, String foto){
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

    /** Random enemigos para luchar **/
    /*public ArrayList<String> obtenerCincoEnemigos(enemigos[]) {
        ArrayList<String>listaEnemigos = new ArrayList<>();
        Collections.addAll(listaEnemigos, enemigos);
        Collections.shuffle(listaEnemigos);
        return new ArrayList<>(listaEnemigos.subList(0, 4));
    }*/
}


