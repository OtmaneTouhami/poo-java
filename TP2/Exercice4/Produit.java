package Exercice4;

/**
 * Classe représentant un produit dans le système.
 * Cette classe contient toutes les informations relatives à un produit.
 */
public class Produit {
    // Attributes
    private long id;
    private String nom;
    private String marque;
    private Float prix;
    private String description;
    private int nombreEnStock;

    /**
     * Constructeur complet initialisant tous les attributs du produit.
     * @param id Identifiant du produit
     * @param nom Nom du produit
     * @param marque Marque du produit
     * @param prix Prix du produit
     * @param description Description du produit
     * @param nombreEnStock Quantité en stock
     */
    public Produit(long id, String nom, String marque, Float prix, String description, int nombreEnStock) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.nombreEnStock = nombreEnStock;
    }
    /**
     * Constructeur par défaut.
     */
    public Produit() {}

    /**
     * Getters and Setters.
     */
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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
    public int getNombreEnStock() {
        return nombreEnStock;
    }
    public void setNombreEnStock(int nombreEnStock) {
        this.nombreEnStock = nombreEnStock;
    }

    /**
     * Retourne une représentation textuelle du produit.
     * @return Une chaîne formatée contenant toutes les informations du produit
     */
    @Override
    public String toString() {
        return "Détails du Produit:\n" +
                "  ID: " + id + "\n" +
                "  Nom: " + (nom != null ? nom : "Non spécifié") + "\n" +
                "  Marque: " + (marque != null ? marque : "Non spécifiée") + "\n" +
                "  Prix: " + (prix != null ? prix + " MAD" : "Non spécifié") + "\n" +
                "  Description: " + (description != null ? description : "Non spécifiée") + "\n" +
                "  Nombre en stock: " + nombreEnStock + "\n";
    }


}
