package Exercice1;

// Classe de base représentant une personne avec des attributs de base comme nom, prénom, email, téléphone et âge.
public class Personne {
    // Attributs privés pour stocker les informations personnelles de chaque personne.
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int age;

    // Constructeur avec paramètres pour initialiser les attributs d'une personne.
    public Personne(String nom, String prenom, String email, String telephone, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }

    // Constructeur sans paramètres pour créer une personne sans informations initiales.
    public Personne() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Méthode pour afficher les informations personnelles de la personne.
    public void afficher() {
        System.out.println("Nom: " + nom);
        System.out.println("Prenom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("Telephone: " + telephone);
        System.out.println("Age: " + age);
    }
}
