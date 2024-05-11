package com.example.javafxproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Enemic;
import model.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {

    private final String imgPath = "src/main/resources/images/";
    private int rondasGanadas = 0;
    private int rondasPerdidas = 0;
    private Enemic rival = null;
    // FXML variables
    @FXML
    private ImageView playerImg = new ImageView();
    @FXML
    private ImageView enemyImg = new ImageView();
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
    @FXML
    private Label nombreUsuario;
    @FXML
    private Label enemyName = null;
    @FXML
    private Label winRounds = null;
    @FXML
    private Label numWinRounds = null;
    @FXML
    private Label loseRounds = null;
    @FXML
    private Label numLoseRounds = null;
    @FXML
    private Label warning = null;


    // Rutes i imatges pedra, paper, tisora
    private String imgPaper = "src/main/resources/images/paper.png";
    private String imgPedra = "src/main/resources/images/pedra.png";
    private String imgTisora = "src/main/resources/images/tisora.png";
    private Image paper = new Image("file:" + imgPaper);
    private Image pedra = new Image("file:" + imgPedra);
    private Image tisora = new Image("file:" + imgTisora);


    public void setRival(int i) {
        ArrayList<Enemic> enemics = DataSingleton.getInstance().getEnemics();
        rival = enemics.get(i);
        System.out.println(rival.getName());
        enemyName.setText(rival.getName());
        String tempPhotoPath = imgPath + PicController.getPic(rival.getFoto());
        Image tempPhoto = new Image("file:" + tempPhotoPath);
        enemyImg.setImage(tempPhoto);
    }

    public void setPlayerData() {
        String imgTemp = imgPath + PicController.getPic(DataSingleton.getInstance().getGame().getId_foto());
        Image tempPhoto = new Image("file:" + imgTemp);
        playerImg.setImage(tempPhoto);
        nombreUsuario.setText(DataSingleton.getInstance().getGame().getName());
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
            DataSingleton.getInstance().getGame().setWin_rounds(DataSingleton.getInstance().getGame().getWin_rounds() + 1);
            playWinSound();
            disableAllBtns();
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                Platform.runLater(() -> {
                    try {
                        winCondition();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }, 2, TimeUnit.SECONDS);
        }
        if (rondasPerdidas == 3) {
            playLoseSound();
            disableAllBtns();
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                Platform.runLater(() -> {
                    try {
                        loseCondition();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }, 2, TimeUnit.SECONDS);
        }

    }

    private void verificarFinJuego() {
        if (rondasGanadas == 3 || rondasPerdidas == 3) {
            disableAllBtns();
            puntuacion(rondasGanadas, rondasPerdidas);
        }
    }

    public void jugadaPedra(ActionEvent actionEvent) throws InterruptedException {
        int gameId = DataSingleton.getInstance().getGameId();
        int lostRounds = DataSingleton.getInstance().getGame().getLost_rounds();
        int winRounds = DataSingleton.getInstance().getGame().getWin_rounds();
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
                DataSingleton.getInstance().getGame().setLost_rounds(lostRounds + 1);
                Game.updateGame(gameId, 0, 1);
            } else { // jugadaEnemigo es "tijeras"
                imageJugadaEnemic.setImage(tisora);
                rondasGanadas++;
                DataSingleton.getInstance().getGame().setWin_rounds(winRounds + 1);
                Game.updateGame(gameId, 1, 0);
            }
            // Actualitzem l'estat de les rondes
            Platform.runLater(() -> {
                enableAllBtns();
                actualizarEstadoRondas();
                verificarFinJuego();
            });
        }, 1, TimeUnit.SECONDS);
    }

    public void jugadaPaper(ActionEvent actionEvent) {
        int gameId = DataSingleton.getInstance().getGameId();
        int lostRounds = DataSingleton.getInstance().getGame().getLost_rounds();
        int winRounds = DataSingleton.getInstance().getGame().getWin_rounds();
        // Desactivem els botons
        disableAllBtns();
        // Programem un executor que torni a activar els botons després de 3 segons
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            imageJugadaJugador.setImage(paper);
            String jugadaEnemigo = rival.elegirAtaque();
            if (jugadaEnemigo.equals("papel")) {
                // Empate
                imageJugadaEnemic.setImage(paper);
            } else if (jugadaEnemigo.equals("tijeras")) {
                imageJugadaEnemic.setImage(tisora);
                rondasPerdidas++;
                DataSingleton.getInstance().getGame().setLost_rounds(lostRounds + 1);
                Game.updateGame(gameId, 0, 1);
            } else { // jugadaEnemigo es "piedra"
                imageJugadaEnemic.setImage(pedra);
                rondasGanadas++;
                DataSingleton.getInstance().getGame().setWin_rounds(winRounds + 1);
                Game.updateGame(gameId, 1, 0);
            }
            // Actualitzem l'estat de les rondes
            Platform.runLater(() -> {
                enableAllBtns();
                actualizarEstadoRondas();
                verificarFinJuego();
            });
        }, 1, TimeUnit.SECONDS);
    }

    public void jugadaTisora(ActionEvent actionEvent) {
        int gameId = DataSingleton.getInstance().getGameId();
        int lostRounds = DataSingleton.getInstance().getGame().getLost_rounds();
        int winRounds = DataSingleton.getInstance().getGame().getWin_rounds();
        // Desactivem els botons
        disableAllBtns();
        // Programem un executor que torni a activar els botons després de 3 segons
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            imageJugadaJugador.setImage(tisora);
            String jugadaEnemigo = rival.elegirAtaque();
            if (jugadaEnemigo.equals("tijeras")) {
                // Empate
                imageJugadaEnemic.setImage(tisora);
            } else if (jugadaEnemigo.equals("piedra")) {
                imageJugadaEnemic.setImage(pedra);
                rondasPerdidas++;
                DataSingleton.getInstance().getGame().setWin_rounds(winRounds + 1);
                Game.updateGame(gameId, 0, 1);
            } else { // jugadaEnemigo es "papel"
                imageJugadaEnemic.setImage(paper);
                rondasGanadas++;
                DataSingleton.getInstance().getGame().setLost_rounds(lostRounds + 1);
                Game.updateGame(gameId, 1, 0);
            }
            // Actualitzem l'estat de les rondes
            Platform.runLater(() -> {
                enableAllBtns();
                actualizarEstadoRondas();
                verificarFinJuego();
            });
        }, 1, TimeUnit.SECONDS);
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

    public void winCondition() throws IOException {
        Stage stage = (Stage) nombreUsuario.getScene().getWindow();
        stage.setTitle("Paper, Rock and Scissor Contest - Game Won");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("results-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setScene(scene);
        stage.show();
    }

    public void loseCondition() throws IOException {
        Stage stage = (Stage) nombreUsuario.getScene().getWindow();
        stage.setTitle("Paper, Rock and Scissor Contest - Game Lost");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("results-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setScene(scene);
        stage.show();
    }

    public void puntuacion(int rondasGanadas, int rondasPerdidas) {
        int puntos = rondasGanadas * 100 - rondasPerdidas * 50;

        if (puntos < 0) {
            puntos = 0;
        }
        DataSingleton.getInstance().getGame().setPoints(DataSingleton.getInstance().getGame().getPoints() + puntos);

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

    @FXML
    public void initialize() throws IOException {
        winRounds.setText("Victòries: ");
        loseRounds.setText("Victòries: ");
        numWinRounds.setText(String.valueOf(rondasGanadas));
        numLoseRounds.setText(String.valueOf(rondasPerdidas));
        if (DataSingleton.getInstance().getPartida() < 4) {
            warning.setText("Batalla " + (DataSingleton.getInstance().getPartida() + 1));
        } else {
            warning.setText("Final Boss");
        }
        setRival(DataSingleton.getInstance().getEnemic());
        System.out.println(DataSingleton.getInstance().getEnemic());
        setPlayerData();
    }
}
