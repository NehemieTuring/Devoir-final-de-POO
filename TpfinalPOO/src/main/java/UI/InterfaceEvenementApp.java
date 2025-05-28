package UI;

// compiler avec: mvn clean javafx:run

import Evenement.Conference;
import Evenement.Evenement;
import Personne.Intervenant;
import Personne.Organisateur;
import Personne.Participant;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Evenement.GestionEvenements;
import Exceptions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class InterfaceEvenementApp extends Application {

    private GestionEvenements gestion = GestionEvenements.getInstance();
    private TextArea output = new TextArea();
    private TextField idEventField = new TextField();
    private TextField nomEventField = new TextField();
    private TextField lieuField = new TextField();
    private TextField capaciteField = new TextField();
    private TextField themeField = new TextField();
    private TextField intervenantField = new TextField();
    private TextField participantIdField = new TextField();
    private TextField participantNomField = new TextField();
    private TextField participantEmailField = new TextField();
    private TextField organisateurIdField = new TextField();
    private Organisateur organisateurConnecte = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion d'Événements");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox loginBox = new HBox(10);
        loginBox.getChildren().addAll(new Label("ID Organisateur:"), organisateurIdField, new Button("Connexion") {{
            setOnAction(e -> connecterOrganisateur());
        }});

        GridPane eventForm = new GridPane();
        eventForm.setHgap(5);
        eventForm.setVgap(5);
        eventForm.addRow(0, new Label("ID:"), idEventField);
        eventForm.addRow(1, new Label("Nom:"), nomEventField);
        eventForm.addRow(2, new Label("Lieu:"), lieuField);
        eventForm.addRow(3, new Label("Capacité Max:"), capaciteField);
        eventForm.addRow(4, new Label("Thème:"), themeField);
        eventForm.addRow(5, new Label("Intervenant:"), intervenantField);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(
                new Button("Ajouter") {{
                    setOnAction(e -> ajouterEvenement());
                }},
                new Button("Supprimer") {{
                    setOnAction(e -> supprimerEvenement());
                }},
                new Button("Rechercher") {{
                    setOnAction(e -> rechercherEvenement());
                }}
        );

        GridPane participantForm = new GridPane();
        participantForm.setHgap(5);
        participantForm.setVgap(5);
        participantForm.addRow(0, new Label("ID Participant:"), participantIdField);
        participantForm.addRow(1, new Label("Nom:"), participantNomField);
        participantForm.addRow(2, new Label("Email:"), participantEmailField);

        HBox participantButtons = new HBox(10);
        participantButtons.getChildren().addAll(
                new Button("Ajouter Participant") {{
                    setOnAction(e -> ajouterParticipant());
                }},
                new Button("Supprimer Participant") {{
                    setOnAction(e -> supprimerParticipant());
                }}
        );

        root.getChildren().addAll(
                loginBox,
                new Separator(),
                new Label("Créer un événement (conférence) :"),
                eventForm,
                buttonBox,
                new Separator(),
                new Label("Gérer les participants :"),
                participantForm,
                participantButtons,
                new Separator(),
                new Label("Résultats / Logs :"),
                output
        );

        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }

    private void connecterOrganisateur() {
        String id = organisateurIdField.getText();
        organisateurConnecte = new Organisateur(id, "Organisateur " + id, id + "@mail.com");
        output.appendText("Organisateur connecté : " + organisateurConnecte.getNom() + "\n");
    }

    private void ajouterEvenement() {
        if (organisateurConnecte == null) {
            output.appendText("Veuillez vous connecter comme organisateur.\n");
            return;
        }
        try {
            List<Intervenant> intervenants = new ArrayList<>();
            intervenants.add(new Intervenant(intervenantField.getText()));
            Conference conf = new Conference(
                    idEventField.getText(),
                    nomEventField.getText(),
                    LocalDateTime.now().plusDays(1),
                    lieuField.getText(),
                    Integer.parseInt(capaciteField.getText()),
                    themeField.getText(),
                    intervenants
            );
            gestion.ajouterEvenement(conf);
            organisateurConnecte.ajouterEvenement(conf);
            output.appendText("Événement ajouté avec succès.\n");
        } catch (Exception e) {
            output.appendText("Erreur : " + e.getMessage() + "\n");
        }
    }

    private void supprimerEvenement() {
        gestion.supprimerEvenement(idEventField.getText());
        output.appendText("Événement supprimé.\n");
    }

    private void rechercherEvenement() {
        Evenement e = gestion.rechercherEvenement(idEventField.getText());
        if (e != null) {
            output.appendText("Trouvé : ");
        } else {
            output.appendText("Événement introuvable.\n");
        }
    }

    private void ajouterParticipant() {
        try {
            Participant p = new Participant(
                    participantIdField.getText(),
                    participantNomField.getText(),
                    participantEmailField.getText()
            );
            Evenement e = gestion.rechercherEvenement(idEventField.getText());
            if (e != null) {
                e.ajouterParticipant(p);
                output.appendText("Participant ajouté.\n");
            } else {
                output.appendText("Événement introuvable.\n");
            }
        } catch (Exception e) {
            output.appendText("Erreur : " + e.getMessage() + "\n");
        }
    }

    private void supprimerParticipant() {
        Evenement e = gestion.rechercherEvenement(idEventField.getText());
        if (e != null) {
            e.supprimerParticipant(participantIdField.getText());
            output.appendText("Participant supprimé.\n");
        } else {
            output.appendText("Événement introuvable.\n");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
