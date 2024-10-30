package Exercice2;

// Classe principale pour tester les fonctionnalités de gestion des employés.
public class Main {
    public static void main(String[] args) {
        // Création d'un ingénieur avec les informations personnelles, le salaire et la spécialité.
        Ingenieur ingenieur = new Ingenieur();
        ingenieur.setNom("Hassan");
        ingenieur.setPrenom("Khalid");
        ingenieur.setEmail("hassan.khalid@gmail.com");
        ingenieur.setTelephone("0654387653");
        ingenieur.setSalaire(11000f);
        ingenieur.setSpecialite("Ingenieur Logicielle");

        // Création d'un manager avec les informations personnelles, le salaire et le service.
        Manager manager = new Manager();
        manager.setNom("Houssam");
        manager.setPrenom("Marwan");
        manager.setEmail("marwan@gmail.com");
        manager.setTelephone("0754321239");
        manager.setSalaire(15000f);
        manager.setService("Infrastructure");

        // Affichage des informations de l'ingénieur.
        System.out.println(ingenieur);
        System.out.println("\n=========================================\n");
        // Affichage des informations du manager.
        System.out.println(manager);
    }
}
