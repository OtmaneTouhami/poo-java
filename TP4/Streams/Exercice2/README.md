# Lab Report - Exercise 2: EmployeeManager

## Description

In this exercise, I implemented an employee management system using Java Streams. The objective was to demonstrate various stream operations on a list of employees, including summing salaries, sorting, filtering, and reducing.

## 1. Employe Class

The `Employe` class represents an employee with attributes for name, department, and salary.

### Code

```java
package Streams.Exercice2;

public class Employe {
    private String nom;
    private String departement;
    private double salaire;

    public Employe(String nom, String departement, double salaire) {
        this.nom = nom;
        this.departement = departement;
        this.salaire = salaire;
    }

    public Employe() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", departement='" + departement + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}

```

### Implementation Details

* **Attributes**:
    * `nom`: Represents the name of the employee.
    * `departement`: Represents the department of the employee.
    * `salaire`: Represents the salary of the employee.
  
* **Constructor**:
  * Initializes an employee with a name, department, and salary.
* **Getters and Setters**:
    * `getNom()`: Returns the name of the employee.
    * `setNom()`: Sets the name of the employee.
    * `getDepartement()`: Returns the department of the employee.
    * `setDepartement()`: Sets the department of the employee.
    * `getSalaire()`: Returns the salary of the employee.
    * `setSalaire()`: Sets the salary of the employee.
* **toString()**:
    * Returns a string representation of the employee.

## 2. EmployeeManager Class

The `EmployeeManager` class contains the main method and demonstrates various stream operations on a list of employees.

### Code

```java
package Streams.Exercice2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employe> employees = new ArrayList<>();
        employees.add(new Employe("Yassir", "Informatique", 25000));
        employees.add(new Employe("Nadia", "Logistique", 22000));
        employees.add(new Employe("Rachid", "Ressources humaines", 24000));
        employees.add(new Employe("Fatima", "Finance", 23000));
        employees.add(new Employe("Hassan", "Marketing", 25000));
        employees.add(new Employe("Sara", "Ressources humaines", 26000));
        employees.add(new Employe("Ahmed", "Ventes", 27000));
        employees.add(new Employe("Khalid", "Logistique", 28000));
        employees.add(new Employe("Soukaina", "Informatique", 29000));
        employees.add(new Employe("Otmane", "Informatique", 30000));

        // Total salary of all employees
        double sumSalary = employees.stream()
                .mapToDouble(Employe::getSalaire)
                .sum();
        System.out.println("Total salary of all employees: " + sumSalary);

        // Employees sorted by name
        List<Employe> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employe::getNom))
                .toList();
        System.out.println("Employees sorted by name: " + sortedEmployees);

        // Employees with salary greater than or equal to a given value
        double salaryToSearch;
        try {
            System.out.print("Enter a salary to search: ");
            salaryToSearch = sc.nextDouble();
            sc.nextLine();

            List<Employe> employeesWithGivenSalary = employees.stream()
                    .filter(employe -> employe.getSalaire() >= salaryToSearch)
                    .toList();
            System.out.println("Employees with salary greater than or equal to " + salaryToSearch + ": " + employeesWithGivenSalary);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid salary.");
        } finally {
            sc.close();
        }

        // Employee with the highest salary
        Employe employeeWithMaxSalary = employees.stream()
                .reduce((max, employee) -> employee.getSalaire() > max.getSalaire() ? employee : max)
                .orElse(null);
        System.out.println("Employee with the highest salary: " + employeeWithMaxSalary);

        // All employee names concatenated
        String allEmployeesNames = employees.stream()
                .map(Employe::getNom)
                .reduce((names, employeeName) -> names + " - " + employeeName)
                .orElse("No employees found");
        System.out.println("All employee names: " + allEmployeesNames);
    }
}
```

### Implementation Details

* **List Initialization**:
```java
List<Employe> employees = new ArrayList<>();
employees.add(new Employe("Yassir", "Informatique", 25000));
employees.add(new Employe("Nadia", "Logistique", 22000));
employees.add(new Employe("Rachid", "Ressources humaines", 24000));
employees.add(new Employe("Fatima", "Finance", 23000));
employees.add(new Employe("Hassan", "Marketing", 25000));
employees.add(new Employe("Sara", "Ressources humaines", 26000));
employees.add(new Employe("Ahmed", "Ventes", 27000));
employees.add(new Employe("Khalid", "Logistique", 28000));
employees.add(new Employe("Soukaina", "Informatique", 29000));
employees.add(new Employe("Otmane", "Informatique", 30000));
```
* Initializes a list of employees with their names, departments, and salaries.

