package Exercice3;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un client dans le système de gestion de commandes d'ordinateurs.
 * Cette classe gère les informations personnelles du client et ses commandes associées.
 */
public class Client {
    // Attributes
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String email;
    private String telephone;
    private List<Commande> commandes = new ArrayList<>();

    /**
     * Constructeur avec tous les paramètres.
     * @param nom Nom du client
     * @param prenom Prénom du client
     * @param adresse Adresse postale du client
     * @param ville Ville de résidence
     * @param email Adresse email
     * @param telephone Numéro de téléphone
     * @param commandes Liste des commandes du client
     */
    public Client(
            String nom,
            String prenom,
            String adresse,
            String ville,
            String email,
            String telephone,
            List<Commande> commandes
    ) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.email = email;
        this.telephone = telephone;
        this.commandes = commandes;
    }
    /** Constructeur par défaut */
    public Client() {}

    // Getters and Setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public List<Commande> getCommandes() {
        return commandes;
    }
    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    /**
     * Ajoute une nouvelle commande à la liste des commandes du client.
     * Vérifie si la commande n'existe pas déjà avant de l'ajouter.
     * @param commande La commande à ajouter
     */
    public void ajouterCommande(Commande commande) {
        boolean existe = commandes.stream()
                .anyMatch(c -> c.getReference().equals(commande.getReference()));
        if (!existe) {
            commandes.add(commande);
        }
    }

    /**
     * Supprime une commande de la liste des commandes du client.
     * @param commande La commande à supprimer
     */
    public void supprimerCommande(Commande commande) {
        commandes.removeIf(c -> c.getReference().equals(commande.getReference()));
    }

    /**
     * Retourne une représentation textuelle détaillée du client et de ses commandes.
     * @return String contenant les informations du client
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Détails du Client:\n");
        sb.append("  Nom: ").append(nom).append("\n");
        sb.append("  Prénom: ").append(prenom).append("\n");
        sb.append("  Adresse: ").append(adresse).append("\n");
        sb.append("  Ville: ").append(ville).append("\n");
        sb.append("  Email: ").append(email).append("\n");
        sb.append("  Téléphone: ").append(telephone).append("\n");

        sb.append("  Commandes:\n");
        if (commandes.isEmpty()) {
            sb.append("    Aucun commande associée à ce client.\n");
        } else {
            for (Commande commande : commandes) {
                sb.append("- ").append(commande.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
