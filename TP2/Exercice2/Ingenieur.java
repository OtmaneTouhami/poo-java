package Exercice2;

// Classe représentant un ingénieur, qui hérite de la classe Employe.
public class Ingenieur extends Employe {
    // Attribut pour stocker la spécialité de l'ingénieur.
    private String specialite;

    // Constructeur avec paramètres pour initialiser les informations de l'ingénieur.
    public Ingenieur(String nom, String prenom, String email, String telephone, Float salaire, String specialite) {
        super(nom, prenom, email, telephone, salaire);
        this.specialite = specialite;
    }

    // Constructeur sans paramètres pour créer un ingénieur sans informations initiales.
    public Ingenieur() {
    }

    // Getter et Setter pour accéder et modifier la spécialité de l'ingénieur.
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    // Redéfinition de la méthode calculerSalire pour calculer le salaire de l'ingénieur avec une augmentation de 15%.
    @Override
    public Float calculerSalire() {
        return getSalaire() + getSalaire() * 0.15f;
    }

    // Redéfinition de la méthode toString pour afficher les informations de l'ingénieur.
    public String toString() {
        return "Ingenieur:\n" + super.toString() +
                "\nSpecialite: " + specialite;
    }
}
