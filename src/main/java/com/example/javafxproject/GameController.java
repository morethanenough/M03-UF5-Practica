package com.example.javafxproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Enemic;
import model.Jugador;

import java.util.ArrayList;

public class GameController {
    @FXML
    private Label nombreUsuario = null;
    @FXML
    private Label winRounds = null;
    @FXML
    private Label loseRounds = null;
    @FXML
    private Label monsterName = null;

    private static Jugador jugador;
    private Enemic enemigo;

    private DataSingleton nombreJugador = DataSingleton.getInstance();
    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;

    public GameController() {
       this.enemigo = new Enemic();
    }

    @FXML
    public void initialize() {
        // Asegúrate de que los componentes de la UI estén disponibles aquí
        this.jugador = new Jugador(nombreJugador.getUserName(), "@../../../../java/Img/hombre.png");
        nombreUsuario.setText(nombreJugador.getUserName());
        actualizarEstadoRondas();
        actualizarNombreMonstruo();
    }

    private void actualizarNombreMonstruo() {
        ArrayList<String> monstruos = enemigo.obtenerCincoEnemigos();
        // Suponiendo que quieres mostrar solo el primer monstruo de la lista
        monsterName.setText("Monstruo: " + monstruos.get(0));
    }

    private void actualizarEstadoRondas() {
        winRounds.setText("Rondas ganadas: " + rondasGanadas);
        loseRounds.setText("Rondas perdidas: " + rondasPerdidas);
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
