package com.example.tpoo2final;

import Personne.Organisateur;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static Hachage.HASHA.hashSHA;

public class connection {
    @FXML
    private TextField conn1, conn2,conn4, conn3;
    @FXML
    private Label labelcon;
    public void SeConnecter() {
        String Hacher;
        String identifiant = conn1.getText();
        String nom = conn2.getText();
        String motsDePasse = conn3.getText();
        String emails = conn4.getText();
        Hacher = hashSHA(motsDePasse);
        ObjectMapper mapper = new ObjectMapper();

       try {
            // Charger tous les utilisateurs
            List<Organisateur> organ = mapper.readValue(new File("Organisateurs.json"), new TypeReference<List<Organisateur>>() {});

            // Vérification
            for (Organisateur o : organ) {
                /*System.out.println(o.getId());
                System.out.println(o.getNom());
                System.out.println(o.getEmail());
                System.out.println( o.getMotsDePasse());
                System.out.println( "\n\n");
                if (o.getId().equals(identifiant)){
                    System.out.println("identifiant correct");
                }
                if (o.getNom().equals(nom)){
                    System.out.println("nom correct");
                }
                else{
                    System.out.println(o.getNom());
                    System.out.println(nom);
                }
                if (o.getEmail().equals(emails)){
                    System.out.println("Email correct");
                }
                if ( o.getMotsDePasse().equals(Hacher)){
                    System.out.println("mots de passe correct");
                }
                System.out.println( "\n\n");
                  /*{
                    labelcon.setText("connexion réussie");
                    /*System.out.println(o.getId() +"="+ identifiant);
                    System.out.println( o.getMotsDePasse() +"="+ Hacher);
                    System.out.println(o.getNom() +"="+ nom);*/
                if(o.getId().equals(identifiant) && o.getNom().equals(nom) && o.getEmail().equals(emails) && o.getMotsDePasse().equals(Hacher)){
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
                }else{
                    labelcon.setText("connexion échoué");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void sinscire() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sinscrire.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("INSCRIPTION");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}



