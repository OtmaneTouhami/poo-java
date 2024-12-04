# Business Layer (Metier)

## Description
The business layer is a crucial component of our School Management System that implements core business logic and rules. It serves as an intermediary between the data access (DAO) and presentation layers, ensuring:

- Data integrity and validation
- Business rule enforcement
- Transaction management
- Error handling and exception management
- Relationship management between entities

## Key Components

### Professor Management (IProfesseurMetier)
Handles all professor-related operations including:
- Professor lifecycle management (CRUD operations)
- Department assignments
- Data validation and integrity checks
- Search functionality

### Department Management (IDepartementMetier)
Manages department-related operations including:
- Department lifecycle management
- Professor-Department relationships
- Department data integrity
- Membership queries

## Professor Management Interface and Implementation

### IProfesseurMetier Interface
The `IProfesseurMetier` interface defines the contract for managing professor-related operations in our school management system. This interface ensures consistent handling of professor data and enforces business rules across the application.

```java
public interface IProfesseurMetier {
    List<Professeur> getAllProfesseur();
    List<Professeur> getProfesseurByKey(String key) throws SQLException;
    Professeur addProfesseur(Professeur professeur);
    boolean deleteProfesseur(int id);
    boolean attachToDepartement(Integer prof_id, Integer depart_id) throws SQLException;
}
```

### IProfesseurMetierImpl Implementation
The implementation class provides concrete implementations of all interface methods, incorporating business logic, validation, and data access through the DAO layer.

```java
public class IProfesseurMetierImpl implements IProfesseurMetier {
    private IProfesseurDAO professeurDAO;

    public IProfesseurMetierImpl(IProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }
}
```

The constructor uses dependency injection to receive the DAO instance, promoting:
- Loose coupling between layers
- Easier unit testing
- Flexibility in changing data access implementations

### Method Implementations

#### 1. getAllProfesseur()
```java
@Override
public List<Professeur> getAllProfesseur() {
    return professeurDAO.getAll();
}
```
This method retrieves all professors from the database:
- Purpose: Provides a complete list of all professors in the system
- Implementation: Direct delegation to DAO layer's getAll method
- Return Value: List of Professeur objects, empty list if none exist

#### 2. getProfesseurByKey(String key)
```java
@Override
public List<Professeur> getProfesseurByKey(String key) throws SQLException {
    return professeurDAO.getByKey(key);
}
```
This method implements search functionality:
- Purpose: Search professors using a keyword
- Parameters: 
  * key: Search term to match against professor attributes
- Implementation: Delegates to DAO's search functionality
- Exception Handling: Propagates SQL exceptions for proper error management

#### 3. addProfesseur(Professeur professeur)
```java
@Override
public Professeur addProfesseur(Professeur professeur) {
    try {
        if (professeurDAO.getByCin(professeur.getCin()) == null &&
            professeurDAO.getByEmail(professeur.getEmail()) == null) {
            return professeurDAO.save(professeur);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}
```
This method handles professor creation with validation:
- Purpose: Add new professor while ensuring data integrity
- Parameters:
  * professeur: New professor object to be added
- Business Rules:
  * Validates CIN uniqueness
  * Ensures email uniqueness
  * Prevents duplicate professors
- Implementation:
  * Checks existing professors by CIN and email
  * Only saves if both checks pass
  * Converts SQL exceptions to runtime exceptions
- Return Value:
  * Saved professor object if successful
  * null if validation fails

#### 4. deleteProfesseur(int id)
```java
@Override
public boolean deleteProfesseur(int id) {
    return professeurDAO.delete(id) > 0;
}
```
This method handles professor deletion:
- Purpose: Remove professor from the system
- Parameters:
  * id: Professor ID to delete
- Implementation:
  * Direct delegation to DAO layer
  * Checks affected rows to confirm deletion
- Return Value:
  * true if deletion successful
  * false if professor not found or deletion failed

#### 5. attachToDepartement(Integer prof_id, Integer depart_id)
```java
@Override
public boolean attachToDepartement(Integer prof_id, Integer depart_id) throws SQLException {
    return professeurDAO.attach(prof_id, depart_id) > 0;
}
```
This method manages department assignments:
- Purpose: Associate professor with department
- Parameters:
  * prof_id: Professor ID to assign
  * depart_id: Target department ID
