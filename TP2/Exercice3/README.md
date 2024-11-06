# Exercise 3: Order Management System

## Description

In this exercise, I implemented a Java application to manage the orders of clients in a company that sells computers. The objective was to create classes representing a computer, a category, an order, an order line, and a client, and to demonstrate their interactions through a main method.

## Class diagram

the image below shows the class diagram of the implemented classes.

![Class Diagram](../Main.jpg)

## 1. Ordinateur Class

The `Ordinateur` class represents a computer with attributes such as name, brand, price, description, and stock quantity. Each computer belongs to a category.

### Code

```java
package Exercice3;

public class Ordinateur {
    private String nom;
    private String marque;
    private Float prix;
    private String description;
    private int nbStock;
    private Categorie categorie;

    public Ordinateur(String nom, String marque, Float prix, String description, int nbStock, Categorie categorie) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.description = description;
        this.nbStock = nbStock;
        this.categorie = categorie;
    }

    public Ordinateur() {}

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

    public int getNbStock() {
        return nbStock;
    }

    public void setNbStock(int nbStock) {
        this.nbStock = nbStock;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Float prixTotalPourQuantité(int quantite) {
        return prix * quantite;
    }

    @Override
    public String toString() {
        return "Détails de l'Ordinateur:\n" +
                "  Nom: " + nom + "\n" +
                "  Marque: " + marque + "\n" +
                "  Prix: " + (prix != null ? prix + " MAD" : "Non spécifié") + "\n" +
                "  Description: " + (description != null ? description : "Non spécifiée") + "\n" +
                "  Nombre en stock: " + nbStock + "\n" +
                "  Catégorie: " + (categorie != null ? categorie.getNom() : "Aucune catégorie associée") + "\n";
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for name, brand, price, description, stock quantity, and category.
* **Constructor**: Initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `prixTotalPourQuantité` method returns the total price for a given quantity. The `toString` method displays the computer information.

## 2. Categorie Class

The `Categorie` class represents a category with attributes such as name, description, and a list of computers.

### Code

```java
package Exercice3;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String nom;
    private String description;
    private List<Ordinateur> ordinateurs = new ArrayList<>();

    public Categorie(String nom, String description, List<Ordinateur> ordinateurs) {
        this.nom = nom;
        this.description = description;
        this.ordinateurs = ordinateurs;
        for (Ordinateur ordinateur : ordinateurs) {
            ordinateur.setCategorie(this);
        }
    }

    public Categorie() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ordinateur> getOrdinateurs() {
        return ordinateurs;
    }

    public void setOrdinateurs(List<Ordinateur> ordinateurs) {
        this.ordinateurs = ordinateurs;
    }

    public void ajouterOrdinateur(Ordinateur ordinateur) {
        boolean existe = ordinateurs.stream()
                .anyMatch(o -> o.getNom().equals(ordinateur.getNom())
                        && o.getMarque().equals(ordinateur.getMarque()));

        if (!existe) {
            ordinateur.setCategorie(this);
            ordinateurs.add(ordinateur);
        }
    }

    public void supprimerOrdinateur(Ordinateur ordinateur) {
        ordinateurs.removeIf(o -> o.getNom().equals(ordinateur.getNom()));
    }

    public List<Ordinateur> rechercherParPrix(Float prix) {
        return ordinateurs
                .stream()
                .filter(o -> o.getPrix().equals(prix))
                .toList();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Détails de la Catégorie:\n");
        sb.append("  Nom: ").append(nom).append("\n");
        sb.append("  Description: ").append(description).append("\n");
        sb.append("  Ordinateurs:\n");
        if (ordinateurs.isEmpty()) {
            sb.append("    Aucun ordinateur dans cette catégorie.\n");
        } else {
            for (Ordinateur ordinateur : ordinateurs) {
                sb.append("- ").append(ordinateur.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for name, description, and a list of computers.
* **Constructor**: Initializes these attributes.
* **Methods**: The `ajouterOrdinateur` method adds a new computer to the list if it does not already exist. The `supprimerOrdinateur` method removes a computer from the list. The `rechercherParPrix` method returns a list of computers with a specific price. The `toString` method displays the category information.

## 3. Commande Class

The `Commande` class represents an order with attributes such as reference, client, order date, and order status.

### Code

```java
package Exercice3;

import java.util.Date;

public class Commande {
    private String reference;
    private Client client;
    private Date dateCommande;
    private String etatCommande;

    public Commande(String reference, Client client, Date dateCommande, String etatCommande) {
        this.reference = reference;
        this.client = client;
        this.dateCommande = dateCommande;
        this.etatCommande = etatCommande;
    }

    public Commande() {}

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    @Override
    public String toString() {
        return "Détails de la Commande:\n" +
                "  Référence: " + reference + "\n" +
                "  Client: " + (client != null ? client.getNom() + " " + client.getPrenom() : "Aucun client associé") + "\n" +
                "  Date de commande: " + (dateCommande != null ? dateCommande.toString() : "Non spécifiée") + "\n" +
                "  État de la commande: " + (etatCommande != null ? etatCommande : "Non spécifié") + "\n";
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for reference, client, order date, and order status.
* **Constructor**: Initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `toString` method displays the order information.

## 4. LigneCommande Class

The `LigneCommande` class represents an order line with attributes such as quantity, order, and ordered computer.

### Code

```java
package Exercice3;

public class LigneCommande {
    private int quantite;
    private Commande commande;
    private Ordinateur ordinateur;

    public LigneCommande(int quantite, Commande commande, Ordinateur ordinateur) {
        this.quantite = quantite;
        this.commande = commande;
        this.ordinateur = ordinateur;
    }

    public LigneCommande() {}

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Ordinateur getOrdinateur() {
        return ordinateur;
    }

    public void setOrdinateur(Ordinateur ordinateur) {
        this.ordinateur = ordinateur;
    }

    @Override
    public String toString() {
        return "Détails de la Ligne de Commande:\n" +
                "  Quantité: " + quantite + "\n" +
                "  Commande: " +
                (commande != null ? commande.toString() : "Aucune commande associée") +
                "\n" +
                "  Ordinateur: " +
                (ordinateur != null ? ordinateur.toString() : "Aucun ordinateur associé") +
                "\n";
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for quantity, order, and ordered computer.
* **Constructor**: Initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `toString` method displays the order line information.

## 5. Client Class

The `Client` class represents a client with attributes such as name, surname, address, email, city, phone number, and a list of orders.

### Code

```java
package Exercice3;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String email;
    private String telephone;
    private List<Commande> commandes = new ArrayList<>();

    public Client(String nom, String prenom, String adresse, String ville, String email, String telephone, List<Commande> commandes) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.email = email;
        this.telephone = telephone;
        this.commandes = commandes;
    }

    public Client() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public void ajouterCommande(Commande commande) {
        boolean existe = commandes.stream()
                .anyMatch(c -> c.getReference().equals(commande.getReference()));
        if (!existe) {
            commandes.add(commande);
        }
    }

    public void supprimerCommande(Commande commande) {
        commandes.removeIf(c -> c.getReference().equals(commande.getReference()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Détails du Client:\n");
        sb.append("  Nom: ").append(nom).append("\n");
        sb.append("  Prénom: ").append(prenom).append("\n");
        sb.append("  Adresse: ").append(adresse).append("\n");
        sb.append("  Ville: ").append(ville).append("\n");
        sb.append("  Email: ").append(email).append("\n");
        sb.append("  Téléphone: ").append(telephone).append("\n");

        sb.append("  Commandes:\n");
        if (commandes.isEmpty()) {
            sb.append("    Aucun commande associée à ce client.\n");
        } else {
            for (Commande commande : commandes) {
                sb.append("- ").append(commande.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for name, surname, address, email, city, phone number, and a list of orders.
* **Constructor**: Initializes these attributes.
* **Methods**: The `ajouterCommande` method adds a new order to the list if it does not already exist. The `supprimerCommande` method removes an order from the list. The `toString` method displays the client information.

## 6. Main Class

The `Main` class contains the main method to test the different classes.

### Code

```java
package Exercice3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ordinateur ord1 = new Ordinateur();
        Ordinateur ord2 = new Ordinateur();
        Ordinateur ord3 = new Ordinateur();

        ord1.setNom("ThinkPad");
        ord1.setMarque("Lenovo");
        ord1.setDescription("For Developers");
        ord1.setPrix(4500f);
        ord1.setNbStock(15);

        ord2.setNom("Latitude");
        ord2.setMarque("Dell");
        ord2.setDescription("For Simple Work");
        ord2.setPrix(5500f);
        ord2.setNbStock(20);

        ord3.setNom("MacBook Pro");
        ord3.setMarque("Apple");
        ord3.setDescription("If You Don't Know What To Do With Money Buy Me");
        ord3.setPrix(45000f);
        ord3.setNbStock(10);

        List<Ordinateur> ords = new ArrayList<>();
        ords.add(ord1);
        ords.add(ord2);
        ords.add(ord3);

        Categorie categorie = new Categorie();
        categorie.setNom("Laptop");
        categorie.setDescription("Mobile Computers For Everyday Job");
        for (Ordinateur ord : ords) {
            categorie.ajouterOrdinateur(ord);
        }

        Client client = new Client();
        client.setNom("Motawakil");
        client.setPrenom("Hassan");
        client.setEmail("motawakil@hassan.com");
        client.setAdresse("IMM 12 APT 5 ALAZHAR MADINATI");
        client.setVille("Casablanca");
        client.setTelephone("0615423897");

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Commande commande = new Commande();
        commande.setReference("R1234");
        commande.setClient(client);
        commande.setDateCommande(date);
        commande.setEtatCommande("Liverer");

        client.ajouterCommande(commande);

        LigneCommande lc1 = new LigneCommande();
        LigneCommande lc2 = new LigneCommande();
        LigneCommande lc3 = new LigneCommande();

        lc1.setQuantite(2);
        lc1.setCommande(commande);
        lc1.setOrdinateur(ord1);

        lc2.setQuantite(1);
        lc2.setCommande(commande);
        lc2.setOrdinateur(ord2);

        lc3.setQuantite(4);
        lc3.setCommande(commande);
        lc3.setOrdinateur(ord3);

        System.out.println(commande);
    }
}
```

### Implementation Details

* **Object Creation**: Instances of `Ordinateur`, `Categorie`, `Client`, `Commande`, and `LigneCommande` are created to represent the different entities in the order management system. Each object is initialized with appropriate attributes to simulate a real-world scenario.
* **Method Calls**: The `toString` method is called on the `Commande` instance to display the detailed information of the order, including the client details, order date, and the list of ordered computers.

## Execution and Results

When the program is executed:

1. The `commande.toString()` call displays the order's information.

### Test Case
```
Détails de la Commande:
  Référence: R1234
  Client: Motawakil Hassan
  Date de commande: Wed Nov 06 16:20:41 WEST 2024
  État de la commande: Liverer
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of classes in Java to manage the orders of clients in a company that sells computers. By creating instances of `Ordinateur`, `Categorie`, `Client`, `Commande`, and `LigneCommande`, and displaying their information, the exercise illustrates object-oriented programming principles and class interactions.
