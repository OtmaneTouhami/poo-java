# Exercise 1: Library Management System

## Description

In this exercise, I implemented a Java application for managing books and members of a library. The objective was to create classes representing a person, a member, an author, and a book, and to demonstrate their interactions through a main method.

## 1. Personne Class

The `Personne` class represents a person with basic attributes such as name, surname, email, phone number, and age.

### Code

```java
package Exercice1;

public class Personne {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int age;

    public Personne(String nom, String prenom, String email, String telephone, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }

    public Personne() {}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void afficher() {
        System.out.println("Nom: " + nom);
        System.out.println("Prenom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("Telephone: " + telephone);
        System.out.println("Age: " + age);
    }
}
```

### Implementation Details

* **Attributes**: The class has private attributes for storing personal information.
* **Constructor**: The constructor initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `afficher` method displays the personal information.

## 2. Adherent Class

The `Adherent` class inherits from `Personne` and adds an attribute for the member number.

### Code

```java
package Exercice1;

public class Adherent extends Personne {
    private int numAdherent;

    public Adherent(String nom, String prenom, String email, String telephone, int age, int numAdherent) {
        super(nom, prenom, email, telephone, age);
        this.numAdherent = numAdherent;
    }

    public int getNumAdherent() {
        return numAdherent;
    }

    public void setNumAdherent(int numAdherent) {
        this.numAdherent = numAdherent;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Num Adherent: " + numAdherent);
    }
}
```

### Implementation Details

* **Inheritance**: `Adherent` inherits from `Personne`.
* **Attributes**: Adds a member number attribute.
* **Methods**: Overrides the `afficher` method to include the member number.

## 3. Auteur Class

The `Auteur` class inherits from `Personne` and adds an attribute for the author number.

### Code

```java
package Exercice1;

public class Auteur extends Personne {
    private int numAuteur;

    public Auteur(String nom, String prenom, String email, String telephone, int age, int numAuteur) {
        super(nom, prenom, email, telephone, age);
        this.numAuteur = numAuteur;
    }

    public int getNumAuteur() {
        return numAuteur;
    }

    public void setNumAuteur(int numAuteur) {
        this.numAuteur = numAuteur;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Num Auteur: " + numAuteur);
    }
}
```

### Implementation Details

* **Inheritance**: `Auteur` inherits from `Personne`.
* **Attributes**: Adds an author number attribute.
* **Methods**: Overrides the `afficher` method to include the author number.

## 4. Livre Class

The `Livre` class represents a book with attributes for title, ISBN, and author.

### Code

```java
package Exercice1;

public class Livre {
    private String titre;
    private int ispn;
    Auteur auteur;

    public Livre(String titre, int ispn, Auteur auteur) {
        this.titre = titre;
        this.ispn = ispn;
        this.auteur = auteur;
    }

    public Livre() {}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getIspn() {
        return ispn;
    }

    public void setIspn(int ispn) {
        this.ispn = ispn;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void afficher() {
        System.out.println("Titre: " + titre);
        System.out.println("ISPN: " + ispn);
        System.out.println("Auteur:");
        auteur.afficher();
    }
}
```

### Implementation Details

* **Attributes**: The class has attributes for title, ISBN, and author.
* **Constructor**: Initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `afficher` method displays the book information, including the author details.

## 5. Main Class

The `Main` class contains the main method to test the different classes.

### Code

```java
package Exercice1;

public class Main {
    public static void main(String[] args) {
        Adherent adherent = new Adherent(
                "Salama",
                "Mouad",
                "salama.mouad@gmail.com",
                "0615306366",
                30,
                1
        );

        Auteur auteur = new Auteur(
                "Martin",
                "Goerge R.R",
                "goerge.rr@gmail.com",
                "0651435278",
                78,
                1
        );

        Livre livre = new Livre(
                "A Song Of Ice And Fire",
                2735,
                auteur
        );

        adherent.afficher();
        System.out.println("\n=========================================\n");
        livre.afficher();
    }
}
```

### Implementation Details

* **Object Creation**: Creates instances of `Adherent`, `Auteur`, and `Livre`.
* **Method Calls**: Calls the `afficher` method to display the information of the member and the book.

## Execution and Results

When the program is executed:

1. The `adherent.afficher()` call displays the member's information.
2. The `livre.afficher()` call displays the book's information, including the author's details.

### Test Case
```
Nom: Salama
Prenom: Mouad
Email: salama.mouad@gmail.com
Telephone: 0615306366
Age: 30
Num Adherent: 1

=========================================

Titre: A Song Of Ice And Fire
ISPN: 2735
Auteur:
Nom: Martin
Prenom: Goerge R.R
Email: goerge.rr@gmail.com
Telephone: 0651435278
Age: 78
Num Auteur: 1
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of classes in Java to manage library members and books. By creating instances of `Adherent`, `Auteur`, and `Livre`, and displaying their information, the exercise illustrates object-oriented programming principles and class interactions.