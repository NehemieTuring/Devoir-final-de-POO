package Evenement;

// Classe abstraite Evenement
import Exceptions.CapaciteMaxAtteinteException;
import jakarta.xml.bind.annotation.XmlTransient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Evenement {
    protected String id;
    public String nom;
    protected LocalDateTime date;
    protected String lieu;
    protected int capaciteMax;
    @XmlTransient
    public List<ParticipantObserver> participants = new ArrayList<>();

    public Evenement(){}
    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }


    public void ajouterParticipant(ParticipantObserver participant) throws CapaciteMaxAtteinteException {
        if (participants.size() >= capaciteMax) throw new CapaciteMaxAtteinteException("Capacité maximale atteinte.");
        participants.add(participant);
    }

    public void annuler() {
        notifierParticipants("L'événement " + nom + " a été annulé.");
    }

    public void afficherDetails() {
        System.out.println(this);
    }

    protected void notifierParticipants(String message) {
        for (ParticipantObserver p : participants) {
            p.recevoirNotification(message);
        }
    }

    @Override
    public String toString() {
        return nom + " (" + date + ", " + lieu + ")";
    }

    public void supprimerParticipant(String text) {
    }

}



