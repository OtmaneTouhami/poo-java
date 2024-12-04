# Main Class: Professor and Department Management System

## Description
This project demonstrates a system for managing professors and departments. It uses a three-layer design: one for database operations, one for business rules, and one for user interaction. The Main class controls all these parts to show how the system works.

## Main Class Overview
The Main class is used to test and show all the features of the system. Each section below explains how different parts of the system work.

### Methods Implementation

#### 1. Main Method
```java
public static void main(String[] args) {
    IProfesseurDAO professeurDAO = new IProfesseurDAOImpl();
    IProfesseurMetier professeurMetier = new IProfesseurMetierImpl(professeurDAO);

    IDepartementDAO departementDAO = new IDepartementDAOImpl();
    IDepartementMetier departementMetier = new IDepartementMetierImpl(departementDAO);

    try {
        createDepartments(departementMetier);
        createProfessors(professeurMetier);
        testGetAllDepartments(departementMetier);
        testGetAllProfessors(professeurMetier);
        testAttachProfessorToDepartment(professeurMetier);
        testUpdateAndDeleteDepartment(departementMetier);
        testSearchAndUpdateProfessor(professeurMetier);
        testGetAllProfessorsInDepartment(departementMetier);
        testDeleteProfessor(professeurMetier);
    } catch (Exception e) {
        System.out.println("An error occurred during testing: " + e.getMessage());
    }
}
```
**How It Works**
The main method starts the system by setting up the database and business layers. It then runs several tests to check all system features. It also includes error handling to manage any problems that might occur.

#### 2. createDepartments Method
```java
private static void createDepartments(IDepartementMetier departementMetier) {
    System.out.println("\n=== Creating Departments ===");
    List.of(
            new Departement("Informatique"),
            new Departement("Mathématiques"),
            new Departement("Physique")
    ).forEach(departementMetier::addDepartement);
}
```
**How It Works**
This method creates three basic departments: Computer Science, Mathematics, and Physics. It uses Java's List feature to create them all at once and add them to the database.

#### 3. createProfessors Method
```java
private static void createProfessors(IProfesseurMetier professeurMetier) {
    System.out.println("\n=== Creating Professors ===");
    List.of(
            new Professeur("El Amrani", "Karim", "karim.elamrani@gmail.com", "BK432156", 
                          "0661234567", "Hay Riad, Rabat", LocalDate.of(2006, 9, 1)),
            new Professeur("Bensouda", "Nadia", "nadia.bensouda@gmail.com", "BE789012", 
                          "0662345678", "Maârif, Casablanca", LocalDate.of(2008, 10, 15)),
            new Professeur("Tazi", "Mohammed", "mohammed.tazi@gmail.com", "BJ567890", 
                          "0663456789", "Guéliz, Marrakech", LocalDate.of(2010, 1, 10)),
            new Professeur("Alaoui", "Fatima", "fatima.alaoui@gmail.com", "BH234567", 
                          "0664567890", "Quartier Hassan, Rabat", LocalDate.of(2012, 3, 20)),
            new Professeur("Benjelloun", "Youssef", "youssef.benjelloun@gmail.com", "BA678901", 
                          "0665678901", "Anfa, Casablanca", LocalDate.of(2015, 5, 5))
    ).forEach(professeurMetier::addProfesseur);
}
```
**How It Works**
This method adds several professors to the system. For each professor, it stores their name, email, ID number, phone number, address, and start date. All professors are added to the database at once.

#### 4. testGetAllDepartments Method
```java
private static void testGetAllDepartments(IDepartementMetier departementMetier) {
    System.out.println("\n=== All Departments ===");
    departementMetier.getAllDepartments().forEach(System.out::println);
}
```
**How It Works**
This method shows all departments in the system. It gets the list from the database and prints each department's information.

#### 5. testGetAllProfessors Method
```java
private static void testGetAllProfessors(IProfesseurMetier professeurMetier) {
    System.out.println("\n=== All Professors ===");
    professeurMetier.getAllProfesseur().forEach(System.out::println);
}
```
**How It Works**
This method shows all professors in the system. It gets the complete list and displays each professor's information.

#### 6. testAttachProfessorToDepartment Method
```java
private static void testAttachProfessorToDepartment(IProfesseurMetier professeurMetier) throws SQLException {
    System.out.println("\n=== Attach Professor to Department ===");
    boolean isAttached = professeurMetier.attachToDepartement(1, 7);
    System.out.println(isAttached ? "Professor successfully attached to department!" : 
                                   "Failed to attach professor to department.");
}
```
**How It Works**
This method connects a professor to a department. It tries to make the connection and reports whether it worked or not.

#### 7. testUpdateAndDeleteDepartment Method
```java
private static void testUpdateAndDeleteDepartment(IDepartementMetier departementMetier) {
    System.out.println("\n=== Update and Delete Department ===");
    Departement department = departementMetier.getAllDepartments().get(0);
    department.setNom("Computer Science");
    Departement updatedDepartment = departementMetier.updateDepartment(department);
    System.out.println("Updated Department: " + updatedDepartment);

    boolean isDeleted = departementMetier.deleteDepartment(department.getId_depart());
    System.out.println(isDeleted ? "Department deleted successfully!" : "Failed to delete department.");
}
```
**How It Works**
This method shows how to change and remove departments. First, it changes a department's name, then it removes the department from the system.

