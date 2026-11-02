package saaqclic;

/** Mission 3 : méthodes static et passage de paramètres. */
public class Guichet {

    public static void corrigerTitulaire(Permis permis, String nouveauTitulaire) {
        // Mission 3 : ce code compile, mais il ne corrige rien pour l'appelant. Pourquoi ?
        // Il vous faudra ajouter une méthode dans Permis.
        permis = new Permis(permis.getNumero(), nouveauTitulaire, permis.getPoints());
    }

    public static Permis remplacer(Permis ancien, String nouveauNumero) {
        // TODO Mission 3 : retournez un nouveau permis, même titulaire et mêmes points.
        throw new UnsupportedOperationException("TODO Mission 3 : remplacer");
    }

    public static int compterSuspendus(Permis[] permis) {
        // TODO Mission 3 : comptez les permis suspendus. Certaines cases peuvent valoir null.
        throw new UnsupportedOperationException("TODO Mission 3 : compterSuspendus");
    }
}
