package com.example.tpoo2final;

import Evenement.Evenement;
import Personne.Organisateur;
import Personne.Participant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Hachage.HASHA.hashSHA;

public class ParticipantContoller {
    @FXML
    private TextField conn1, conn2, conn3, conn4;
    public void Enregistrer() throws IOException {
        boolean valeur1 = false;
        boolean valeur2 = false;
        ObjectMapper mapper = new ObjectMapper();
        String identifiant = conn1.getText();
        String nom = conn2.getText();
        String email = conn3.getText();
        String IdEven = conn4.getText();

        File fichier = new File("Participant.json");
        List<Participant> part = new ArrayList<>();

        if (fichier.exists() && fichier.length() != 0) {
            List<Participant> part0 = mapper.readValue(new File("Participant.json"), new TypeReference<List<Participant>>() {});
            List<Evenement> even = mapper.readValue(new File("Evenement.json"), new TypeReference<List<Evenement>>() {});
            for (Participant p : part0) {
                if(p.getId().equals(identifiant)){
                    valeur1 = true;
                }
            }
            for (Evenement e : even) {
                if(e.getId().equals(IdEven)){
                    valeur2 = true;
                }
            }
            if (valeur1 == false && valeur2 == true){
                try {
                    // Essaye de lire comme une liste
                    part = mapper.readValue(fichier, new TypeReference<List<Participant>>() {});
                } catch (MismatchedInputException e) {
                    // Si c'est un seul objet (et non une liste), lis comme un seul participant
                    Participant single = mapper.readValue(fichier, Participant.class);
                    part.add(single);
                }
                Participant participant = new Participant(identifiant, nom, email);
                part.add(participant);
                mapper.writeValue(fichier,part);
            }else{
                System.out.println(identifiant);
            }
        }

    }
}
