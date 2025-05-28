package test;

import Evenement.Conference;
import Evenement.Evenement;
import Personne.Intervenant;
import Personne.Organisateur;
import Personne.Participant;
import Evenement.GestionEvenements;
import Exceptions.CapaciteMaxAtteinteException;
import Exceptions.EvenementDejaExistantException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionEvenementsTest {

    private GestionEvenements gestion;
    private Conference conf;
    private Participant participant;

    @BeforeEach
    public void setUp() throws Exception {
        gestion = GestionEvenements.getInstance();
        gestion.supprimerEvenement("C1");
        conf = new Conference("C1", "Conférence IA", LocalDateTime.now().plusDays(1),
                "Amphi A", 2, "Intelligence Artificielle",
                List.of(new Intervenant("Dr. Jean")));
        participant = new Participant("P1", "Alice", "alice@mail.com");
    }

    @Test
    public void testAjouterEtRechercherEvenement() throws Exception {
        gestion.ajouterEvenement(conf);
        Evenement e = gestion.rechercherEvenement("C1");
        assertNotNull(e);
        assertEquals("Conférence IA",
                e.nom);
    }

    @Test
    public void testEvenementDejaExistantException() throws Exception {
        gestion.ajouterEvenement(conf);
        assertThrows(EvenementDejaExistantException.class, () -> {
            gestion.ajouterEvenement(conf);
        });
    }

    @Test
    public void testSupprimerEvenement() throws Exception {
        gestion.ajouterEvenement(conf);
        gestion.supprimerEvenement("C1");
        assertNull(gestion.rechercherEvenement("C1"));
    }

    @Test
    public void testAjouterParticipant() throws Exception {
        conf.ajouterParticipant(participant);
        assertEquals(1, conf.participants.size());
    }

    @Test
    public void testCapaciteMaxAtteinteException() throws Exception {
        conf.ajouterParticipant(new Participant("P2", "Bob", "bob@mail.com"));
        conf.ajouterParticipant(new Participant("P3", "Charlie", "charlie@mail.com"));
        assertThrows(CapaciteMaxAtteinteException.class, () -> {
            conf.ajouterParticipant(new Participant("P4", "Diane", "diane@mail.com"));
        });
    }

    @Test
    public void testAnnulationNotification() throws Exception {
        conf.ajouterParticipant(participant);
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        conf.annuler();
        assertTrue(outContent.toString().contains("Notification à Alice: L'événement Conférence IA a été annulé."));
    }

    @Test
    public void testSerializationJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(conf);
        assertNotNull(json);
        assertTrue(json.contains("Conférence IA"));

        Conference clone = mapper.readValue(json, Conference.class);
        assertEquals(conf.nom, clone.nom);
    }

    @Test
    public void testSerializationXML() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Conference.class);
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(conf, writer);

        String xml = writer.toString();
        assertNotNull(xml);
        assertTrue(xml.contains("Conférence IA"));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Conference clone = (Conference) unmarshaller.unmarshal(new StringReader(xml));
        assertEquals(conf.nom, clone.nom);
    }

    @Test
    public void testOrganisateurAjouteEvenement() throws Exception {
        Organisateur orga = new Organisateur("O1", "Organisateur1", "orga@mail.com");
        orga.ajouterEvenement(conf);
        assertTrue(orga.getEvenementsOrganises().contains(conf));
    }
}

