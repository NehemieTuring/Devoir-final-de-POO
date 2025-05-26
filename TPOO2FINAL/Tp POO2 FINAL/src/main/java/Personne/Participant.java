package Personne;

public class Participant{
    protected String id;
    protected String nom;
    protected String email;
    public Participant(String Id, String Nom, String Email){
        this.id = Id;
        this.nom = Nom;
        this.email = Email;
    }

    public Participant() {

    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
