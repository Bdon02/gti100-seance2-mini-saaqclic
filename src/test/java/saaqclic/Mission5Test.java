package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 5 : polymorphisme")
class Mission5Test {

    private static final double DELTA = 0.001;

    private static Transaction renouvellement(String ref, int annees, boolean enLigne) {
        Object o = new Renouvellement(ref, annees, enLigne);
        return assertInstanceOf(Transaction.class, o, "Renouvellement doit hériter de Transaction");
    }

    private static Transaction immatriculation(String ref, String plaque) {
        Object o = new Immatriculation(ref, plaque);
        return assertInstanceOf(Transaction.class, o, "Immatriculation doit hériter de Transaction");
    }

    @Test
    @DisplayName("Renouvellement : montant et description")
    void renouvellement() {
        Transaction t = renouvellement("R-1", 2, true);
        assertEquals(55.0, t.montant(), DELTA, "montant() doit réutiliser Tarifs : 2 ans en ligne font 55 $");
        assertEquals("Renouvellement 2 an(s)", t.description(), "description() doit donner le nombre d'années");
    }

    @Test
    @DisplayName("Immatriculation : montant et description")
    void immatriculation() {
        Transaction t = immatriculation("I-1", "ABC 123");
        assertEquals(120.0, t.montant(), DELTA, "Une immatriculation coûte 120 $");
        assertEquals("Immatriculation ABC 123", t.description(), "description() doit donner la plaque");
    }

    @Test
    @DisplayName("recu() du parent appelle les méthodes de l'enfant")
    void recuPolymorphe() {
        assertEquals("R-1 | Renouvellement 2 an(s) | 55.0 $", renouvellement("R-1", 2, true).recu(),
                "Le reçu doit utiliser reference, description() et montant()");
        assertEquals("I-1 | Immatriculation ABC 123 | 120.0 $", immatriculation("I-1", "ABC 123").recu(),
                "Le reçu doit utiliser reference, description() et montant()");
    }

    @Test
    @DisplayName("Caisse.total additionne un mélange de transactions")
    void total() {
        Transaction[] panier = {
            renouvellement("R-1", 2, true),
            immatriculation("I-1", "ABC 123"),
            renouvellement("R-2", 1, false)
        };
        assertEquals(205.0, Caisse.total(panier), DELTA, "55 $ + 120 $ + 30 $ font 205 $");
    }

    @Test
    @DisplayName("Caisse.total ignore les cases null")
    void totalAvecNull() {
        Transaction[] panier = {null, immatriculation("I-1", "ABC 123"), null};
        assertEquals(120.0, Caisse.total(panier), DELTA, "Les cases null valent 0 $");
    }

    @Test
    @DisplayName("Caisse.total sur un panier vide : 0")
    void totalVide() {
        assertEquals(0.0, Caisse.total(new Transaction[0]), DELTA, "Un panier vide vaut 0 $");
    }
}
