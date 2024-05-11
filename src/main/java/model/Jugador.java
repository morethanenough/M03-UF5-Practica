package model;

public class Jugador extends Pj {
    private int puntuacion;
    private int partidasGanadas;
    private int partidasPerdidas;

    /** constuctor Jugador
     * paràmetres inicials: nom intoduït pel jugador i foto inicial de la imatge per defecte (que després el jugador pot canviar la imatge)
     * puntiacion: inicialment 0 a mesura que es progresi amb el joc, s'incrementarà o desincrementarà el valor
     * partidasGanadas: cada cop que el jugador guanyi a tots els enemics s'incrementarà el valor
     * partidasPerdidas: cada cop que el jugador perdi contra un enemic s'incrementarà el valor **/
    public Jugador(String nombreJugador, int id_foto){
        super(nombreJugador, id_foto);
        this.puntuacion = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    public void setNombre(String nombre) { this.name = nombre; }
    public String getNombre() { return name; }

    public void setFotos(int id_foto) { this.id_foto = id_foto; }
    public int getFotos() { return id_foto; }

    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
    public int getPuntuacion() { return puntuacion; }

    public void setPartidasGanadas(int partidasGanadas) { this.partidasGanadas = partidasGanadas; }
    public int getPartidasGanadas() { return partidasGanadas; }

    public void setPartidasPerdidas(int partidasPerdidas) { this.partidasPerdidas = partidasPerdidas; }
    public int getPartidasPerdidas() { return partidasPerdidas; }

    /** mètodes per definir la puntuació calculant les partides perdudes i les guanyades **/
}
