package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 3 : static et références")
class Mission3Test {

    @Test
    @DisplayName("nombreEmis compte chaque permis une seule fois")
    void compteur() {
        int avant = Permis.nombreEmis();
        new Permis("B123456789012", "Lina Tremblay", 2);
        assertEquals(avant + 1, Permis.nombreEmis(), "Le constructeur à 3 paramètres doit compter 1 permis");
        new Permis("C123456789012", "Marc Roy");
        assertEquals(avant + 2, Permis.nombreEmis(),
                "Le constructeur à 2 paramètres doit compter 1 permis, pas 2");
    }

    @Test
    @DisplayName("corrigerTitulaire modifie l'objet reçu")
    void corrigerTitulaire() {
        Permis p = new Permis("B123456789012", "Lina Tremblai");
        Guichet.corrigerTitulaire(p, "Lina Tremblay");
        assertEquals("Lina Tremblay", p.getTitulaire(),
                "L'objet d'origine n'a pas changé : réassigner le paramètre ne touche que la copie de la référence");
    }

    @Test
    @DisplayName("corrigerTitulaire ne crée aucun nouveau permis")
    void corrigerSansCreer() {
        Permis p = new Permis("B123456789012", "Lina Tremblai");
        int avant = Permis.nombreEmis();
        Guichet.corrigerTitulaire(p, "Lina Tremblay");
        assertEquals(avant, Permis.nombreEmis(), "Une correction ne doit pas émettre un nouveau permis");
    }

    @Test
    @DisplayName("remplacer retourne un nouvel objet et garde l'ancien intact")
    void remplacer() {
        Permis ancien = new Permis("B123456789012", "Lina Tremblay", 6);
        Permis nouveau = Guichet.remplacer(ancien, "B999999999999");
        assertNotSame(ancien, nouveau, "remplacer doit créer un nouvel objet");
        assertEquals("B999999999999", nouveau.getNumero(), "Le nouveau permis porte le nouveau numéro");
        assertEquals("Lina Tremblay", nouveau.getTitulaire(), "Le titulaire est conservé");
        assertEquals(6, nouveau.getPoints(), "Les points sont conservés");
        assertEquals("B123456789012", ancien.getNumero(), "L'ancien permis ne doit pas changer");
    }

    @Test
    @DisplayName("compterSuspendus compte et ignore les cases null")
    void compterSuspendus() {
        Permis[] permis = {
            new Permis("A000000000001", "A", 15),
            null,
            new Permis("A000000000002", "B", 3),
            new Permis("A000000000003", "C", 20)
        };
        assertEquals(2, Guichet.compterSuspendus(permis), "2 permis sur 3 sont suspendus, et une case est null");
    }

    @Test
    @DisplayName("compterSuspendus sur un tableau vide : 0")
    void compterVide() {
        assertEquals(0, Guichet.compterSuspendus(new Permis[0]), "Un tableau vide contient 0 permis suspendu");
    }
}