- Implementation:
  * Delegates to DAO layer for relationship creation
  * Verifies success through affected rows count
- Exception Handling:
  * Propagates SQL exceptions for transaction management
- Return Value:
  * true if assignment successful
  * false if operation failed

## Department Management Interface and Implementation

### IDepartementMetier Interface
The `IDepartementMetier` interface defines the contract for managing department-related operations in our school management system. This interface ensures consistent department management and maintains proper relationships with professors.

**Key Responsibilities:**
- Department lifecycle management (create, read, update, delete)
- Department-Professor relationship handling
- Data integrity enforcement
- Department membership queries

```java
public interface IDepartementMetier {
    Departement addDepartement(Departement departement);
    List<Departement> getAllDepartments();
    boolean deleteDepartment(int id);
    Departement updateDepartment(Departement departement);
    List<Professeur> getAllProfInDepartment(int id);
}
```

### IDepartementMetierImpl Implementation
The implementation class provides concrete implementations of the interface methods with proper business logic and validation.

```java
public class IDepartementMetierImpl implements IDepartementMetier {
    private IDepartementDAO departementDAO;

    public IDepartementMetierImpl(IDepartementDAO departementDAO) {
        this.departementDAO = departementDAO;
    }
}
```

The constructor uses dependency injection for the DAO instance, which:
- Promotes loose coupling
- Facilitates unit testing
- Allows flexible DAO implementation changes
- Follows dependency inversion principle

### Method Implementations

#### 1. addDepartement(Departement departement)
```java
@Override
public Departement addDepartement(Departement departement) {
    Departement d = departementDAO.getByName(departement.getNom());
    if (d == null) {
        return departementDAO.save(departement);
    }
    return null;
}
```
This method handles new department creation:
- Purpose: Create new department with validation
- Parameters:
  * departement: New department object with required data
- Business Rules:
  * Department name must be unique
  * No duplicate departments allowed
- Implementation Details:
  * Checks for existing department with same name
  * Only saves if name is unique
  * Returns null if validation fails

#### 2. getAllDepartments()
```java
@Override
public List<Departement> getAllDepartments() {
    return departementDAO.getAll();
}
```
This method retrieves all departments:
- Purpose: Get a complete list of all departments
- Implementation Details:
  * Direct delegation to DAO layer
  * No additional business validation needed
  * Returns empty list if no departments exist

#### 3. deleteDepartment(int id)
```java
@Override
public boolean deleteDepartment(int id) {
    return departementDAO.delete(id) > 0;
}
```
This method manages department deletion:
- Purpose: Remove a department from the system
- Parameters:
  * id: Unique identifier of department to delete
- Implementation Details:
  * Delegates deletion to DAO layer
  * Verifies deletion through affected rows
  * Returns boolean success indicator

#### 4. updateDepartment(Departement departement)
```java
@Override
public Departement updateDepartment(Departement departement) {
    if (departement.getId_depart() > 0 && 
        departementDAO.getById(departement.getId_depart()) != null) {
        return departementDAO.save(departement);
    }
    return null;
}
```
This method handles department updates:
- Purpose: Modify existing department information
- Parameters:
  * departement: Department object with updated data
- Business Rules:
  * Department must exist
  * ID must be valid (greater than 0)
- Implementation Details:
  * Verifies department exists before update
  * Uses same save method as add operation
  * Returns null if department not found

#### 5. getAllProfInDepartment(int id)
```java
@Override
public List<Professeur> getAllProfInDepartment(int id) {
    return departementDAO.getAllProf(id);
}
```
This method retrieves department members:
- Purpose: Get all professors in a specific department
- Parameters:
  * id: Department ID to query
- Implementation Details:
  * Queries professor-department relationships
  * Returns list of associated professors
  * Handles empty departments gracefully

## Conclusion

The business layer implementation demonstrates a well-structured approach to managing school data through carefully designed interfaces and their implementations. By choosing to separate professor and department management into distinct interfaces, we've achieved better code organization and maintainability. The implementation leverages dependency injection for loose coupling with the DAO layer, while enforcing business rules and data validation at the appropriate level. Key design decisions include using interface-based design for flexibility, implementing thorough data validation (especially for unique constraints like department names and professor emails), and providing clear error handling through appropriate exception management. The code structure follows the Single Responsibility Principle, with each class and method having a focused purpose, making the system easier to maintain and extend.