**Total Salary of All Employees**:
```java
double sumSalary = employees.stream()
        .mapToDouble(Employe::getSalaire)
        .sum();
System.out.println("Total salary of all employees: " + sumSalary);
```
* Uses `stream()` to create a stream from the list.
* Uses `mapToDouble()` to map each employee to their salary.
* Uses `sum()` to calculate the total salary.
* Prints the total salary.

**Employees Sorted by Name**:
```java
List<Employe> sortedEmployees = employees.stream()
        .sorted(Comparator.comparing(Employe::getNom))
        .toList();
System.out.println("Employees sorted by name: " + sortedEmployees);
```
* Uses `sorted()` with a comparator to sort employees by name.
* Collects the sorted employees into a list using `toList()`.
* Prints the sorted list of employees.

**Employees with Salary Greater Than or Equal to a Given Value**:
```java
double salaryToSearch;
try {
    System.out.print("Enter a salary to search: ");
    salaryToSearch = sc.nextDouble();
    sc.nextLine();

    List<Employe> employeesWithGivenSalary = employees.stream()
            .filter(employe -> employe.getSalaire() >= salaryToSearch)
            .toList();
    System.out.println("Employees with salary greater than or equal to " + salaryToSearch + ": " + employeesWithGivenSalary);
} catch (Exception e) {
    System.out.println("Invalid input! Please enter a valid salary.");
} finally {
    sc.close();
}
```
* Prompts the user to enter a salary to search for.
* Uses `filter()` to keep only employees with a salary greater than or equal to the given value.
* Collects the filtered employees into a list using `toList()`.
* Prints the list of employees with the given salary.

**Employee with the Highest Salary**:
```java
Employe employeeWithMaxSalary = employees.stream()
        .reduce((max, employee) -> employee.getSalaire() > max.getSalaire() ? employee : max)
        .orElse(null);
System.out.println("Employee with the highest salary: " + employeeWithMaxSalary);
```
* Uses `reduce()` to find the employee with the highest salary.
* Prints the employee with the highest salary.

**All Employee Names Concatenated**:
```java
String allEmployeesNames = employees.stream()
        .map(Employe::getNom)
        .reduce((names, employeeName) -> names + " - " + employeeName)
        .orElse("No employees found");
System.out.println("All employee names: " + allEmployeesNames);
```
* Uses `map()` to get the names of all employees.
* Uses `reduce()` to concatenate all employee names.
* Prints the concatenated employee names.

## 3. Execution and Results

When the program is executed:

1. The total salary of all employees is displayed.
2. The employees sorted by name are displayed.
3. The employees with salary greater than or equal to a given value are displayed.
4. The employee with the highest salary is displayed.
5. All employee names concatenated are displayed.

### Test Case
```
Total salary of all employees: 259000.0
Employees sorted by name: [Employe{nom='Ahmed', departement='Ventes', salaire=27000.0}, Employe{nom='Fatima', departement='Finance', salaire=23000.0}, Employe{nom='Hassan', departement='Marketing', salaire=25000.0}, Employe{nom='Khalid', departement='Logistique', salaire=28000.0}, Employe{nom='Nadia', departement='Logistique', salaire=22000.0}, Employe{nom='Otmane', departement='Informatique', salaire=30000.0}, Employe{nom='Rachid', departement='Ressources humaines', salaire=24000.0}, Employe{nom='Sara', departement='Ressources humaines', salaire=26000.0}, Employe{nom='Soukaina', departement='Informatique', salaire=29000.0}, Employe{nom='Yassir', departement='Informatique', salaire=25000.0}]
Enter a salary to search: 27000
Employees with salary greater than or equal to 27000.0: [Employe{nom='Ahmed', departement='Ventes', salaire=27000.0}, Employe{nom='Khalid', departement='Logistique', salaire=28000.0}, Employe{nom='Soukaina', departement='Informatique', salaire=29000.0}, Employe{nom='Otmane', departement='Informatique', salaire=30000.0}]
Employee with the highest salary: Employe{nom='Otmane', departement='Informatique', salaire=30000.0}
All employee names: Yassir - Nadia - Rachid - Fatima - Hassan - Sara - Ahmed - Khalid - Soukaina - Otmane
```

## Conclusion

Through this exercise, I demonstrated various stream operations on a list of employees using Java Streams. The `EmployeeManager` class showcases summing, sorting, filtering, and reducing techniques, illustrating fundamental stream manipulation concepts.