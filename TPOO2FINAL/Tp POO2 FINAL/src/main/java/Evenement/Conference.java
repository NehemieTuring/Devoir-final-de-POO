package Evenement;

import Personne.Intervenant;

import java.time.LocalDateTime;
import java.util.List;


public class Conference extends Evenement{
    protected List<String> intervenants;
    protected String theme;

    public Conference(String identifiant, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme, List<String> intervenants) {
        super(identifiant, nom, date, lieu, capaciteMax );
        this.theme = theme;
        this.intervenants = intervenants;
    }

    public List<String> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(List<String> intervenants) {
        this.intervenants = intervenants;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}




