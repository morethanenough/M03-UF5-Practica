package com.example.javafxproject;

import com.mysql.jdbc.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;


public class AdminController {

    @FXML private ComboBox<String> selectUser;

    @FXML
    private void initialize() {
        ObservableList<String> users = FXCollections.observableArrayList();
        try {
            ResultSet rs = (ResultSet) User.readUser();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                users.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        selectUser.setItems(users);
    }

    public void backToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        stage.setTitle("Paper, Rock and Scissor Contest");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteUser() {
        String selectedUserName = selectUser.getValue();
        if (selectedUserName != null && !selectedUserName.isEmpty()) {
            boolean deleted = User.deleteUser(selectedUserName);
            // Lógica para eliminar el usuario seleccionado
            if (deleted) {
                System.out.println("Usuario eliminado: " + selectedUserName);
            } else {
                System.out.println("No se ha podido eliminar el usuario: " + selectedUserName);
            }
            initialize();
        } else {
            System.out.println("Por favor, selecciona un usuario.");
        }
    }
}
