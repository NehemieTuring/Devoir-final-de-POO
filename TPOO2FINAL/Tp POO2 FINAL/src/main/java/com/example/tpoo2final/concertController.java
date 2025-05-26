package com.example.tpoo2final;

import Evenement.Concert;
import Personne.Organisateur;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Hachage.HASHA.hashSHA;

public class concertController {
    @FXML
    private TextField conn1, conn2, conn3, conn4, conn5, conn6, conn7;
    @FXML
    private Button button1;
    public void Enregistrer() throws IOException {

        String identifiant = conn1.getText();
        String nom = conn2.getText();
        String date1 = conn3.getText();
        // convertion en localdate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime date = LocalDateTime.parse(date1);

        String lieu = conn4.getText();
        String artiste = conn5.getText();
        String genreMusicale = conn6.getText();
        int capaciteMax = Integer.parseInt(conn7.getText());
        ObjectMapper mapper = new ObjectMapper();


        //SERIALISATION
        File fichier = new File("Evenement.json");
        List<Concert> conc = new ArrayList<>();

        if (fichier.exists() && fichier.length() != 0) {
            try {
                // Essaye de lire comme une liste
                conc = mapper.readValue(fichier, new TypeReference<List<Concert>>() {});
            } catch (MismatchedInputException e) {
                // Si c'est un seul objet (et non une liste), lis comme un seul Organisateur
                Concert single = mapper.readValue(fichier, Concert.class);
                conc.add(single);
            }
        }

        Concert concert = new Concert(identifiant, nom, date, lieu, capaciteMax, artiste, genreMusicale);
        conc.add(concert);
        mapper.writeValue(fichier, conc);
    }
}
