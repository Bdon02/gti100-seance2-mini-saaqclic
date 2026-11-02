package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 2 : classe Permis")
class Mission2Test {

    @Test
    @DisplayName("Le constructeur complet remplit les trois attributs")
    void constructeurComplet() {
        Permis p = new Permis("B123456789012", "Lina Tremblay", 3);
        assertEquals("B123456789012", p.getNumero(), "numero n'est pas enregistré : pensez à this");
        assertEquals("Lina Tremblay", p.getTitulaire(), "titulaire n'est pas enregistré : pensez à this");
        assertEquals(3, p.getPoints(), "points n'est pas enregistré : pensez à this");
    }

    @Test
    @DisplayName("Le constructeur court part de 0 point")
    void constructeurCourt() {
        Permis p = new Permis("B123456789012", "Lina Tremblay");
        assertEquals("B123456789012", p.getNumero(), "Le constructeur à 2 paramètres doit appeler this(...)");
        assertEquals("Lina Tremblay", p.getTitulaire(), "Le constructeur à 2 paramètres doit appeler this(...)");
        assertEquals(0, p.getPoints(), "Un nouveau permis commence à 0 point");
    }

    @Test
    @DisplayName("Points négatifs au constructeur : ramenés à 0")
    void pointsNegatifs() {
        Permis p = new Permis("B123456789012", "Lina Tremblay", -4);
        assertEquals(0, p.getPoints(), "Un permis ne peut pas avoir moins de 0 point");
    }

    @Test
    @DisplayName("ajouterPoints cumule les points")
    void ajouterPoints() {
        Permis p = new Permis("B123456789012", "Lina Tremblay");
        p.ajouterPoints(2);
        p.ajouterPoints(3);
        assertEquals(5, p.getPoints(), "2 points puis 3 points font 5 points");
    }

    @Test
    @DisplayName("ajouterPoints ignore les nombres négatifs ou nuls")
    void ajouterPointsInvalides() {
        Permis p = new Permis("B123456789012", "Lina Tremblay", 5);
        p.ajouterPoints(-2);
        p.ajouterPoints(0);
        assertEquals(5, p.getPoints(), "ajouterPoints ne doit jamais diminuer les points");
    }

    @Test
    @DisplayName("Suspendu à partir de la limite, pas avant")
    void suspension() {
        Permis p = new Permis("B123456789012", "Lina Tremblay", 14);
        assertFalse(p.estSuspendu(), "À 14 points, le permis n'est pas suspendu");
        p.ajouterPoints(1);
        assertTrue(p.estSuspendu(), "À 15 points, le permis est suspendu");
    }

    @Test
    @DisplayName("Deux objets ont chacun leur état")
    void objetsIndependants() {
        Permis a = new Permis("B123456789012", "Lina Tremblay");
        Permis b = new Permis("C123456789012", "Marc Roy");
        a.ajouterPoints(4);
        assertEquals(0, b.getPoints(), "points doit être un attribut d'instance, pas un attribut static");
    }
}
