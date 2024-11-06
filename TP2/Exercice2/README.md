# Exercise 2: Employee Salary Management System

## Description

In this exercise, I implemented a Java application to manage the salaries of engineers and managers in a software development company. The objective was to create classes representing an employee, an engineer, and a manager, and to demonstrate their interactions through a main method.

## 1. Employe Class

The `Employe` class is an abstract class representing an employee with basic attributes such as name, surname, email, phone number, and salary.

### Code

```java
package Exercice2;

public abstract class Employe {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Float salaire;

    public Employe(String nom, String prenom, String email, String telephone, Float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.salaire = salaire;
    }

    public Employe() {}

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

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public abstract Float calculerSalire();

    public String toString() {
        return "Nom: " + nom +
                "\nPrenom: " + prenom +
                "\nEmail: " + email +
                "\nTelephone: " + telephone +
                "\nSalaire: " + this.calculerSalire();
    }
}
```

### Implementation Details

* **Attributes**: The class has private attributes for storing personal information and salary.
* **Constructor**: The constructor initializes these attributes.
* **Methods**: Getters and setters are provided for each attribute. The `calculerSalire` method is abstract and must be implemented by subclasses. The `toString` method displays the employee information.

## 2. Ingenieur Class

The `Ingenieur` class inherits from `Employe` and adds an attribute for the engineer's specialty.

### Code

```java
package Exercice2;

public class Ingenieur extends Employe {
    private String specialite;

    public Ingenieur(String nom, String prenom, String email, String telephone, Float salaire, String specialite) {
        super(nom, prenom, email, telephone, salaire);
        this.specialite = specialite;
    }

    public Ingenieur() {}

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public Float calculerSalire() {
        return getSalaire() + getSalaire() * 0.15f;
    }

    public String toString() {
        return "Ingenieur:\n" + super.toString() +
                "\nSpecialite: " + specialite;
    }
}
```

### Implementation Details

* **Inheritance**: `Ingenieur` inherits from `Employe`.
* **Attributes**: Adds a specialty attribute.
* **Methods**: Overrides the `calculerSalire` method to include a 15% salary increase. The `toString` method is overridden to include the specialty.

## 3. Manager Class

The `Manager` class inherits from `Employe` and adds an attribute for the manager's service.

### Code

```java
package Exercice2;

public class Manager extends Employe {
    private String service;

    public Manager(String nom, String prenom, String email, String telephone, Float salaire, String service) {
        super(nom, prenom, email, telephone, salaire);
        this.service = service;
    }

    public Manager() {}

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public Float calculerSalire() {
        return getSalaire() + getSalaire() * 0.2f;
    }

    public String toString() {
        return "Manager:\n" + super.toString() +
                "\nService: " + service;
    }
}
```

### Implementation Details

* **Inheritance**: `Manager` inherits from `Employe`.
* **Attributes**: Adds a service attribute.
* **Methods**: Overrides the `calculerSalire` method to include a 20% salary increase. The `toString` method is overridden to include the service.

## 4. Main Class

The `Main` class contains the main method to test the different classes.

### Code

```java
package Exercice2;

public class Main {
    public static void main(String[] args) {
        Ingenieur ingenieur = new Ingenieur();
        ingenieur.setNom("Hassan");
        ingenieur.setPrenom("Khalid");
        ingenieur.setEmail("hassan.khalid@gmail.com");
        ingenieur.setTelephone("0654387653");
        ingenieur.setSalaire(11000f);
        ingenieur.setSpecialite("Ingenieur Logicielle");

        Manager manager = new Manager();
        manager.setNom("Houssam");
        manager.setPrenom("Marwan");
        manager.setEmail("marwan@gmail.com");
        manager.setTelephone("0754321239");
        manager.setSalaire(15000f);
        manager.setService("Infrastructure");

        System.out.println(ingenieur);
        System.out.println("\n=========================================\n");
        System.out.println(manager);
    }
}
```

### Implementation Details

* **Object Creation**: Creates instances of `Ingenieur` and `Manager`.
* **Method Calls**: Calls the `toString` method to display the information of the engineer and the manager.

## Execution and Results

When the program is executed:

1. The `ingenieur.toString()` call displays the engineer's information.
2. The `manager.toString()` call displays the manager's information.

### Test Case
```
Ingenieur:
Nom: Hassan
Prenom: Khalid
Email: hassan.khalid@gmail.com
Telephone: 0654387653
Salaire: 12650.0
Specialite: Ingenieur Logicielle

=========================================

Manager:
Nom: Houssam
Prenom: Marwan
Email: marwan@gmail.com
Telephone: 0754321239
Salaire: 18000.0
Service: Infrastructure
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of classes in Java to manage the salaries of engineers and managers. By creating instances of `Ingenieur` and `Manager`, and displaying their information, the exercise illustrates object-oriented programming principles and class interactions.
