package Exercice3;

/**
 * Représente un ordinateur dans le système de gestion de commandes.
 * Cette classe gère les caractéristiques et spécifications d'un ordinateur.
 */
public class Ordinateur {
    /** Attributes */
    private String nom;
    private String marque;
    private Float prix;
    private String description;
    private int nbStock;
    private Categorie categorie;

    /**
     * Constructeur avec tous les paramètres.
     * @param nom Nom d'ordinateur
     * @param marque Marque d'ordinateur
     * @param prix Prix d'ordinateur
     * @param description Description d'ordinateur
     * @param nbStock La quantite au stock d'ordinateur
     * @param categorie Categorie d'ordinateur
     */
    public Ordinateur(String nom, String marque, Float prix, String description, int nbStock, Categorie categorie) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.nbStock = nbStock;
        this.categorie = categorie;
    }
    /** Constructeur par défaut */
    public Ordinateur() {
    }

    /** Getters and Setters */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbStock() {
        return nbStock;
    }

    public void setNbStock(int nbStock) {
        this.nbStock = nbStock;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Float prixTotalPourQuantité(int quantite) {
        return prix * quantite;
    }

    /**
     * Retourne une représentation textuelle détaillée de l'ordinateur.
     * @return String contenant les spécifications de l'ordinateur
     */
    @Override
    public String toString() {
        return "Détails de l'Ordinateur:\n" +
                "  Nom: " + nom + "\n" +
                "  Marque: " + marque + "\n" +
                "  Prix: " + (prix != null ? prix + " MAD" : "Non spécifié") + "\n" +
                "  Description: " + (description != null ? description : "Non spécifiée") + "\n" +
                "  Nombre en stock: " + nbStock + "\n" +
                "  Catégorie: " + (categorie != null ? categorie.getNom() : "Aucune catégorie associée") + "\n";
    }

}
