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
    public Jugador(String nombreJugador, String fotoJugador){
        super(nombreJugador, fotoJugador);
        this.puntuacion = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }

    /*TODO: InteliJ no detecta como error this.name o this.photos que provienen de la clase Pj
       (si hay un error, cambiar esto por super.name y super.photos)*/
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

    /** mètodes per definir la puntuació calculant les partides perdudes i les guanyades **/
    public void sumarPuntuacion(int rondasGanadas) {
        int puntosPorRonda;

        // Si se ganan 3 rondas seguidas, se otorgan 100 puntos
        if (rondasGanadas == 3) {
            this.puntuacion += 100;
        } else if (rondasGanadas >= 10) {
            this.puntuacion += 10;
        } else {
            // Calcular los puntos por ronda en función del número total de rondas ganadas
            puntosPorRonda = (rondasGanadas - 3) * 10;
            int puntos = 100 - puntosPorRonda;
            this.puntuacion += puntos;
        }
    }
}
