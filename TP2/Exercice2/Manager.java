package Exercice2;

// Classe représentant un manager, qui hérite également de la classe Employe.
public class Manager extends Employe {
    // Attribut pour stocker le service du manager.
    private String service;

    // Constructeur avec paramètres pour initialiser les informations du manager.
    public Manager(String nom, String prenom, String email, String telephone, Float salaire, String service) {
        super(nom, prenom, email, telephone, salaire);
        this.service = service;
    }

    // Constructeur sans paramètres pour créer un manager sans informations initiales.
    public Manager() {
    }

    // Getter et Setter pour accéder et modifier le service du manager.
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    // Redéfinition de la méthode calculerSalire pour calculer le salaire du manager avec une augmentation de 20%.
    @Override
    public Float calculerSalire() {
        return getSalaire() + getSalaire() * 0.2f;
    }

    // Redéfinition de la méthode toString pour afficher les informations du manager.
    public String toString() {
        return "Manager:\n" + super.toString() +
                "\nService: " + service;
    }
}
