package com.example.tpoo2final;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public void Connection() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connection.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CONNECTION");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void creerEven() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fenetre1.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CREER EVENEMENT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void consultEven() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fenetre2.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CONSULTER EVENEMENT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ajout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Participants.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("AJOUT DE PARTICIPANT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}