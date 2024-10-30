package Exercice4;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principale de l'application de gestion des produits.
 * Fournit une interface utilisateur en ligne de commande permettant d'interagir
 * avec le système de gestion des produits.
 */
public class Application {
    /**
     * Affiche le menu principal de l'application.
     * Présente à l'utilisateur les différentes options disponibles.
     */
    public static void menu() {
        System.out.println("\n**** Menu ***");
        System.out.println(
                """
                        1. Afficher la liste des produits.
                        2. Rechercher des produits par mot clé.
                        3. Ajouter un nouveau produit dans la liste.
                        4. Récupérer et afficher un produit par ID.
                        5. Supprimer un produit par id.
                        6. Quitter ce programme.
                        """
        );
        System.out.print("Entrer votre choix: ");
    }

    public static void main(String[] args) {
        // Initialisation des objets principaux
        IMetierProduit metierProduit = new MetierProduitImpl();
        Scanner sc = new Scanner(System.in);

        /**
         * Boucle principale du programme.
         * Continue jusqu'à ce que l'utilisateur choisisse de quitter (option 6).
         *
         * Traitement des différentes options :
         * Case "1" : Affiche tous les produits avec gestion du cas où la liste est vide
         * Case "2" : Recherche des produits par mot clé avec affichage des résultats
         * Case "3" : Ajout d'un nouveau produit avec saisie sécurisée des données
         * Case "4" : Recherche d'un produit par ID avec gestion des cas d'erreur
         * Case "5" : Suppression d'un produit par ID avec gestion des cas d'erreur
         * Case "6" : Sortie propre du programme
         * Default : Gestion des choix invalides
         */
        do {
            menu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    List<Produit> produits = metierProduit.getAll();
                    if (!produits.isEmpty()) {
                        String terminaison = produits.size() > 1 ? "s" : "";
                        System.out.println("Il y a " + produits.size() + " produit" + terminaison);
                        for (Produit p : produits) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("Il n'y a aucun produit enregistré !");
                    }
                    break;
                case "2":
                    System.out.print("Entrer un mot clé: ");
                    String motCle = sc.nextLine();
                    List<Produit> searchResult = metierProduit.findByNom(motCle);
                    if (searchResult.isEmpty()) {
                        System.out.println("Aucun produit avec ce mot clé n'existe !");
                    } else {
                        for (Produit p : searchResult) {
                            System.out.println(p);
                        }
                    }
                    break;
                case "3":
                    System.out.println("Entrer les informations du produit: ");
                    try {
                        System.out.print("Entrer l'id: ");
                        long id = sc.nextLong();
                        sc.nextLine();
                        System.out.print("Entrer le nom: ");
                        String nom = sc.nextLine();
                        System.out.print("Entrer la marque: ");
                        String marque = sc.nextLine();
                        System.out.print("Entrer une description: ");
                        String description = sc.nextLine();
                        System.out.print("Entrer le prix: ");
                        float prix = sc.nextFloat();
                        System.out.print("Entrer la quantité du produit en stock: ");
                        int quantite = sc.nextInt();
                        sc.nextLine();
                        Produit produit = new Produit(id, nom, marque, prix, description, quantite);
                        metierProduit.add(produit);
                    } catch (Exception e) {
                        System.out.println("Erreur de saisie, veuillez réessayer.");
                        sc.nextLine();
                    }
                    break;
                case "4":
                    System.out.print("Entrer l'id du produit à rechercher: ");
                    try {
                        long idToSearch = sc.nextLong();
                        sc.nextLine();
                        Produit idSearchResult = metierProduit.findById(idToSearch);
                        if (idSearchResult != null) {
                            System.out.println(idSearchResult);
                        } else {
                            System.out.println("Il n'y a aucun produit avec cet id !");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur de saisie, veuillez réessayer.");
                        sc.nextLine();
                    }
                    break;
                case "5":
                    System.out.print("Entrer l'id du produit à supprimer: ");
                    try {
                        long idToDelete = sc.nextLong();
                        sc.nextLine();
                        metierProduit.delete(idToDelete);
                    } catch (Exception e) {
                        System.out.println("Erreur de saisie, veuillez réessayer.");
                        sc.nextLine();
                    }
                    break;
                case "6":
                    System.out.println("Au revoir !");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (true);
    }
}
