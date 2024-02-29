package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Enemic;
import model.Jugador;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    @FXML
    private Label nombreUsuario = null;
    @FXML
    private Label winRounds = null;
    @FXML
    private Label loseRounds = null;
    @FXML
    private Label monsterName = null;
    @FXML
    private Label warning = null;
    @FXML
    private ImageView imageJugadaJugador = new ImageView();
    @FXML
    private ImageView imageJugadaEnemic = new ImageView();
    @FXML
    private Button piedraBtn;
    @FXML
    private Button papelBtn;
    @FXML
    private Button tijerasBtn;



    private static Jugador jugador;
    private Enemic enemigo;

    private DataSingleton nombreJugador = DataSingleton.getInstance();
    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;
    // Rutes imatges pedra, paper, tisora
    private String imgPaper = "src/main/resources/images/paper.png";
    private String imgPedra = "src/main/resources/images/pedra.png";
    private String imgTisora = "src/main/resources/images/tisora.png";

    Image paper = new Image("file:" + imgPaper);
    Image pedra = new Image("file:" + imgPedra);
    Image tisora = new Image("file:" + imgTisora);

    /*public GameController() {
       this.enemigo = new Enemic();
    }*/

    @FXML
    /*public void initialize() {
        // Asegúrate de que los componentes de la UI estén disponibles aquí
        this.jugador = new Jugador(nombreJugador.getUserName(), "@../../../images/img_1.png");
        nombreUsuario.setText(nombreJugador.getUserName());
        warning.setText("");
        actualizarEstadoRondas();
        actualizarNombreMonstruo();
    }*/

    /*private void actualizarNombreMonstruo() {
        ArrayList<String> monstruos = enemigo.obtenerCincoEnemigos();
        // Suponiendo que quieres mostrar solo el primer monstruo de la lista
        monsterName.setText("Monstruo: " + monstruos.get(0));
    }*/

    private void actualizarEstadoRondas() {
        winRounds.setText("Rondas ganadas: " + rondasGanadas);
        loseRounds.setText("Rondas ganadas: " + rondasPerdidas);
        if (rondasGanadas == 3) {
            warning.setText("Victoria");
            playWinSound();
            disableAllBtns();
        }
        if (rondasPerdidas == 3) {
            warning.setText("Derrota");
            playLoseSound();
            disableAllBtns();
        }
    }

    private void verificarFinJuego() {
        if (rondasGanadas == 3 || rondasPerdidas == 3) {
            disableAllBtns();
        }
    }

    public void jugadaPiedra(ActionEvent actionEvent) throws InterruptedException {
        // Verifica si el juego ya ha terminado
        if (rondasGanadas < 3 && rondasPerdidas < 3) {
            disableBtnsInTime();
            TimeUnit.SECONDS.sleep(2);
            imageJugadaJugador.setImage(pedra);
            String jugadaEnemigo = enemigo.elegirAtaque();
            if (jugadaEnemigo.equals("piedra")) {
                // Empate
                imageJugadaEnemic.setImage(pedra);
            } else if (jugadaEnemigo.equals("papel")) {
                imageJugadaEnemic.setImage(paper);
                rondasPerdidas++;
            } else { // jugadaEnemigo es "tijeras"
                imageJugadaEnemic.setImage(tisora);
                rondasGanadas++;
            }

            actualizarEstadoRondas();
            verificarFinJuego();
        } else {
            disableAllBtns();
        }

    }

    public void jugadaPaper(ActionEvent actionEvent) throws InterruptedException {
        // Verifica si el juego ya ha terminado
        if (rondasGanadas < 3 && rondasPerdidas < 3) {
            disableBtnsInTime();
            TimeUnit.SECONDS.sleep(2);
            imageJugadaJugador.setImage(paper);
            String jugadaEnemigo = enemigo.elegirAtaque();
            if (jugadaEnemigo.equals("papel")) {
                // Empate
                imageJugadaEnemic.setImage(paper);
            } else if (jugadaEnemigo.equals("tijeras")) {
                imageJugadaEnemic.setImage(tisora);
                rondasPerdidas++;
            } else { // jugadaEnemigo es "piedra"
                imageJugadaEnemic.setImage(pedra);
                rondasGanadas++;
            }

            actualizarEstadoRondas();
            verificarFinJuego();
        } else {
            disableAllBtns();
        }
    }

    public void jugadaTisora(ActionEvent actionEvent) throws InterruptedException {
        // Verifica si el juego ya ha terminado
        if (rondasGanadas < 3 && rondasPerdidas < 3) {
            disableBtnsInTime();
            TimeUnit.SECONDS.sleep(2);
            imageJugadaJugador.setImage(tisora);
            String jugadaEnemigo = enemigo.elegirAtaque();
            if (jugadaEnemigo.equals("tijeras")) {
                // Empate
                imageJugadaEnemic.setImage(tisora);
            } else if (jugadaEnemigo.equals("piedra")) {
                imageJugadaEnemic.setImage(pedra);
                rondasPerdidas++;
            } else { // jugadaEnemigo es "papel"
                imageJugadaEnemic.setImage(paper);
                rondasGanadas++;
            }

            actualizarEstadoRondas();
            verificarFinJuego();
        } else {
            disableAllBtns();
        }
    }

    public void disableAllBtns() {
        piedraBtn.setDisable(true);
        tijerasBtn.setDisable(true);
        papelBtn.setDisable(true);
    }

    public void disableBtnsInTime() {
        if (rondasGanadas == 3 || rondasPerdidas == 3) {
            disableAllBtns();
        } else {
            piedraBtn.setDisable(true);

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                // Este código se ejecutará después de 3 segundos
                piedraBtn.setDisable(false); // Vuelve a activar el botón
            }, 3, TimeUnit.SECONDS);

            papelBtn.setDisable(true);

            ScheduledExecutorService executor2 = Executors.newSingleThreadScheduledExecutor();
            executor2.schedule(() -> {
                // Este código se ejecutará después de 3 segundos
                papelBtn.setDisable(false); // Vuelve a activar el botón
            }, 3, TimeUnit.SECONDS);

            tijerasBtn.setDisable(true);

            ScheduledExecutorService executor3 = Executors.newSingleThreadScheduledExecutor();
            executor3.schedule(() -> {
                // Este código se ejecutará después de 3 segundos
                tijerasBtn.setDisable(false); // Vuelve a activar el botón
            }, 3, TimeUnit.SECONDS);
        }
    }

    private void playSound(String soundFileName) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/audios/" + soundFileName);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores
        }
    }

    private void playWinSound() {
        playSound("YouWin.wav");
    }

    private void playLoseSound() {
        playSound("YouLoose.wav");
    }
}
