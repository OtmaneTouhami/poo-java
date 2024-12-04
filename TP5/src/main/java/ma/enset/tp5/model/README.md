# Model Layer

## Description
This package contains the core domain entities (Professeur and Departement) that represent the data model of our School Management System. These Plain Old Java Objects (POJOs) are designed to map database records to Java objects, providing a clean and type-safe way to handle school data throughout the application. Each entity includes proper constructors, getters, setters, and database result set mapping functionality.

## Department Class Methods

### Constructors
1. `Departement(Integer id_depart, String nom)`
   - Creates a new department with both ID and name
   - Used when working with existing departments

2. `Departement(String nom)`
   - Creates a new department with just the name
   - Used when creating a new department (ID will be assigned by database)

3. `Departement()`
   - Default constructor with no parameters
   - Used by the database mapper

### Getter and Setter Methods
- `getId_depart()` / `setId_depart(Integer id_depart)`
  - Handles the department's unique identifier
  - ID is managed by the database

- `getNom()` / `setNom(String nom)`
  - Manages the department's name
  - Essential for department identification

### Utility Methods
- **departementAssembler(ResultSet resultSet)**
```java
  public static Departement departementAssembler(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setId_depart(resultSet.getInt("id_depart"));
        departement.setNom(resultSet.getString("nom"));
        return departement;
    }
  ```
  - Static method to create a Department from database results
  - Handles null results safely
  - Maps database columns to object properties
  - Throws SQLException if database access fails


- **toString()**
```java
    public String toString() {
        return "Departement{" +
                "id=" + id_depart +
                ", nom='" + nom + '\'' +
                "}";
    }
```
  - Creates a readable string representation
  - Shows department ID and name
  - Used for logging and debugging

## Professor Class Methods

### Constructors
1. `Professeur(String nom, String prenom, String email, String cin, String telephone, String addresse, LocalDate date_recrutement)`
   - Creates a new professor with all required information
   - Used when registering a new professor
   - Department can be set separately

2. `Professeur()`
   - Default constructor
   - Used by the database mapper

### Getter and Setter Methods
- `getId_prof()` / `setId_prof(int id_prof)`
  - Manages professor's unique identifier
  - ID is handled by the database

- `getNom()` / `setNom(String nom)`
  - Handles professor's last name

- `getPrenom()` / `setPrenom(String prenom)`
  - Handles professor's first name

- `getCin()` / `setCin(String cin)`
  - Manages national ID number
  - Must be unique for each professor

- `getAddresse()` / `setAddresse(String addresse)`
  - Handles professor's physical address

- `getTelephone()` / `setTelephone(String telephone)`
  - Manages contact phone number

- `getEmail()` / `setEmail(String email)`
  - Handles professional email address
  - Must be unique for each professor

- `getDate_recrutement()` / `setDate_recrutement(LocalDate date_recrutement)`
  - Manages recruitment date
  - Uses Java's LocalDate for proper date handling

- `getDepartement()` / `setDepartement(Departement departement)`
  - Handles department assignment
  - Links professor to their department

### Utility Methods
- **professorAssembler(ResultSet resultSet)**
```java
    public static Professeur professorAssembler(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Professeur professeur = new Professeur();
        professeur.setId_prof(resultSet.getInt("id_prof"));
        professeur.setNom(resultSet.getString("nom"));
        professeur.setPrenom(resultSet.getString("prenom"));
        professeur.setCin(resultSet.getString("cin"));
        professeur.setAddresse(resultSet.getString("adresse"));
        professeur.setDate_recrutement(resultSet.getDate("date_recrutement").toLocalDate());
        professeur.setEmail(resultSet.getString("email"));
        professeur.setTelephone(resultSet.getString("telephone"));
        return professeur;
    }
```
  - Static method to create Professor from database results
  - Safely handles null results
  - Maps all database columns to object properties
  - Includes date conversion for recruitment date
  - Throws SQLException if database access fails


- **toString()**
```java
    public String toString() {
        return "Professeur{" +
                "id=" + id_prof +
                ", nom='" + nom + '\'' +
                ", prénom='" + prenom + '\'' +
                ", CIN='" + cin + '\'' +
                ", adresse='" + addresse + '\'' +
                ", tél='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", recrutement=" + (date_recrutement != null ? date_recrutement.toString() : "Non définie") +
                ", département=" + (departement != null ? departement.getNom() : "Non affecté") +
                "}";
    }
```
  - Creates detailed string representation
  - Includes all professor information
  - Handles null department and date gracefully
  - Used for logging and debugging

## Conclusion
The model layer provides a clean and efficient way to represent our school management data. Through well-structured classes with proper validation and database mapping capabilities, it ensures data integrity while remaining simple to use. The combination of Professeur and Departement classes forms the foundation for managing academic staff and organizational structure in our application.