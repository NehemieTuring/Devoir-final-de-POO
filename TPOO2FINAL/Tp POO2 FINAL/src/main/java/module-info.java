module com.example.tpoo2final {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    //requires org.junit.jupiter.api;

    exports Evenement to com.fasterxml.jackson.databind;

    opens com.example.tpoo2final to javafx.fxml;
    exports com.example.tpoo2final;
    exports Hachage;
    opens Hachage to javafx.fxml;
    opens Personne to com.fasterxml.jackson.databind; // ← autorise Jackson à accéder aux classes
    exports Personne;
}

