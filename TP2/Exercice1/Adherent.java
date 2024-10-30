package Exercice1;

// Classe représentant un adhérent de la bibliothèque, qui hérite de Personne.
public class Adherent extends Personne {
    // Attribut pour stocker le numéro unique d'adhérent.
    private int numAdherent;

    // Constructeur qui initialise les attributs de Personne et ajoute le numéro d'adhérent.
    public Adherent(String nom, String prenom, String email, String telephone, int age, int numAdherent) {
        super(nom, prenom, email, telephone, age);
        this.numAdherent = numAdherent;
    }

    // Getter et Setter pour accéder et modifier le numéro d'adhérent.
    public int getNumAdherent() {
        return numAdherent;
    }

    public void setNumAdherent(int numAdherent) {
        this.numAdherent = numAdherent;
    }

    // Redéfinition de la méthode afficher pour inclure les informations de l'adhérent.
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Num Adherent: " + numAdherent);
    }
}
