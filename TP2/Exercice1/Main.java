package Exercice1;

// Classe principale pour tester les différentes fonctionnalités.
public class Main {
    public static void main(String[] args) {
        // Création d'un adhérent avec les informations personnelles et un numéro d'adhérent.
        Adherent adherent = new Adherent(
                "Salama",
                "Mouad",
                "salama.mouad@gmail.com",
                "0615306366",
                30,
                1
        );

        // Création d'un auteur avec les informations personnelles et un numéro d'auteur.
        Auteur auteur = new Auteur(
                "Martin",
                "Goerge R.R",
                "goerge.rr@gmail.com",
                "0651435278",
                78,
                1
        );

        // Création d'un livre avec un titre, un numéro ISPN et un auteur.
        Livre livre = new Livre(
                "A Song Of Ice And Fire",
                2735,
                auteur
        );

        // Affichage des informations de l'adhérent.
        adherent.afficher();
        System.out.println("\n=========================================\n");
        // Affichage des informations du livre (incluant celles de l'auteur).
        livre.afficher();
    }
}
