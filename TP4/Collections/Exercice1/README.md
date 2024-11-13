# Lab Report - Exercise 1: GestionProduitsApp

## Description

In this exercise, I implemented a product management system using Java Collections. The objective was to demonstrate basic operations on a list of products, including adding, removing, updating, and searching for products.

## 1. Produit Class

The `Produit` class represents a product with attributes for id, name, and price.

### Code

```java
package Collections.Exercice1;

public class Produit {
    private long id;
    private String nom;
    private double prix;

    public Produit(long id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
```

### Implementation Details

* **Attributes**: The class has three private attributes: `id` (long), `nom` (String), and `prix` (double).
* **Constructors**:
    * The parameterized constructor initializes the attributes with the provided values.
    * The default constructor initializes the attributes with default values.
* **Getters and Setters**: Methods to access and modify the attributes.
* **toString Method**: Returns a string representation of the product, including its id, name, and price.

## 2. GestionProduitsApp Class

The `GestionProduitsApp` class contains the main method and demonstrates various operations on a list of products.

### Code

```java
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
```

### Implementation Details

* **Scanner Initialization**:
```java
Scanner scanner = new Scanner(System.in);
```
* Initializes a `Scanner` object to read user input.

**List Initialization and Adding Products**:
```java
List<Produit> produits = new ArrayList<>();
produits.add(new Produit(1, "Produit 1", 100));
produits.add(new Produit(2, "Produit 2", 200));
produits.add(new Produit(3, "Produit 3", 300));
produits.add(new Produit(4, "Produit 4", 400));
```
* Initializes a list of `Produit` objects and adds four products to the list.

**Removing a Product**:
```java
produits.remove(1);
```
* Removes the product at index 1 from the list.

**Displaying Products**:
```java
produits.forEach(System.out::println);
```
* Iterates over the list and prints each product using the `toString` method.

**Updating a Product**:
```java
produits.set(2, new Produit(4, "Produit 4 updated", 154));
```
* Updates the product at index 2 with a new product.

**Searching for a Product by Name**:
```java
System.out.print("Entrer le nom du produit a recherecher: ");
String nomProduit = scanner.nextLine();
produits.stream()
        .filter(produit -> produit.getNom().equals(nomProduit))
        .findFirst()
        .ifPresentOrElse(System.out::println, () -> System.out.println("Produit non trouvé"));
scanner.close();
```
* Prompts the user to enter a product name.
* Uses a stream to filter the list for a product with the given name.
* If found, prints the product; otherwise, prints "Produit non trouvé".
* Closes the scanner.

## 3. Execution and Results

When the program is executed:

1. The list of products is initialized and four products are added.
2. The product at index 1 is removed.
3. The remaining products are displayed.
4. The product at index 2 is updated.
5. The user is prompted to enter a product name to search for.
6. If the product is found, it is displayed; otherwise, a message indicating the product was not found is shown.

### Test Case
```
Produit{id=1, nom='Produit 1', prix=100.0}
Produit{id=3, nom='Produit 3', prix=300.0}
Produit{id=4, nom='Produit 4', prix=400.0}
Entrer le nom du produit a recherecher: Produit 1
Produit{id=1, nom='Produit 1', prix=100.0}
```

## Conclusion

Through this exercise, I demonstrated basic operations on a list of products using Java Collections. The `GestionProduitsApp` class showcases adding, removing, updating, and searching for products, illustrating fundamental collection manipulation techniques.