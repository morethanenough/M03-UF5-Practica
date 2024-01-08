package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField userName;

    @FXML
    protected void onButtonClick() {
        welcomeText.setText(userName.getText());

    }
}