package Evenement;

import Personne.Intervenant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Concert extends Evenement{
    protected String identifiant;
    protected String nom;
    protected LocalDate date;
    protected String lieu;
    protected int capaciteMax;
    protected String artiste;
    protected String genreMusicale;
    public Concert(String identifiant, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusicale){
        super(identifiant, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusicale = genreMusicale;
    }

    public String getGenreMusicale() {
        return genreMusicale;
    }

    public void setGenreMusicale(String genreMusicale) {
        this.genreMusicale = genreMusicale;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }
}
