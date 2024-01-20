package model;

public class Jugador extends Pj {
  
    private int puntuacion;
    private int partidasGanadas;
    private int partidasPerdidas;

    /** constuctor Jugador
     * parametres inicials: nom intoduit pel jugador i foto inicial de la imatge per defecte (que despres el jugador pot cambiar la foto)
     * puntiacion: inicialment 0 a mesura que progresi amb el joc, s'incrementara o desincrementa el valor
     * partidasGanadas: cada cop que el jugador guanyi a tots els enemics s'incrementara
     * partidasPerdidas: cada cop que el jugador perdi contra un enemic s'incrementara **/
    Jugador(String nombreJugador, String fotoJugador){
        super.name = nombreJugador;
        super.photos = fotoJugador;
        this.puntuacion = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    public void setNombre(String nombre) { this.name = nombre; }
    public String getNombre() { return name; }

    public void setFotos(String fotos) { this.photos = fotos; }
    public String getFotos() { return photos; }

    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
    public int getPuntuacion() { return puntuacion; }

    public void setPartidasGanadas(int partidasGanadas) { this.partidasGanadas = partidasGanadas; }
    public int getPartidasGanadas() { return partidasGanadas; }

    public void setPartidasPerdidas(int partidasPerdidas) { this.partidasPerdidas = partidasPerdidas; }
    public int getPartidasPerdidas() { return partidasPerdidas; }

    /** metodes per definir la puntuació calculant les partides perdudes i les guanyades**/
    public void sumarPuntuacion() {
        this.puntuacion += 40;//+40
        if (this.puntuacion > 99999999){
            this.puntuacion = 99999999;
        }
    }
    public void restarPuntuacion() {
        this.puntuacion -= 10;//-10
        if (this.puntuacion < 0){
            this.puntuacion = 0;
        }
    }
}