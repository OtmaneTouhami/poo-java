# DAO Layer - Database Access Components

## Description
This package implements the Data Access Object (DAO) pattern for managing database operations in our school management system. It provides a structured approach to handle database interactions through well-defined interfaces and ensures proper connection management using the Singleton pattern.

## Components Overview

### 1. Database Connection (SignletonConnexionDB)

#### Class Structure
```java
public class SignletonConnexionDB {
    private static SignletonConnexionDB instance;
    private final Connection connection;
}
```

#### Methods Implementation

##### Constructor Method
```java
private SignletonConnexionDB() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school_db";
        String username = "root";
        String password = "*********";
        connection = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException e) {
        throw new SQLException("JDBC Driver not found", e);
    }
}
```

**How It Works**
- Initializes MySQL JDBC driver
- Establishes database connection with provided credentials
- Implements error handling for driver loading failures

##### getInstance Method
```java
public static SignletonConnexionDB getInstance() throws SQLException {
    if (instance == null) {
        return new SignletonConnexionDB();
    } else if (instance.getConnection().isClosed()) {
        return new SignletonConnexionDB();
    }
    return instance;
}
```

**How It Works**
- Ensures single database connection instance
- Creates new connection if none exists
- Verifies connection state and recreates if closed

### 2. Professor Data Access (IProfesseurDAO)

#### Interface Definition
```java
public interface IProfesseurDAO {
    Professeur getById(int id) throws SQLException;
    Professeur getByEmail(String email) throws SQLException;
    Professeur getByCin(String cin) throws SQLException;
    List<Professeur> getByKey(String key) throws SQLException;
    List<Professeur> getAll();
    Professeur save(Professeur professeur);
    int delete(int id);
    int attach(Integer prof_id, Integer depart_id) throws SQLException;
}
```

#### Methods Description
- **getById**: Retrieves professor using unique identifier
- **getByEmail**: Finds professor by email address
- **getByCin**: Searches professor using national ID
- **getByKey**: Performs keyword-based professor search
- **getAll**: Returns complete professor list
- **save**: Creates or updates professor record
- **delete**: Removes professor from database
- **attach**: Associates professor with department

### 3. Department Data Access (IDepartementDAO)

#### Interface Definition
```java
public interface IDepartementDAO {
    Departement save(Departement departement);
    List<Departement> getAll();
    Departement getById(int id);
    Departement getByName(String name);
    int delete(int id);
    List<Professeur> getAllProf(int id);
}
```

#### Methods Description
- **save**: Creates or updates department information
- **getAll**: Retrieves all departments
- **getById**: Finds department by ID
- **getByName**: Searches department using name
- **delete**: Removes department record
- **getAllProf**: Lists professors in specific department

## Implementation Details - IProfesseurDAOImpl

### Utility Methods

#### 1. prepareConnection Method
```java
public static Connection prepareConnection() {
    try {
        return SignletonConnexionDB.getInstance().getConnection();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```
This helper method gets a database connection using our singleton connection manager. It makes the code cleaner by handling the connection setup in one place.

#### 2. getProfesseur Method
```java
public Professeur getProfesseur(ResultSet resultSet) throws SQLException {
    Professeur professeur = Professeur.professorAssembler(resultSet);
    Integer idDepart = (Integer) resultSet.getObject("id_depart");
    if (idDepart != null) {
        IDepartementDAO departementDAO = new IDepartementDAOImpl();
        Departement departement = departementDAO.getById(idDepart);
        professeur.setDepartement(departement);
    }
    return professeur;
}
```
This method creates a Professor object from database results. It:
- Uses a helper method to create the basic professor object
- Checks if the professor has a department
- If yes, loads the department details
- Returns the complete professor object

### Main Operations

#### 1. getById Implementation
```java
public Professeur getById(int id) throws SQLException {
    String query = "SELECT * FROM professor WHERE id_prof = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return getProfesseur(resultSet);
            }
        }
    } catch (SQLException e) {
        throw new SQLException("Error retrieving professor by ID: " + id, e);
    }
    return null;
}
```
This method finds a professor by their ID:
- Creates a SQL query with a parameter placeholder (?)
- Sets up database connection and statement
- Safely sets the ID parameter
- Executes the query and processes the result
- Returns null if no professor is found

#### 2. getByEmail Implementation
```java
public Professeur getByEmail(String email) throws SQLException {
    String query = "SELECT * FROM professor WHERE email = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setString(1, email);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return getProfesseur(resultSet);
            }
        }
    } catch (SQLException e) {
        throw new SQLException("Error retrieving professor by Email: " + email, e);
    }
    return null;
}
```
Similar to getById, but searches using email address:
- Uses prepared statement to prevent SQL injection
- Returns the first matching professor
- Includes proper error handling

