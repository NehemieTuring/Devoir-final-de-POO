package Personne;

import Evenement.ParticipantObserver;

public class Participant implements ParticipantObserver {
    protected String id;
    protected String nom;
    protected String email;

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    @Override
    public void recevoirNotification(String message) {
        System.out.println("Notification à " + nom + ": " + message);
    }
}