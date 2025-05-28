package Personne;

import Evenement.Evenement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Organisateur extends Participant {
    private List<Evenement> evenementsOrganises = new ArrayList<>();

    public Organisateur(String id, String nom, String email) {
        super(id, nom, email);
    }

    public void ajouterEvenement(Evenement e) {
        evenementsOrganises.add(e);
    }

    public Collection<Object> getEvenementsOrganises() {
        return List.of();
    }

    public String getNom() {
        return nom;
    }
}