#### 8. testSearchAndUpdateProfessor Method
```java
private static void testSearchAndUpdateProfessor(IProfesseurMetier professeurMetier) throws SQLException {
    System.out.println("\n=== Search and Update Professor ===");
    List<Professeur> profs = professeurMetier.getProfesseurByKey("Alaoui");
    if (!profs.isEmpty()) {
        Professeur prof = profs.get(0);
        System.out.println("Found Professor: " + prof);

        prof.setEmail("updated.alaoui@gmail.com");
        Professeur updatedProf = professeurMetier.updateProfesseur(prof);
        System.out.println("Updated Professor: " + updatedProf);
    } else {
        System.out.println("No professor found with the keyword!");
    }
}
```
**How It Works**
This method finds a professor and updates their information. It searches for a professor by name, then changes their email address if found.

#### 9. testGetAllProfessorsInDepartment Method
```java
private static void testGetAllProfessorsInDepartment(IDepartementMetier departementMetier) {
    System.out.println("\n=== All Professors in Department ===");
    departementMetier.getAllProfInDepartment(2).forEach(System.out::println);
}
```
**How It Works**
This method shows all professors working in a specific department. It gets and displays the list of professors for department number 2.

#### 10. testDeleteProfessor Method
```java
private static void testDeleteProfessor(IProfesseurMetier professeurMetier) throws SQLException {
    System.out.println("\n=== Search and Delete a Professor  ===");
    Professeur profToDelete = professeurMetier.getProfesseurByKey("Benjelloun").get(0);
    System.out.println("Found Professor: " + profToDelete);
    System.out.println(
            professeurMetier.deleteProfesseur(profToDelete.getId_prof()) ?
                    "Professor deleted successfully" :
                    "Something went wrong!"
    );
}
```
**How It Works**
This method removes a professor from the system. It first finds the professor by name, then removes their record from the database.

## Execution and Results
When the program runs, it performs these steps in order:

1. Creates three departments
2. Adds five professors to the system
3. Shows all departments and professors
4. Tries to assign a professor to a department
5. Changes a department's name and then removes it
6. Finds a professor and updates their email
7. Shows all professors in one department
8. Removes a professor from the system

### Test Case Output
```
=== Creating Departments ===

=== Creating Professors ===

=== All Departments ===
Departement{id=31, nom='Informatique'}
Departement{id=32, nom='Mathématiques'}
Departement{id=33, nom='Physique'}

=== All Professors ===
Professeur{id=21, nom='El Amrani', prénom='Karim', CIN='BK432156', adresse='Hay Riad, Rabat', tél='0661234567', email='karim.elamrani@gmail.com', recrutement=2006-09-01, département=Non affecté}
Professeur{id=22, nom='Bensouda', prénom='Nadia', CIN='BE789012', adresse='Maârif, Casablanca', tél='0662345678', email='nadia.bensouda@gmail.com', recrutement=2008-10-15, département=Non affecté}
Professeur{id=23, nom='Tazi', prénom='Mohammed', CIN='BJ567890', adresse='Guéliz, Marrakech', tél='0663456789', email='mohammed.tazi@gmail.com', recrutement=2010-01-10, département=Non affecté}
Professeur{id=24, nom='Alaoui', prénom='Fatima', CIN='BH234567', adresse='Quartier Hassan, Rabat', tél='0664567890', email='fatima.alaoui@gmail.com', recrutement=2012-03-20, département=Non affecté}
Professeur{id=25, nom='Benjelloun', prénom='Youssef', CIN='BA678901', adresse='Anfa, Casablanca', tél='0665678901', email='youssef.benjelloun@gmail.com', recrutement=2015-05-05, département=Non affecté}

=== Attach Professor to Department ===
Failed to attach professor to department.

=== Update and Delete Department ===
Updated Department: Departement{id=31, nom='Computer Science'}
Department deleted successfully!

=== Search and Update Professor ===
Found Professor: Professeur{id=24, nom='Alaoui', prénom='Fatima', CIN='BH234567', adresse='Quartier Hassan, Rabat', tél='0664567890', email='fatima.alaoui@gmail.com', recrutement=2012-03-20, département=Non affecté}
Updated Professor: Professeur{id=24, nom='Alaoui', prénom='Fatima', CIN='BH234567', adresse='Quartier Hassan, Rabat', tél='0664567890', email='updated.alaoui@gmail.com', recrutement=2012-03-20, département=Non affecté}

=== All Professors in Department ===

=== Search and Delete a Professor  ===
Found Professor: Professeur{id=25, nom='Benjelloun', prénom='Youssef', CIN='BA678901', adresse='Anfa, Casablanca', tél='0665678901', email='youssef.benjelloun@gmail.com', recrutement=2015-05-05, département=Non affecté}
Professor deleted successfully
```

## Conclusion
The Main class shows that our system works well. It can:
- Add, change, and remove both departments and professors
- Connect professors to their departments
- Handle errors when something goes wrong
- Keep different parts of the system organized and separated.
