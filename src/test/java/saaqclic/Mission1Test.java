package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 1 : méthodes et surcharge")
class Mission1Test {

    private static final double DELTA = 0.001;

    @Test
    @DisplayName("Frais de base : 30 $ par année")
    void fraisDeBase() {
        assertEquals(30.0, Tarifs.fraisRenouvellement(1), DELTA, "1 an doit coûter 30 $");
        assertEquals(150.0, Tarifs.fraisRenouvellement(5), DELTA, "5 ans doivent coûter 150 $");
    }

    @Test
    @DisplayName("Durée nulle ou négative : 0 $")
    void dureeInvalide() {
        assertEquals(0.0, Tarifs.fraisRenouvellement(0), DELTA, "0 an doit coûter 0 $");
        assertEquals(0.0, Tarifs.fraisRenouvellement(-2), DELTA, "Une durée négative doit coûter 0 $");
    }

    @Test
    @DisplayName("Surcharge : rabais de 5 $ seulement en ligne")
    void surchargeAvecRabais() {
        assertEquals(55.0, Tarifs.fraisRenouvellement(2, true), DELTA, "2 ans en ligne : 60 $ - 5 $");
        assertEquals(60.0, Tarifs.fraisRenouvellement(2, false), DELTA, "2 ans au comptoir : aucun rabais");
    }

    @Test
    @DisplayName("Surcharge : jamais de montant négatif")
    void surchargeJamaisNegative() {
        assertEquals(0.0, Tarifs.fraisRenouvellement(0, true), DELTA,
                "0 an en ligne doit coûter 0 $, pas -5 $");
    }

    @Test
    @DisplayName("Numéro valide : une majuscule puis 12 chiffres")
    void numerosValides() {
        assertTrue(Tarifs.numeroValide("B123456789012"), "B123456789012 est valide");
        assertTrue(Tarifs.numeroValide("Z000000000000"), "Z000000000000 est valide");
    }

    @Test
    @DisplayName("Numéro invalide : longueur, lettre, chiffres")
    void numerosInvalides() {
        assertFalse(Tarifs.numeroValide("B12345"), "Trop court");
        assertFalse(Tarifs.numeroValide("B1234567890123"), "Trop long");
        assertFalse(Tarifs.numeroValide("b123456789012"), "La lettre doit être une majuscule");
        assertFalse(Tarifs.numeroValide("1123456789012"), "Le premier caractère doit être une lettre");
        assertFalse(Tarifs.numeroValide("B12345678901X"), "Après la lettre, seulement des chiffres");
    }

    @Test
    @DisplayName("Numéro null ou vide : false, sans plantage")
    void numeroNull() {
        assertFalse(Tarifs.numeroValide(null), "null doit retourner false, sans NullPointerException");
        assertFalse(Tarifs.numeroValide(""), "Une chaîne vide doit retourner false");
    }

    @Test
    @DisplayName("Javadoc de numeroValide avec @param et @return")
    void javadocEcrite() throws IOException {
        String source = Files.readString(Path.of("src/main/java/saaqclic/Tarifs.java"));
        Pattern javadoc = Pattern.compile(
                "/\\*\\*(?:(?!\\*/).)*@param\\s+numero(?:(?!\\*/).)*@return(?:(?!\\*/).)*\\*/\\s*"
                        + "public static boolean numeroValide",
                Pattern.DOTALL);
        assertTrue(javadoc.matcher(source).find(),
                "Ajoutez un commentaire /** ... */ juste avant numeroValide, avec @param numero et @return");
    }
}
