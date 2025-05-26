package Personne;

import Evenement.Evenement;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

import java.util.List;

public class Organisateur extends Participant{
    @JsonProperty("Mots de passe")
    protected String motsDePasse;
    List <Evenement> liste1 = new ArrayList<>();

    public Organisateur() {
        super(); // Appelle le constructeur vide de Participant (à ajouter aussi si nécessaire)
    }

    public Organisateur(String Id, String Nom, String Email, String Motsdepasse) {
        super(Id, Nom, Email);
        this.motsDePasse= Motsdepasse;
    }

    public String getMotsDePasse() {
        return motsDePasse;
    }
}
