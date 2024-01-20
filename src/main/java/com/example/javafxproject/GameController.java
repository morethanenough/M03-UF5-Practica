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
    private static Label nombreUsuarioLabel = null;
    @FXML
    private Label winRoundsLabel = null;
    @FXML
    private Label loseRoundsLabel = null;
    @FXML
    private Label monsterNameLabel = null;
    private static String nombreJugador;

    private static Jugador jugador;
    private Enemic enemigo;

    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;

    public static void setNombreJugador(String nombre) {
        if (jugador != null) {
            jugador.setName(nombre);
        }
        nombreUsuarioLabel.setText(nombre);
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

    public void jugadaPiedra(ActionEvent actionEvent) {
        String jugadaEnemigo = enemigo.elegirAtaque();
        if (jugadaEnemigo.equals("piedra")) {
            // Empate
        } else if (jugadaEnemigo.equals("papel")) {
            jugador.restarPuntuacion();
            rondasPerdidas++;
        } else { // jugadaEnemigo es "tijeras"
            jugador.sumarPuntuacion();
            rondasGanadas++;
        }

        actualizarEstadoRondas();
        verificarFinJuego();
    }

    public void jugadaPaper(ActionEvent actionEvent) {
        String jugadaEnemigo = enemigo.elegirAtaque();
        if (jugadaEnemigo.equals("papel")) {
            // Empate
        } else if (jugadaEnemigo.equals("tijeras")) {
            jugador.restarPuntuacion();
            rondasPerdidas++;
        } else { // jugadaEnemigo es "piedra"
            jugador.sumarPuntuacion();
            rondasGanadas++;
        }

        actualizarEstadoRondas();
        verificarFinJuego();
    }

    public void jugadaTisora(ActionEvent actionEvent) {
        String jugadaEnemigo = enemigo.elegirAtaque();
        if (jugadaEnemigo.equals("tijeras")) {
            // Empate
        } else if (jugadaEnemigo.equals("piedra")) {
            jugador.restarPuntuacion();
            rondasPerdidas++;
        } else { // jugadaEnemigo es "papel"
            jugador.sumarPuntuacion();
            rondasGanadas++;
        }

        actualizarEstadoRondas();
        verificarFinJuego();
    }
}
