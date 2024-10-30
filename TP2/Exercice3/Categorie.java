package Exercice3;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une catégorie d'ordinateurs dans le système de gestion de commandes.
 * Cette classe permet de regrouper et gérer une collection d'ordinateurs appartenant
 * à une même catégorie.
 */
public class Categorie {
    // Attributes
    private String nom;
    private String description;
    private List<Ordinateur> ordinateurs = new ArrayList<>();

    /**
     * Constructeur avec paramètres.
     * @param nom Le nom de la catégorie
     * @param description La description de la catégorie
     * @param ordinateurs La liste des ordinateurs à ajouter à la catégorie
     */
    public Categorie(String nom, String description, List<Ordinateur> ordinateurs) {
        this.nom = nom;
        this.description = description;
        this.ordinateurs = ordinateurs;
        for (Ordinateur ordinateur : ordinateurs) {
            ordinateur.setCategorie(this);
        }
    }
    /** Constructeur par défaut */
    public Categorie() {
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ordinateur> getOrdinateurs() {
        return ordinateurs;
    }

    public void setOrdinateurs(List<Ordinateur> ordinateurs) {
        this.ordinateurs = ordinateurs;
    }

    /**
     * Ajoute un nouvel ordinateur à la catégorie si celui-ci n'existe pas déjà.
     * La vérification se fait sur le nom et la marque de l'ordinateur.
     * @param ordinateur L'ordinateur à ajouter
     */
    public void ajouterOrdinateur(Ordinateur ordinateur) {
        boolean existe = ordinateurs.stream()
                .anyMatch(o -> o.getNom().equals(ordinateur.getNom())
                        && o.getMarque().equals(ordinateur.getMarque()));

        if (!existe) {
            ordinateur.setCategorie(this);
            ordinateurs.add(ordinateur);
        }
    }

    /**
     * Supprime un ordinateur de la catégorie.
     * @param ordinateur L'ordinateur à supprimer
     */
    public void supprimerOrdinateur(Ordinateur ordinateur) {
        ordinateurs.removeIf(o -> o.getNom().equals(ordinateur.getNom()));
    }

    /**
     * Recherche et retourne tous les ordinateurs ayant un prix spécifique.
     * @param prix Le prix à rechercher
     * @return Liste des ordinateurs correspondant au prix donné
     */
    public List<Ordinateur> rechercherParPrix(Float prix) {
        return ordinateurs
                .stream()
                .filter(o -> o.getPrix().equals(prix))
                .toList();
    }

    /**
     * Retourne une représentation textuelle détaillée de la catégorie.
     * @return String contenant les détails de la catégorie
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Détails de la Catégorie:\n");
        sb.append("  Nom: ").append(nom).append("\n");
        sb.append("  Description: ").append(description).append("\n");
        sb.append("  Ordinateurs:\n");
        if (ordinateurs.isEmpty()) {
            sb.append("    Aucun ordinateur dans cette catégorie.\n");
        } else {
            for (Ordinateur ordinateur : ordinateurs) {
                sb.append("- ").append(ordinateur.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
