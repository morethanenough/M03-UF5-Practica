package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Enemic;
import model.Jugador;

import java.util.ArrayList;

public class GameController {
    @FXML
    private Label nombreUsuarioLabel = null;
    @FXML
    private Label winRoundsLabel = null;
    @FXML
    private Label loseRoundsLabel = null;
    @FXML
    private Label monsterNameLabel = null;
    private static String nombreJugador;

    private Jugador jugador;
    private Enemic enemigo;

    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;

    public static void setNombreJugador(String nombre) {
        nombreJugador = nombre;
    }

    public GameController() {
       this.enemigo = new Enemic();
    }

    @FXML
    public void initialize() {
        // Asegúrate de que los componentes de la UI estén disponibles aquí
        this.jugador = new Jugador(nombreJugador, "@../../../../java/Img/hombre.png");
        nombreUsuarioLabel.setText(nombreJugador);
        actualizarEstadoRondas();
        actualizarNombreMonstruo();
    }

    private void actualizarNombreMonstruo() {
        ArrayList<String> monstruos = enemigo.obtenerCincoEnemigos();
        // Suponiendo que quieres mostrar solo el primer monstruo de la lista
        monsterNameLabel.setText("Monstruo: " + monstruos.get(0));
    }

    private void actualizarEstadoRondas() {
        winRoundsLabel.setText("Rondas ganadas: " + rondasGanadas);
        loseRoundsLabel.setText("Rondas perdidas: " + rondasPerdidas);
    }

    private void verificarFinJuego() {
        if (rondasGanadas == 3 || rondasPerdidas == 3) {
        }
    }

    private void resolverRonda(String jugadaJugador) {
        String jugadaEnemigo = enemigo.elegirAtaque();

        // Lógica de juego
        if (jugadaJugador.equals(jugadaEnemigo)) {
            // Empate
        } else if ((jugadaJugador.equals("piedra") && jugadaEnemigo.equals("tijeras")) ||
                (jugadaJugador.equals("papel") && jugadaEnemigo.equals("piedra")) ||
                (jugadaJugador.equals("tijeras") && jugadaEnemigo.equals("papel"))) {
            jugador.sumarPuntuacion();
            rondasGanadas++;
        } else {
            jugador.restarPuntuacion();
            rondasPerdidas++;
        }

        verificarFinJuego();
    }

    public void jugadaPiedra(ActionEvent actionEvent) {
        resolverRonda("piedra");
    }

    public void jugadaPaper(ActionEvent actionEvent) {
        resolverRonda("papel");
    }

    public void jugadaTisora(ActionEvent actionEvent) {
        resolverRonda("tijeras");
    }
}
