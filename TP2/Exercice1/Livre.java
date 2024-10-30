package Exercice1;

// Classe représentant un livre dans la bibliothèque.
public class Livre {
    // Attributs pour le titre, l'ISPN (numéro d'identification) et l'auteur du livre.
    private String titre;
    private int ispn;
    Auteur auteur;

    // Constructeur pour initialiser le titre, l'ISPN et l'auteur d'un livre.
    public Livre(String titre, int ispn, Auteur auteur) {
        this.titre = titre;
        this.ispn = ispn;
        this.auteur = auteur;
    }

    // Constructeur sans paramètres pour créer un livre sans informations initiales.
    public Livre() {}

    // Getters et Setters pour accéder et modifier les attributs du livre.
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIspn() {
        return ispn;
    }

    public void setIspn(int ispn) {
        this.ispn = ispn;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    // Méthode pour afficher les informations du livre, incluant les informations de l'auteur.
    public void afficher() {
        System.out.println("Titre: " + titre);
        System.out.println("ISPN: " + ispn);
        System.out.println("Auteur:");
        auteur.afficher();
    }
}
