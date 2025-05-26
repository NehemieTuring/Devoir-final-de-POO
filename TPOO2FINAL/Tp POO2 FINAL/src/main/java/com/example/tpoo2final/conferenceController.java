package com.example.tpoo2final;

import Evenement.Concert;
import Evenement.Conference;
import Personne.Intervenant;
import Personne.Organisateur;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Hachage.HASHA.hashSHA;

public class conferenceController {
    @FXML
    private TextField conn1, conn2, conn3, conn4, conn5, conn6;
    @FXML
    private Button button1;
    @FXML
    private TextField textI;
    public void Enregistrer() throws IOException {
        List<String>intervenants = new ArrayList<>();
        String identifiant = conn1.getText();
        String nom = conn2.getText();
        String date1 = conn3.getText();
        // convertion en localdate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime date = LocalDateTime.parse(date1);

        String lieu = conn4.getText();
        String theme = conn5.getText();
        String inter = textI.getText();
        String[] inter1 = inter.split(",");
        for(String i: inter1){
            intervenants.add(i);
        }
        int capaciteMax = Integer.parseInt(conn6.getText());
        ObjectMapper mapper = new ObjectMapper();


        //SERIALISATION
        File fichier = new File("Evenement.json");
        List<Conference> conf = new ArrayList<>();

        if (fichier.exists() && fichier.length() != 0) {
            try {
                // Essaye de lire comme une liste
                conf = mapper.readValue(fichier, new TypeReference<List<Conference>>() {});
            } catch (MismatchedInputException e) {
                Conference single = mapper.readValue(fichier, Conference.class);
                conf.add(single);
            }
        }

        Conference conference = new Conference(identifiant, nom, date, lieu,capaciteMax, theme, intervenants);
        conf.add(conference);
        mapper.writeValue(fichier, conf);
    }
    public void ajouterI(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intervenant.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("AJOUTER INTERVENANT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
