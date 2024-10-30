package Exercice3;

import java.util.Date;

/**
 * Représente une commande dans le système de gestion de commandes d'ordinateurs.
 * Cette classe gère les informations relatives à une commande spécifique.
 */
public class Commande {
    // Attributes
    private String reference;
    private Client client;
    private Date dateCommande;
    private String etatCommande;

    /**
     * Constructeur avec tous les paramètres.
     * @param reference Référence unique de la commande
     * @param client Client qui passe la commande
     * @param dateCommande Date de la commande
     * @param etatCommande État de la commande
     */
    public Commande(String reference, Client client, Date dateCommande, String etatCommande) {
        this.reference = reference;
        this.client = client;
        this.dateCommande = dateCommande;
        this.etatCommande = etatCommande;
    }
    /** Constructeur par défaut */
    public Commande() {}

    // Getters and Setters
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Date getDateCommande() {
        return dateCommande;
    }
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
    public String getEtatCommande() {
        return etatCommande;
    }
    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    /**
     * Retourne une représentation textuelle détaillée de la commande.
     * @return String contenant les informations de la commande
     */
    @Override
    public String toString() {
        return "Détails de la Commande:\n" +
                "  Référence: " + reference + "\n" +
                "  Client: " + (client != null ? client.getNom() + " " + client.getPrenom() : "Aucun client associé") + "\n" +
                "  Date de commande: " + (dateCommande != null ? dateCommande.toString() : "Non spécifiée") + "\n" +
                "  État de la commande: " + (etatCommande != null ? etatCommande : "Non spécifié") + "\n";
    }
}