#### 3. getByKey Implementation
```java
public List<Professeur> getByKey(String key) throws SQLException {
    String query = "SELECT * FROM professor WHERE nom LIKE ? OR prenom LIKE ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setString(1, "%" + key + "%");
        preparedStatement.setString(2, "%" + key + "%");
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Professeur> professeurs = new ArrayList<>();
            while (resultSet.next()) {
                professeurs.add(getProfesseur(resultSet));
            }
            return professeurs;
        }
    }
}
```
This method performs a flexible search:
- Searches in both first and last names
- Uses SQL LIKE with wildcards for partial matches
- Returns all matching professors in a list

#### 4. save Implementation
```java
public Professeur save(Professeur professeur) {
   if (professeur.getId_prof() == null) {
      String insertQuery = "INSERT INTO professor "
              + "(nom, prenom, cin, adresse, telephone, email, date_recrutement) "
              + "VALUES (?, ?, ?, ?, ?, ?, ?)";
      try (
              Connection connection = prepareConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)
      ) {
         prepareProfessor(professeur, preparedStatement);

         int affectedRows = preparedStatement.executeUpdate();
         if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                  professeur.setId_prof(generatedKeys.getInt(1));
               }
            }
            return professeur;
         }

      } catch (SQLException e) {
         System.out.println("Error during insert: " + e.getMessage());
      }
   } else {
      String updateQuery = "UPDATE professor SET "
              + "nom = ?, prenom = ?, cin = ?, adresse = ?, telephone = ?, email = ?, date_recrutement = ? "
              + "WHERE id_prof = ?";
      try (
              Connection connection = prepareConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
      ) {
         prepareProfessor(professeur, preparedStatement);
         preparedStatement.setInt(8, professeur.getId_prof());

         int affectedRows = preparedStatement.executeUpdate();
         if (affectedRows > 0) {
            return professeur;
         }
      } catch (SQLException e) {
         System.out.println("Error during update: " + e.getMessage());
      }
   }
   return null;
}
```
This method handles both insert and update operations:
- Checks if professor has an ID to determine operation type
- For new professors:
  - Generates INSERT query
  - Gets generated ID after insert
- For existing professors:
  - Generates UPDATE query
  - Updates all fields
- Returns the saved professor object

#### 5. delete Implementation
```java
public int delete(int id) {
    String query = "DELETE FROM professor WHERE id_prof = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error deleting professor with ID " + id, e);
    }
}
```
This method removes a professor from the database:
- Uses prepared statement for safe deletion
- Returns number of affected rows
- Throws runtime exception if deletion fails

### Helper Methods

#### prepareProfessor Method
```java
private void prepareProfessor(Professeur professeur, PreparedStatement preparedStatement) throws SQLException {
    preparedStatement.setString(1, professeur.getNom());
    preparedStatement.setString(2, professeur.getPrenom());
    preparedStatement.setString(3, professeur.getCin());
    preparedStatement.setString(4, professeur.getAddresse());
    preparedStatement.setString(5, professeur.getTelephone());
    preparedStatement.setString(6, professeur.getEmail());
    preparedStatement.setDate(7, java.sql.Date.valueOf(professeur.getDate_recrutement()));
}
```
This helper method sets up prepared statement parameters:
- Takes professor object and statement
- Sets all professor fields in correct order
- Handles date conversion for recruitment date
- Used by both insert and update operations

## Implementation Details - IDepartementDAOImpl

### Utility Methods

#### prepareConnection Method
```java
public static Connection prepareConnection() {
    try {
        return SignletonConnexionDB.getInstance().getConnection();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```
This helper method:
- Gets a database connection using our singleton manager
- Wraps SQL exceptions in runtime exceptions
- Makes connection handling consistent across the class

### Main Operations

#### 1. save Implementation
```java
public Departement save(Departement departement) {
   if (departement.getId_depart() == null) {
      String insertQuery = "INSERT INTO department (nom) VALUES (?)";
      try (
              Connection connection = prepareConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(
                      insertQuery,
                      Statement.RETURN_GENERATED_KEYS
              )
      ) {
         preparedStatement.setString(1, departement.getNom());
         int affectedRows = preparedStatement.executeUpdate();
         if (affectedRows > 0) {
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                  departement.setId_depart(generatedKeys.getInt(1));
               }
            }
            return departement;
         }

      } catch (SQLException e) {
         System.out.println("Error during insert: " + e.getMessage());
      }
   } else {
      String updateQuery = "UPDATE department SET nom = ? WHERE id_depart = ?";
      try (
              Connection connection = prepareConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
      ) {
         preparedStatement.setString(1, departement.getNom());
         preparedStatement.setInt(2, departement.getId_depart());

         int affectedRows = preparedStatement.executeUpdate();
         if (affectedRows > 0) {
            return departement;
         }
      } catch (SQLException e) {
         System.out.println("Error during update: " + e.getMessage());
      }
   }
   return null;
}
```
This method handles both creating and updating departments:
- Checks if department has an ID to determine operation type
- For new departments:
  - Uses simple INSERT query with department name
  - Retrieves and sets the generated ID
