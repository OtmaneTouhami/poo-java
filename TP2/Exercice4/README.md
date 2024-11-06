# Exercise 4: Product Management System

## Description

In this exercise, I implemented a Java application to manage a collection of products using lists and interfaces. The objective was to create a `Produit` class, an `IMetierProduit` interface, and a `MetierProduitImpl` class that implements the interface. The application provides a command-line interface for users to interact with the product management system.

## 1. Produit Class

The `Produit` class represents a product with attributes such as id, name, brand, price, description, and stock quantity.

### Code

```java
package Exercice4;

public class Produit {
    private long id;
    private String nom;
    private String marque;
    private Float prix;
    private String description;
    private int nombreEnStock;

    public Produit(long id, String nom, String marque, Float prix, String description, int nombreEnStock) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.nombreEnStock = nombreEnStock;
    }

    public Produit() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreEnStock() {
        return nombreEnStock;
    }

    public void setNombreEnStock(int nombreEnStock) {
        this.nombreEnStock = nombreEnStock;
    }

    @Override
    public String toString() {
        return "Détails du Produit:\n" +
                "  ID: " + id + "\n" +
                "  Nom: " + (nom != null ? nom : "Non spécifié") + "\n" +
                "  Marque: " + (marque != null ? marque : "Non spécifiée") + "\n" +
                "  Prix: " + (prix != null ? prix + " MAD" : "Non spécifié") + "\n" +
                "  Description: " + (description != null ? description : "Non spécifiée") + "\n" +
                "  Nombre en stock: " + nombreEnStock + "\n";
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for id, name, brand, price, description, and stock quantity.
* **Constructor**: Initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `toString` method displays the product information.

## 2. IMetierProduit Interface

The `IMetierProduit` interface declares methods for managing product objects.

### Code

```java
package Exercice4;

import java.util.List;

public interface IMetierProduit {
    Produit add(Produit p);
    List<Produit> getAll();
    List<Produit> findByNom(String motCle);
    Produit findById(long id);
    void delete(long id);
}
```

### Implementation Details

* **Methods**:
    * `add(Produit p)`: Adds a product to the list.
    * `getAll()`: Returns all products as a list.
    * `findByNom(String motCle)`: Finds products by name containing the keyword.
    * `findById(long id)`: Finds a product by its id.
    * `delete(long id)`: Deletes a product by its id.

## 3. MetierProduitImpl Class

The `MetierProduitImpl` class implements the `IMetierProduit` interface and manages a list of products.

### Code

```java
package Exercice4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MetierProduitImpl implements IMetierProduit {
    List<Produit> produits = new ArrayList<>();

    @Override
    public Produit add(Produit p) {
        int index = IntStream.range(0, produits.size())
                .filter(i -> produits.get(i).getId() == p.getId())
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            produits.set(index, p);
            System.out.println(
                    "Le produit avec l'id " +
                            p.getId() +
                            " existe déjà ! Il a été modifié avec les nouvelles informations."
            );
        } else {
            produits.add(p);
            System.out.println("Produit ajouté avec succès !");
        }
        return p;
    }

    @Override
    public List<Produit> getAll() {
        return new ArrayList<>(produits);
    }

    @Override
    public List<Produit> findByNom(String motCle) {
        return produits.stream()
                .filter(produit -> produit.getNom().toLowerCase().contains(motCle.toLowerCase()))
                .toList();
    }

    @Override
    public Produit findById(long id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(long id) {
        boolean isDeleted = produits.removeIf(p -> p.getId() == id);
        if (isDeleted) {
            System.out.println("Le produit a été supprimé !");
        } else {
            System.out.println("Le produit avec l'id " + id + " n'existe pas !");
        }
    }
}
```

### Implementation Details

* **Attributes**:
    * `produits`: This attribute is a list of `Produit` objects. It is used to store all the products managed by the application. The list is implemented using `ArrayList` to allow dynamic management of the collection.

* **Methods**:
    * `add(Produit p)`: This method adds a new product to the list or updates an existing product if a product with the same ID already exists. It first checks if the product ID exists in the list. If it does, the existing product is updated with the new information. If it does not, the new product is added to the list.
    * `getAll()`: This method returns a copy of the list of all products. It creates a new `ArrayList` containing all the products to avoid direct modification of the internal list.
    * `findByNom(String motCle)`: This method searches for products whose names contain the specified keyword. The search is case-insensitive. It returns a list of products that match the search criteria.
    * `findById(long id)`: This method searches for a product by its ID. It returns the product if found, or `null` if no product with the specified ID exists.
    * `delete(long id)`: This method deletes a product from the list based on its ID. It returns a confirmation message if the product is successfully deleted or an error message if no product with the specified ID is found.

## 4. Application Class

The `Application` class contains the main method to interact with the user and manage products.

### Code

```java
package Exercice4;

import java.util.List;
import java.util.Scanner;

public class Application {
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
        IMetierProduit metierProduit = new MetierProduitImpl();
        Scanner sc = new Scanner(System.in);

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
```

### Implementation Details

* **Menu**: Displays the menu options to the user. It prints a list of available operations that the user can perform, such as displaying all products, searching for products by keyword, adding a new product, retrieving a product by id, and deleting a product by id. The user is prompted to enter their choice.

* **Main Method**: Initializes the product management system and handles user input to perform various operations. It creates an instance of `MetierProduitImpl` to manage the products and uses a `Scanner` to read user input. The method contains a loop that continuously displays the menu and processes the user's choice. Depending on the user's input, it calls the appropriate method from `MetierProduitImpl` to perform the requested operation, such as displaying all products, searching for products by keyword, adding a new product, retrieving a product by id, or deleting a product by id. The loop continues until the user chooses to exit the program.
## Execution and Results

When the program is executed, the user is presented with a menu to manage products. The user can choose to display all products, search for products by keyword, add a new product, retrieve a product by id, or delete a product by id.

### Test Case

```
**** Menu ***
1. Afficher la liste des produits.
2. Rechercher des produits par mot clé.
3. Ajouter un nouveau produit dans la liste.
4. Récupérer et afficher un produit par ID.
5. Supprimer un produit par id.
6. Quitter ce programme.
Entrer votre choix: 1
Il n'y a aucun produit enregistré !
Entrer votre choix: 3
Entrer les informations du produit: 
Entrer l'id: 1
Entrer le nom: Laptop
Entrer la marque: Dell
Entrer une description: High performance laptop
Entrer le prix: 15000
Entrer la quantité du produit en stock: 10
Produit ajouté avec succès !
Entrer votre choix: 1
Il y a 1 produit
Détails du Produit:
  ID: 1
  Nom: Laptop
  Marque: Dell
  Prix: 15000.0 MAD
  Description: High performance laptop
  Nombre en stock: 10
Entrer votre choix: 6
Au revoir !
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of classes in Java to manage a collection of products. By creating instances of `Produit`, `IMetierProduit`, and `MetierProduitImpl`, and providing a command-line interface for user interaction, the exercise illustrates object-oriented programming principles and class interactions.