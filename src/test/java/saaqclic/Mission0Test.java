package saaqclic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Mission 0 : premier push")
class Mission0Test {

    @Test
    @DisplayName("message() retourne le texte exact")
    void messageDeBienvenue() {
        assertEquals("Bienvenue sur mini-SAAQclic", Bienvenue.message(),
                "Bienvenue.message() doit retourner exactement le texte attendu");
    }
}
