package Exercice2;

// Classe abstraite représentant un employé avec des informations de base et un salaire.
public abstract class Employe {
    // Attributs privés pour stocker les informations de l'employé.
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Float salaire;

    // Constructeur avec paramètres pour initialiser les informations de l'employé.
    public Employe(String nom, String prenom, String email, String telephone, Float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.salaire = salaire;
    }

    // Constructeur sans paramètres pour créer un employé sans informations initiales.
    public Employe() {
    }

    // Getters et Setters pour accéder et modifier les attributs privés.
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

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    // Méthode abstraite pour calculer le salaire, implémentée dans les sous-classes.
    public abstract Float calculerSalire();

    // Redéfinition de la méthode toString pour afficher les informations de l'employé.
    public String toString() {
        return "Nom: " + nom +
                "\nPrenom: " + prenom +
                "\nEmail: " + email +
                "\nTelephone: " + telephone +
                "\nSalaire: " + this.calculerSalire();
    }
}
