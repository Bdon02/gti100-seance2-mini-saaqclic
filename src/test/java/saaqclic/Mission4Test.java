package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 4 : héritage")
class Mission4Test {

    private static Permis apprenti(String numero, String titulaire) {
        Object o = new PermisApprenti(numero, titulaire);
        return assertInstanceOf(Permis.class, o, "PermisApprenti doit hériter de Permis avec extends");
    }

    @Test
    @DisplayName("PermisApprenti est un Permis")
    void heritage() {
        apprenti("A123456789012", "Noa Gagnon");
    }

    @Test
    @DisplayName("super(...) transmet numéro et titulaire au parent")
    void constructeurParent() {
        Permis p = apprenti("A123456789012", "Noa Gagnon");
        assertEquals("A123456789012", p.getNumero(), "Le constructeur doit appeler super(numero, titulaire)");
        assertEquals("Noa Gagnon", p.getTitulaire(), "Le constructeur doit appeler super(numero, titulaire)");
        assertEquals(0, p.getPoints(), "Un apprenti commence à 0 point");
    }

    @Test
    @DisplayName("Un apprenti est suspendu à 4 points")
    void limiteApprenti() {
        Permis p = apprenti("A123456789012", "Noa Gagnon");
        p.ajouterPoints(3);
        assertFalse(p.estSuspendu(), "À 3 points, un apprenti n'est pas suspendu");
        p.ajouterPoints(1);
        assertTrue(p.estSuspendu(),
                "À 4 points, un apprenti est suspendu : estSuspendu() doit appeler limitePoints()");
    }

    @Test
    @DisplayName("La limite est redéfinie, pas estSuspendu()")
    void redefinitionAuBonEndroit() {
        assertEquals(PermisApprenti.class, classeQuiDeclare("limitePoints"),
                "PermisApprenti doit redéfinir limitePoints()");
        assertThrows(NoSuchMethodException.class,
                () -> PermisApprenti.class.getDeclaredMethod("estSuspendu"),
                "Ne redéfinissez pas estSuspendu() : la règle est déjà dans le parent");
    }

    private static Class<?> classeQuiDeclare(String nom) {
        try {
            return PermisApprenti.class.getMethod(nom).getDeclaringClass();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Test
    @DisplayName("Un permis ordinaire garde sa limite de 15")
    void parentIntact() {
        assertEquals(15, new Permis("B123456789012", "Marc Roy").limitePoints(),
                "La limite de Permis doit rester 15");
    }

    @Test
    @DisplayName("toString réutilise super.toString()")
    void affichage() {
        Permis p = apprenti("A123456789012", "Noa Gagnon");
        assertEquals("A123456789012 (Noa Gagnon) : 0 pts [apprenti]", p.toString(),
                "toString doit ajouter \" [apprenti]\" au résultat de super.toString()");
    }

    @Test
    @DisplayName("Guichet fonctionne avec un mélange de permis, sans modification")
    void polymorphisme() {
        Permis apprenti = apprenti("A123456789012", "Noa Gagnon");
        apprenti.ajouterPoints(5);
        Permis ordinaire = new Permis("B123456789012", "Marc Roy", 5);
        assertEquals(1, Guichet.compterSuspendus(new Permis[] {apprenti, ordinaire}),
                "Avec 5 points, l'apprenti est suspendu et le permis ordinaire ne l'est pas");
    }
}
