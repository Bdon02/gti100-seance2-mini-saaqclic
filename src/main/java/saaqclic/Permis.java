package saaqclic;

/** Missions 2 et 3 : un permis de conduire. Règles simplifiées pour l'exercice. */
public class Permis {

    // TODO Mission 3 : ajoutez ici un attribut partagé par tous les permis pour les compter.

    private String numero;
    private String titulaire;
    private int points;

    public Permis(String numero, String titulaire, int points) {
        // Mission 2 : ce constructeur contient un bogue. Les tests vous aideront à le trouver.
        // Il doit aussi ramener à 0 un nombre de points négatif.
        numero = numero;
        titulaire = titulaire;
        points = points;
    }

    public Permis(String numero, String titulaire) {
        // TODO Mission 2 : appelez l'autre constructeur avec 0 point.
    }

    public static int nombreEmis() {
        // TODO Mission 3 : retournez le nombre de permis créés depuis le début du programme.
        throw new UnsupportedOperationException("TODO Mission 3 : nombreEmis");
    }

    public String getNumero() {
        throw new UnsupportedOperationException("TODO Mission 2 : getNumero");
    }

    public String getTitulaire() {
        throw new UnsupportedOperationException("TODO Mission 2 : getTitulaire");
    }

    public int getPoints() {
        throw new UnsupportedOperationException("TODO Mission 2 : getPoints");
    }

    public void ajouterPoints(int nombre) {
        // TODO Mission 2 : ajoutez nombre aux points, seulement si nombre est positif.
        throw new UnsupportedOperationException("TODO Mission 2 : ajouterPoints");
    }

    public boolean estSuspendu() {
        // TODO Mission 2 : suspendu dès que les points atteignent limitePoints().
        throw new UnsupportedOperationException("TODO Mission 2 : estSuspendu");
    }

    /** Seuil de points d'inaptitude de ce permis. */
    public int limitePoints() {
        return 15;
    }

    @Override
    public String toString() {
        return numero + " (" + titulaire + ") : " + points + " pts";
    }
}
