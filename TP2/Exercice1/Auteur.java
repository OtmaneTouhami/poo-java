package Exercice1;

// Classe représentant un auteur, qui hérite également de la classe Personne.
public class Auteur extends Personne {
    // Attribut pour stocker le numéro unique d'auteur.
    private int numAuteur;

    // Constructeur qui initialise les attributs de Personne et ajoute le numéro d'auteur.
    public Auteur(String nom, String prenom, String email, String telephone, int age, int numAuteur) {
        super(nom, prenom, email, telephone, age);
        this.numAuteur = numAuteur;
    }

    // Getter et Setter pour accéder et modifier le numéro d'auteur.
    public int getNumAuteur() {
        return numAuteur;
    }

    public void setNumAuteur(int numAuteur) {
        this.numAuteur = numAuteur;
    }

    // Redéfinition de la méthode afficher pour inclure les informations de l'auteur.
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Num Auteur: " + numAuteur);
    }
}