- For existing departments:
  - Updates the department name
  - Keeps the same ID
- Returns the saved department or null if operation fails

#### 2. getAll Implementation
```java
public List<Departement> getAll() {
    String query = "SELECT * FROM department";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Departement> departments = new ArrayList<>();

        while (resultSet.next()) {
            Departement departement = new Departement();
            departement.setId_depart(resultSet.getInt("id_depart"));
            departement.setNom(resultSet.getString("nom"));
            departments.add(departement);
        }
        return departments;
    } catch (SQLException e) {
        System.out.println("Error retrieving departments: " + e.getMessage());
    }
    return List.of();
}
```
This method retrieves all departments:
- Uses a simple SELECT query
- Creates department objects from results
- Returns empty list if error occurs
- Handles resources properly with try-with-resources

#### 3. getById Implementation
```java
public Departement getById(int id) {
    String query = "SELECT * FROM department WHERE id_depart = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Departement.departementAssembler(resultSet);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving department by ID: " + id, e);
    }
    return null;
}
```
This method finds a specific department:
- Uses prepared statement for safe parameter handling
- Returns null if department not found
- Uses department assembler for object creation
- Throws runtime exception if database error occurs

#### 4. getByName Implementation
```java
public Departement getByName(String name) {
    String query = "SELECT * FROM department WHERE nom = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setString(1, name);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return Departement.departementAssembler(resultSet);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving department by name: " + name, e);
    }
    return null;
}
```
Similar to getById but searches by name:
- Uses exact name matching
- Returns first matching department
- Null if no department found
- Includes error handling

#### 5. delete Implementation
```java
public int delete(int id) {
    String query = "DELETE FROM department WHERE id_depart = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error deleting department with ID " + id, e);
    }
}
```
This method removes a department:
- Returns number of affected rows
- Uses prepared statement for safety
- Throws runtime exception on failure

#### 6. getAllProf Implementation
```java
public List<Professeur> getAllProf(int id) {
    String query = "SELECT * FROM professor WHERE id_depart = ?";
    try (
        Connection connection = prepareConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Professeur> professeurs = new ArrayList<>();
            IProfesseurDAOImpl professeurDAO = new IProfesseurDAOImpl();
            while (resultSet.next()) {
                professeurs.add(professeurDAO.getProfesseur(resultSet));
            }
            return professeurs;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error retrieving professors for department with ID: " + id, e);
    }
}
```
This method gets all professors in a department:
- Uses department ID to find related professors
- Creates full professor objects with department info
- Returns empty list if no professors found
- Throws runtime exception on database errors

## Database Structure

### Tables Schema

#### 1. Professor Table
```sql
CREATE TABLE professor (
    id_prof INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    cin VARCHAR(20) UNIQUE NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(15),
    email VARCHAR(100) UNIQUE NOT NULL,
    date_recrutement DATE NOT NULL,
    id_depart INT,
    FOREIGN KEY (id_depart) REFERENCES department(id_depart)
);
```

**Fields Description**:
- `id_prof`: Unique identifier for professors
- `nom`: Professor's last name
- `prenom`: Professor's first name
- `cin`: National ID number (unique)
- `adresse`: Professor's address
- `telephone`: Contact number
- `email`: Email address (unique)
- `date_recrutement`: Recruitment date
- `id_depart`: Foreign key linking to department

#### 2. Department Table
```sql
CREATE TABLE department (
    id_depart INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) UNIQUE NOT NULL
);
```

**Fields Description**:
- `id_depart`: Unique identifier for departments
- `nom`: Department name (unique)

### Database Relationships

1. **One-to-Many Relationship**:
   - One department can have multiple professors
   - Each professor belongs to at most one department
   - Implemented through foreign key `id_depart` in professor table
   - Allows NULL for professors not yet assigned to departments

2. **Constraints**:
   - Unique constraints on professor's CIN and email
   - Unique constraint on department name
   - Foreign key constraint ensuring data integrity
   - NOT NULL constraints on essential fields

## Conclusion

### Implementation Summary
The DAO layer implementation provides a robust and efficient way to manage professors and departments data:

1. **Design Patterns**:
   - Singleton pattern for database connection management
   - DAO pattern for data access abstraction
   - Factory pattern for object creation

2. **Code Organization**:
   - Clear separation of concerns
   - Interface-based design
   - Consistent error handling
   - Resource management best practices

3. **Features Implemented**:
   - CRUD operations for both professors and departments
   - Relationship management between entities
   - Flexible search capabilities
   - Data integrity maintenance
