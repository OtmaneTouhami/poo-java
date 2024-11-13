package Collections.Exercice1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionProduitsApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit(1, "Produit 1", 100));
        produits.add(new Produit(2, "Produit 2", 200));
        produits.add(new Produit(3, "Produit 3", 300));
        produits.add(new Produit(4, "Produit 4", 400));

        produits.remove(1);

        produits.forEach(System.out::println);

        produits.set(2, new Produit(4, "Produit 4 updated", 154));

        System.out.print("Entrer le nom du produit a recherecher: ");
        String nomProduit = scanner.nextLine();
        produits.stream()
                .filter(produit -> produit.getNom().equals(nomProduit))
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Produit non trouvé"));
        scanner.close();
    }
}
