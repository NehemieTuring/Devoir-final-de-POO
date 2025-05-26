package com.example.tpoo2final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;
public class Main extends Application {
    public static void main(String[] args){ launch();}
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.tpoo2final.Main.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MENU PRINCIPALE");
        stage.setScene(scene);
        stage.show();
    }
}



