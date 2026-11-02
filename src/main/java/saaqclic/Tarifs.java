package saaqclic;

/** Mission 1 : méthodes, surcharge et Javadoc. Tarifs fictifs. */
public class Tarifs {

    public static final double TARIF_ANNUEL = 30.0;
    public static final double RABAIS_EN_LIGNE = 5.0;

    public static double fraisRenouvellement(int annees) {
        // TODO Mission 1 : TARIF_ANNUEL par année. Une durée nulle ou négative coûte 0.
        throw new UnsupportedOperationException("TODO Mission 1 : fraisRenouvellement(int)");
    }

    public static double fraisRenouvellement(int annees, boolean enLigne) {
        // TODO Mission 1 : réutilisez la méthode ci-dessus au lieu de recopier le calcul.
        // En ligne, on retire RABAIS_EN_LIGNE, sans jamais descendre sous 0.
        throw new UnsupportedOperationException("TODO Mission 1 : fraisRenouvellement(int, boolean)");
    }

    // TODO Mission 1 : écrivez ici la Javadoc de numeroValide (paramètre et valeur de retour).
    public static boolean numeroValide(String numero) {
        // TODO Mission 1 : une lettre majuscule (A à Z) suivie de 12 chiffres, 13 caractères en tout.
        // Si numero vaut null, retournez false. Character.isDigit(c) vous sera utile.
        throw new UnsupportedOperationException("TODO Mission 1 : numeroValide");
    }
}
