package com.example.tpoo2final;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fenetre1 {
    public void creerconcert() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("concert.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CREER CONCERT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void creerconference() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("conference.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CREER CONFERENCE");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
