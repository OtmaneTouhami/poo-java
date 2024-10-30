package Exercice3;

/**
 * Représente une ligne de commande dans le système de gestion.
 * Cette classe fait le lien entre une commande et un ordinateur commandé.
 */
public class LigneCommande {
    // Attributes
    private int quantite;
    private Commande commande;
    private Ordinateur ordinateur;


    /**
     * Constructeur avec tous les paramètres.
     * @param quantite Quantité comm   andée
     * @param commande Commande associée
     * @param ordinateur Ordinateur com mandé
     */
    public LigneCommande(int quantite, Commande commande, Ordinateur ordinateur) {
        this.quantite = quantite;
        this.commande = commande;
        this.ordinateur = ordinateur;
    }
    /** Constructeur par défaut */
    public LigneCommande() {}

    /** Getters and Setters */
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Commande getCommande() {
        return commande;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public Ordinateur getOrdinateur() {
        return ordinateur;
    }
    public void setOrdinateur(Ordinateur ordinateur) {
        this.ordinateur = ordinateur;
    }

    /**
     * Retourne une représentation textuelle de la ligne de commande.
     * @return String contenant les détails de la ligne de commande
     */
    @Override
    public String toString() {
        return "Détails de la Ligne de Commande:\n" +
                "  Quantité: " + quantite + "\n" +
                "  Commande: " +
                (commande != null ? commande.toString() : "Aucune commande associée") +
                "\n" +
                "  Ordinateur: " +
                (ordinateur != null ? ordinateur.toString() : "Aucun ordinateur associé") +
                "\n";
    }
}
