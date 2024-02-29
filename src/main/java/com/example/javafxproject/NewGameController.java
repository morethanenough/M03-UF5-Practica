package com.example.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Enemic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewGameController {

    private ArrayList<Enemic> enemics = new ArrayList<>();
    private String imgPath = "src/main/resources/images/";
    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;
    private Enemic rival = null;
    // FXML variables
    @FXML private ImageView playerImg = new ImageView();
    @FXML private ImageView enemyImg = new ImageView();
    @FXML private ImageView imageJugadaJugador = new ImageView();
    @FXML private ImageView imageJugadaEnemic = new ImageView();
    @FXML private Button piedraBtn;
    @FXML private Button papelBtn;
    @FXML private Button tijerasBtn;
    @FXML private Label nombreUsuario;
    @FXML private Label enemyName = null;
    @FXML private Label winRounds = null;
    @FXML private Label numWinRounds = null;
    @FXML private Label loseRounds = null;
    @FXML private Label numLoseRounds = null;
    @FXML private Label warning = null;


    // Rutes i imatges pedra, paper, tisora
    private String imgPaper = "src/main/resources/images/paper.png";
    private String imgPedra = "src/main/resources/images/pedra.png";
    private String imgTisora = "src/main/resources/images/tisora.png";
    private Image paper = new Image("file:" + imgPaper);
    private Image pedra = new Image("file:" + imgPedra);
    private Image tisora = new Image("file:" + imgTisora);



    public void setRival(int i) {
        enemics = DataSingleton.getInstance().getEnemics();
        rival = enemics.get(i);
        System.out.println(rival.getName());
        enemyName.setText(rival.getName());
        String tempPhotoPath = imgPath + rival.getPhotos();
        Image tempPhoto = new Image("file:" + tempPhotoPath);
        enemyImg.setImage(tempPhoto);
    }

    public void setPlayerData() {
        String imgTemp = imgPath + DataSingleton.getInstance().getJugador().getPhotos();
        Image tempPhoto = new Image("file:" + imgTemp);
        playerImg.setImage(tempPhoto);
        nombreUsuario.setText(DataSingleton.getInstance().getJugador().getName());
    }

    public void disableAllBtns() {
        piedraBtn.setDisable(true);
        tijerasBtn.setDisable(true);
        papelBtn.setDisable(true);
    }

    public void enableAllBtns() {
        piedraBtn.setDisable(false);
        tijerasBtn.setDisable(false);
        papelBtn.setDisable(false);
    }
    private void actualizarEstadoRondas() {
        //winRounds.setText("Wins: " + rondasGanadas);
        numWinRounds.setText(String.valueOf(rondasGanadas));
        //loseRounds.setText("Wins: " + rondasPerdidas);
        numLoseRounds.setText(String.valueOf(rondasPerdidas));
        if (rondasGanadas == 3) {
            warning.setText("Victory");
            disableAllBtns();
        }
        if (rondasPerdidas == 3) {
            warning.setText("Defeated");
            disableAllBtns();
        }
    }

    private void verificarFinJuego() {
        if (rondasGanadas == 3 || rondasPerdidas == 3) {
            disableAllBtns();
        }
    }

    public void jugadaPedra(ActionEvent actionEvent, String jugada) throws InterruptedException {
        // Desactivem els botons
        disableAllBtns();
        // Programem un executor que torni a activar els botons després de 3 segons
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            imageJugadaJugador.setImage(pedra);
            String jugadaEnemigo = rival.elegirAtaque();
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
            // Actualitzem l'estat de les rondes
            Platform.runLater(() -> {
                enableAllBtns();
                actualizarEstadoRondas();
                verificarFinJuego();
            });
        }, 3, TimeUnit.SECONDS);
    }

    public void jugadaPaper(ActionEvent actionEvent) throws InterruptedException {
        // Verifica si el juego ya ha terminado
        if (rondasGanadas < 3 && rondasPerdidas < 3) {
            disableBtnsInTime();
            TimeUnit.SECONDS.sleep(2);
            imageJugadaJugador.setImage(paper);
            String jugadaEnemigo = rival.elegirAtaque();
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
            //TimeUnit.SECONDS.sleep(2);
            imageJugadaJugador.setImage(tisora);
            String jugadaEnemigo = rival.elegirAtaque();
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

    @FXML
    public void initialize() throws IOException {
        winRounds.setText("Wins: " );
        loseRounds.setText("Wins: ");
        numWinRounds.setText(String.valueOf(rondasGanadas));
        numLoseRounds.setText(String.valueOf(rondasPerdidas));
        setRival(0);
        setPlayerData();
    }
}
