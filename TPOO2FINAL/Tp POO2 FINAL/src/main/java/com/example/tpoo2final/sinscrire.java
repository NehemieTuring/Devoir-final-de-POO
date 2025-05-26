package com.example.tpoo2final;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import Personne.Organisateur;

import static Hachage.HASHA.hashSHA;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sinscrire {
    @FXML
    private TextField conn1, conn2, conn3, conn4;
    public void Enregistrer() throws IOException {

        String Hacher;
        String identifiant = conn1.getText();
        String nom = conn2.getText();
        String email = conn3.getText();
        String motsDePasse = conn4.getText();
        ObjectMapper mapper = new ObjectMapper();
        Hacher = hashSHA(motsDePasse);

        //SERIALISATION
        File fichier = new File("Organisateurs.json");
        List<Organisateur> organ = new ArrayList<>();

        if (fichier.exists() && fichier.length() != 0) {
            try {
                // Essaye de lire comme une liste
                organ = mapper.readValue(fichier, new TypeReference<List<Organisateur>>() {});
            } catch (MismatchedInputException e) {
                // Si c'est un seul objet (et non une liste), lis comme un seul Organisateur
                Organisateur single = mapper.readValue(fichier, Organisateur.class);
                organ.add(single);
            }
        }

        Organisateur organisateur = new Organisateur(identifiant, nom, email, Hacher);
        organ.add(organisateur);
        mapper.writeValue(fichier, organ);
    }
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
}
