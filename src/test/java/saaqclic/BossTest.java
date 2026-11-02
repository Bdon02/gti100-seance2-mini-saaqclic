package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** La classe Amende n'existe pas au départ : ce test la cherche par son nom. */
@DisplayName("Boss : ajouter une transaction sans toucher à Caisse")
class BossTest {

    private static final double DELTA = 0.001;

    private static Class<?> classeAmende() {
        try {
            return Class.forName("saaqclic.Amende");
        } catch (ClassNotFoundException e) {
            fail("Créez le fichier src/main/java/saaqclic/Amende.java");
            return null;
        }
    }

    private static Transaction amende(String reference, double base, int jours) throws Exception {
        Class<?> classe = classeAmende();
        assertTrue(Transaction.class.isAssignableFrom(classe), "Amende doit hériter de Transaction");
        Constructor<?> constructeur;
        try {
            constructeur = classe.getConstructor(String.class, double.class, int.class);
        } catch (NoSuchMethodException e) {
            fail("Amende doit avoir le constructeur public Amende(String, double, int)");
            return null;
        }
        return (Transaction) constructeur.newInstance(reference, base, jours);
    }

    @Test
    @DisplayName("Montant : base + 2 $ par jour de retard")
    void montant() throws Exception {
        assertEquals(100.0, amende("A-1", 100.0, 0).montant(), DELTA, "Sans retard : le montant de base");
        assertEquals(120.0, amende("A-2", 100.0, 10).montant(), DELTA, "10 jours de retard ajoutent 20 $");
    }

    @Test
    @DisplayName("Description et reçu")
    void recu() throws Exception {
        assertEquals("A-2 | Amende (10 j de retard) | 120.0 $", amende("A-2", 100.0, 10).recu(),
                "description() doit retourner « Amende (10 j de retard) »");
    }

    @Test
    @DisplayName("Caisse accepte l'amende sans modification")
    void caisse() throws Exception {
        Transaction[] panier = {amende("A-3", 50.0, 5), amende("A-4", 120.0, 0)};
        assertEquals(180.0, Caisse.total(panier), DELTA, "60 $ + 120 $ font 180 $");
    }
}
