package saaqclic;

/** Mission 5 : classe parente de toutes les transactions. Fournie, ne pas modifier. */
public class Transaction {

    protected String reference;

    public Transaction(String reference) {
        this.reference = reference;
    }

    public double montant() {
        return 0.0;
    }

    public String description() {
        return "Transaction";
    }

    public String recu() {
        return reference + " | " + description() + " | " + montant() + " $";
    }
}
