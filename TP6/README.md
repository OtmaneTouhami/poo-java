# Java Lab 6: Advanced Programming with Generics and I/O Operations

This laboratory work focuses on implementing advanced Java programming concepts through three practical exercises. The project demonstrates the use of generics, file I/O operations, serialization, and design patterns. It includes a directory listing utility, a phone directory management system, and a product-customer management application, showcasing real-world applications of Java's powerful features.

## Table of Contents
1. [Exercise 1: Directory Listing Utility (ls Command Simulation)](#exercise-1-directory-listing-utility-ls-command-simulation)
   - [Overview](#overview)
   - [Classes and Components](#classes-and-components)
   - [Detailed Implementation](#detailed-implementation)
      - [Permission Check Method](#1-permission-check-method)
      - [Directory Inspection Method](#2-directory-inspection-method)
      - [Main Method](#3-main-method)
   - [Execution and Results](#execution-and-results)
      - [How to Run](#how-to-run)
      - [Example Output](#example-output)

2. [Exercise 2: Phone Directory Management System](#exercise-2-phone-directory-management-system)
   - [Overview](#overview-1)
   - [Classes and Implementation Details](#classes-and-implementation-details)
   - [Program Execution](#program-execution)
   - [Design Features and Implementation Notes](#design-features-and-implementation-notes)

3. [Exercise 3: Generic Service Implementation with File Persistence](#exercise-3-generic-service-implementation-with-file-persistence)
   - [Overview](#overview-2)
   - [Core Components](#core-components)
   - [Sample Interactions](#sample-interactions)

4. [Technical Implementation Details](#technical-implementation-details)

## Exercise 1: Directory Listing Utility (ls Command Simulation)

### Overview
This exercise implements a Java-based directory listing utility that simulates the functionality of the Unix/Linux `ls` command. The program provides a hierarchical view of directories and files, along with their permissions and types.

### Classes and Components

#### `Ls.java`
The main and only class in this exercise, responsible for directory traversal and information display.

### Detailed Implementation

#### 1. Permission Check Method
```java
public static String permissions(File file) {
    return (file.canRead() ? "r" : "-") +
           (file.canWrite() ? "w" : "-") +
           (file.isHidden() ? "h" : "-");
}
```
This method determines the permissions of a file or directory:
- Checks if the file is readable (`r`), writable (`w`), and hidden (`h`)
- Returns a string in the format "rwh" where `-` indicates absence of permission
- Each character represents a specific permission flag

#### 2. Directory Inspection Method
```java
public static void directoryInspector(File file, String indent) {
    String type = file.isDirectory() ? "<DIR>" : "<FICH>";
    String line = String.format("%s%s %s %s", indent, file, type, permissions(file));
    System.out.println(line);

    if (file.isDirectory()) {
        File[] subFiles = file.listFiles();
        if (subFiles != null) {
            for (File subFile : subFiles) {
                directoryInspector(subFile, indent + "----");
            }
        }
    }
}
```
This recursive method traverses through directories and files:
- Determines if the current file is a directory (`<DIR>`) or a regular file (`<FICH>`)
- Formats and displays file information including path, type, and permissions
- Uses indentation to show hierarchical structure
- Recursively processes subdirectories with increased indentation

#### 3. Main Method
```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the directory path: ");
    String path = scanner.nextLine();
    File file = new File(path);
    scanner.close();
    if (!file.exists()) {
        System.out.println("The directory does not exist or an I/O error occurred.");
        return;
    }
    directoryInspector(file, "");
}
```
The entry point of the program:
- Accepts user input for the directory path
- Validates the existence of the specified path
- Initiates the directory inspection process

### Execution and Results

#### How to Run
1. Compile the Java file: `javac Ls.java`
2. Run the compiled program: `java Ls`
3. Enter the directory path when prompted

#### Example Output
```
Enter the directory path: C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3
C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3 <DIR> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\application <DIR> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\application\ClientsApplication.java <FICH> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\application\ProductsApplication.java <FICH> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\Client.java <FICH> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\impl <DIR> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\impl\ClientServiceImpl.java <FICH> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\impl\ProductServiceImpl.java <FICH> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\IService.java <FICH> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\Product.java <FICH> rw-
----C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\Storage <DIR> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\Storage\clients.dat <FICH> rw-
--------C:\Users\Otmane\Be-Serious\Learning\ENSET\S1\POO JAVA\poo-java\TP6\Exercice3\Storage\products.dat <FICH> rw-
```

The output shows:
- Full path of each file and directory
- Type indicator (`<DIR>` or `<FICH>`)
- Permission flags (e.g., `rw-`)
- Hierarchical structure using indentation (`----`)
- Recursive listing of all subdirectories and their contents

## Exercise 2: Phone Directory Management System

### Overview
This exercise implements a phone directory management system utilizing the Singleton design pattern. The system demonstrates the practical application of file I/O operations, data persistence, and object-oriented design principles in Java.

### Classes and Implementation Details

#### 1. Contact Class (`Contact.java`)
The foundational data model class representing a contact entity.

##### Class Definition and Constructor
```java
public class Contact {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
```
**Explanation:**
This class implements a basic contact model using encapsulation principles. The private fields ensure data integrity, while the constructor provides a controlled way to initialize contact objects. This design follows the principle of information hiding, a fundamental concept in object-oriented programming.

##### Accessor Methods
```java
public String getName() {
    return name;
}

public String getNumber() {
    return number;
}
```
**Explanation:**
These getter methods provide controlled access to the private fields, implementing encapsulation. They allow read-only access to the contact's properties while maintaining data integrity.

##### String Representation
```java
@Override
public String toString() {
    return String.format("(%s : %s)", name, number);
}
```
**Explanation:**
The overridden toString method provides a formatted string representation of a contact. The format "(name : number)" is chosen for clarity and readability when displaying contact information to users.

#### 2. DossierContact Class (`DossierContact.java`)
The core management class implementing the Singleton pattern for centralized contact handling.

##### Singleton Implementation
```java
private static DossierContact instance = null;
private List<Contact> contacts = new ArrayList<>();

private DossierContact() {
}

public static DossierContact getInstance(File phoneBook) {
    if (instance == null) {
        instance = new DossierContact();
        instance.loadContacts(phoneBook);
    }
    return instance;
}
```
**Explanation:**
This implementation ensures that only one instance of the contact manager exists throughout the application's lifecycle:
- The private constructor prevents direct instantiation
- The static instance field maintains the single instance
- The getInstance method implements lazy initialization
- The contacts list provides in-memory storage of loaded contacts

##### Contact Loading Mechanism
```java
private void loadContacts(File phoneBook) {
    File[] contactFiles = phoneBook.listFiles();
    if (contactFiles != null) {
        for (File file : contactFiles) {
            String name = file.getName();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String number = reader.readLine();
                Contact contact = new Contact(name, number);
                instance.contacts.add(contact);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```
**Explanation:**
This method implements the data loading mechanism:
- Reads all files from the phone book directory
- Each file represents one contact
- The filename serves as the contact's name
- The file's content contains the phone number
- Uses try-with-resources for proper resource management
- Maintains data persistence through file system storage

##### Contact Addition
```java
public boolean addContact(File phoneBook, Contact contact) {
    try {
        File contactFile = new File(phoneBook, contact.getName());
        if (contactFile.exists()) {
            System.out.println("Contact already exists!");
            return false;
        }
        if (contactFile.createNewFile()) {
            try (FileWriter writer = new FileWriter(contactFile)) {
                writer.write(contact.getNumber());
            }
            contacts.add(contact);
            return true;
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return false;
}
```
**Explanation:**
The contact addition process involves several steps:
1. Checks for existing contacts to prevent duplicates
2. Creates a new file for the contact
3. Writes the phone number to the file
4. Updates the in-memory contact list
5. Implements proper error handling
6. Returns success/failure status

##### Contact Deletion
```java
public boolean deleteContact(File phoneBook, Contact contact) {
    File contactToDeleteFile = new File(phoneBook, contact.getName());
    if (contactToDeleteFile.delete()) {
        contacts.remove(contact);
        return true;
    }
    return false;
}
```
**Explanation:**
The deletion process maintains consistency between file system and memory:
- Removes the contact's file from storage
- Updates the in-memory contact list
- Returns operation success status
- Ensures atomic operation (all-or-nothing approach)

##### Contact Update
```java
public boolean updateContact(File phoneBook, Contact oldContact, Contact newContact) {
    File contactToUpdateFile = new File(phoneBook, oldContact.getName());
    try (FileWriter writer = new FileWriter(contactToUpdateFile, false)) {
        writer.write(newContact.getNumber());
        contacts.set(contacts.indexOf(oldContact), newContact);
        return true;
    } catch (IOException e) {
        System.err.println("An error occurred while overwriting the file: " + e.getMessage());
    }
    return false;
}
```
**Explanation:**
The update operation maintains data consistency:
- Overwrites the existing contact file
- Updates the in-memory contact reference
- Uses atomic operation principles
- Implements proper error handling
- Returns operation success status

#### 3. Main Class (`Main.java`)
The user interface and program control class.

##### Menu Display
```java
public static void menu() {
    System.out.println("\nContact Manager - Menu");
    System.out.println("1. Search for a phone number");
    System.out.println("2. Add a new contact");
    System.out.println("3. Delete a contact");
    System.out.println("4. Update a contact's phone number");
    System.out.println("5. Exit the program");
    System.out.print("Please enter your choice: ");
}
```
**Explanation:**
Provides a clear, user-friendly interface:
- Displays available operations
- Uses numbered options for easy selection
- Implements a consistent menu format
- Enhances user experience through clear prompts

##### Directory Initialization
```java
public static File initializeDirectory(String dirPath) {
    File directory = new File(dirPath);
    if (!directory.exists()) {
        if (!directory.mkdir()) {
            System.err.println("Failed to create the directory: " + dirPath);
            return null;
        }
    }
    return directory;
}
```
**Explanation:**
Handles the phone book directory setup:
- Creates directory if it doesn't exist
- Provides proper error handling
- Returns directory reference or null on failure
- Ensures application data persistence

##### Contact Search Implementation
```java
public static List<Contact> handleSearch(List<Contact> contacts, String nameToSearch) {
    return contacts.stream()
            .filter(
                    contact -> contact.getName()
                            .toLowerCase()
                            .contains(nameToSearch.toLowerCase())
            )
            .toList();
}
```
**Explanation:**
Implements an efficient search mechanism:
- Uses Java streams for functional processing
- Performs case-insensitive partial name matching
- Returns all matching contacts
- Provides flexible search capabilities

##### Contact Selection Handler
```java
public static Contact handleOptions(Scanner scanner, List<Contact> contacts, String action) {
    System.out.print("Enter the name of the contact you want to " + action + ": ");
    String nameToSearch = scanner.nextLine().trim();
    List<Contact> contactList = handleSearch(contacts, nameToSearch);
    if (contactList.isEmpty()) {
        System.out.println("No contact with the name '" + nameToSearch + "' to " + action + "!");
    } else {
        System.out.println("The found contacts: ");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(i + 1 + " - " + contactList.get(i));
        }
        int index = -1;
        do {
            System.out.printf(
                    "Enter the number of the contact to %s (1 to %d): ",
                    action,
                    contactList.size()
            );
            try {
                index = scanner.nextInt();
                scanner.nextLine();
                if (index < 1 || index > contactList.size()) {
                    System.out.println("Invalid choice! Please choose a valid contact number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        } while (index < 1 || index > contactList.size());
        return contactList.get(index - 1);
    }
    return null;
}
```
**Explanation:**
Manages contact selection for operations:
- Handles user input for contact selection
- Implements search functionality
- Provides numbered selection interface
- Includes input validation
- Returns selected contact or null

##### Name Validation
```java
public static boolean contactNameRule(String name) {
    Pattern pattern = Pattern.compile("^[a-zA-Z]+[.\\-_]?[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(name);
    return matcher.find();
}
```
**Explanation:**
Implements contact name validation:
- Uses regex pattern matching
- Enforces naming conventions
- Allows specific special characters to prevent errors while creating the file
- Ensures data consistency


**Detailed Explanation of Main Method:**

The main method serves as the program's entry point and implements a comprehensive menu-driven interface for the phone directory system. Let's break down its functionality:

1. **Initialization Phase**
   ```java
   String dirPath = "Exercice2/Phone-Book";
   File phoneBook = initializeDirectory(dirPath);
   DossierContact dossierContact = DossierContact.getInstance(phoneBook);
   ```
   - Sets up the phone book directory path
   - Initializes the directory structure
   - Creates the singleton instance of DossierContact


2. **Main Program Loop**
   ```java
   try (Scanner scanner = new Scanner(System.in)) {
        boolean inRunMode = true;
        while (inRunMode) {
            menu();
            String choice = scanner.nextLine().trim();
            List<Contact> contacts = dossierContact.getContacts();
            // Operations
        }
   }
   ```
   - Implements a continuous operation cycle using a while loop
   - Maintains program state through `inRunMode` boolean
   - Load contacts from memory storage
   - Uses try-with-resources for proper Scanner management


3. **Menu Operations**
   Each case in the switch statement handles a specific operation:

   a) **Search Operation (Case "1")**
    ````java
    case "1":
        System.out.print("Enter the name of the contact you want to search: ");
        String nameToSearch = scanner.nextLine().trim();
        List<Contact> resultContacts = handleSearch(contacts, nameToSearch);
        if (resultContacts.isEmpty()) {
            System.out.println("No contact was found with the name " + nameToSearch + "!");
        } else {
            resultContacts.forEach(System.out::println);
        }
        break;  
    ````
   - Takes user input for contact name
   - Performs case-insensitive search
   - Displays all matching contacts
   - Handles the case of no matches found

   b) **Add Operation (Case "2")**
    ````java
    case "2":
        String name;
        boolean isNameValid;
        do {
            System.out.print("Enter the name of the contact: ");
            name = scanner.nextLine().trim();
            isNameValid = contactNameRule(name);
            if (!isNameValid) {
                System.out.println("Invalid name. Names should follow the rules: e.g., no special characters except of '-', '_', or '.' in the middle!");
            }
        } while (!isNameValid);
        System.out.print("Enter the number of the contact " + name + ": ");
        String number = scanner.nextLine().trim();
        Contact contact = new Contact(name, number);
        boolean isCreated = dossierContact.addContact(phoneBook, contact);
        System.out.println(isCreated ? "Contact added successfully" : "Failed to add contact.");
        break;
    ````
   - Implements input validation loop for contact name
   - Collects phone number information
   - Creates and stores new contact
   - Provides operation success/failure feedback

   c) **Delete Operation (Case "3")**
    ````java
        case "3":
            Contact contactToDelete = handleOptions(scanner, contacts, "delete");
            if (contactToDelete != null) {
                boolean isDeleted = dossierContact.deleteContact(phoneBook, contactToDelete);
                System.out.println(
                        isDeleted ?
                        "Contact deleted successfully" :
                        "Something went wrong!"
                );
            }
            break;
    ````
   - Uses handleOptions for contact selection
   - Performs deletion if contact exists
   - Provides operation status feedback

   d) **Update Operation (Case "4")**
    ````java
        case "4":
            Contact contactToUpdate = handleOptions(scanner, contacts, "update");
            if (contactToUpdate != null) {
                System.out.print("Enter the new number: ");
                String newNumber = scanner.nextLine().trim();
                Contact updatedContact = new Contact(contactToUpdate.getName(), newNumber);
                boolean isUpdated = dossierContact.updateContact(
                        phoneBook, contactToUpdate, updatedContact
                );
                System.out.println(
                        isUpdated ?
                            "Contact updated successfully" :
                            "Something went wrong!"
                );
            }
            break;
    ````
   - Selects contact for update
   - Collects new phone number
   - Updates contact information
   - Confirms operation success/failure

   e) **Exit Operation (Case "5")**
    ````java
        case "5":
            System.out.println("Exiting the program. Goodbye!");
            inRunMode = false;
            break;
    ````
   - Gracefully terminates the program
   - Sets inRunMode to false

### Program Execution

#### Sample Interaction
```
Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 1
Enter the name of the contact you want to search: otmane
(Otmane.TOUHAMI : 0715306675)

Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 2
Enter the name of the contact: John@Doe
Invalid name. Names should follow the rules: e.g., no special characters except of '-', '_', or '.' in the middle!
Enter the name of the contact: John-Doe
Enter the number of the contact John-Doe: 0762534239
Contact added successfully

Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 4
Enter the name of the contact you want to update: john
The found contacts: 
1 - (John-Doe : 0762534239)
Enter the number of the contact to update (1 to 1): 1
Enter the new number: 0765432178
Contact updated successfully

Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 1
Enter the name of the contact you want to search: john
(John-Doe : 0765432178)

Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 3
Enter the name of the contact you want to delete: joh
The found contacts: 
1 - (John-Doe : 0765432178)
Enter the number of the contact to delete (1 to 1): 1
Contact deleted successfully

Contact Manager - Menu
1. Search for a phone number
2. Add a new contact
3. Delete a contact
4. Update a contact's phone number
5. Exit the program
Please enter your choice: 5
Exiting the program. Goodbye!
```

### Design Features and Implementation Notes
1. **Singleton Pattern**: Ensures centralized contact management
2. **File-Based Persistence**: Each contact stored as individual file
3. **Data Validation**: Comprehensive input checking
4. **Error Handling**: Robust exception management
5. **User Interface**: Interactive command-line interface
6. **Search Capability**: Flexible name-based search
7. **Data Integrity**: Consistent file and memory state



4. **Error Handling**
   - Input validation at each step
   - Proper resource management
   - Comprehensive error messages
   - Graceful error recovery

5. **User Interface Features**
   - Clear prompts for user input
   - Consistent feedback messages
   - Input validation with helpful error messages
   - Smooth navigation between operations

## Exercise 3: Generic Service Implementation with File Persistence

### Overview
This exercise implements a comprehensive product and client management system using Java generics and file-based persistence. The system demonstrates advanced Java concepts including generic interfaces, serialization, and object-oriented design patterns.

### Core Components

#### 1. Generic Service Interface (`IService.java`)
The foundation of our system, providing a generic contract for basic CRUD operations.

```java
public interface IService<T> {
    List<T> getAll();
    T add(T object);
    boolean delete(String name);
    T findByName(String name);
    void saveAll();
}
```

**Explanation:**
This generic interface defines the core operations that any service must implement:
- `getAll()`: Retrieves all entities from storage
- `add(T object)`: Adds a new entity to the system
- `delete(String name)`: Removes an entity by name
- `findByName(String name)`: Searches for an entity by name
- `saveAll()`: Persists all entities to storage

The use of generics (`<T>`) allows this interface to work with any type, making it highly reusable.

#### 2. Product Management Implementation

##### Product Class (`Product.java`)
```java
public class Product implements Serializable {
    private String name;
    private String brand;
    private float price;
    private String description;
    private int stockQuantity;

    public Product(String name, String brand, float price, 
                  String description, int stockQuantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters
}
```

Key features:
- Implements `Serializable` for file persistence
- Encapsulates product data with private fields
- Provides controlled access through getters and setters
- Includes comprehensive product attributes

##### Product Service Implementation (`ProductServiceImpl.java`)
```java
public class ProductServiceImpl implements IService<Product> {
   private List<Product> products = new ArrayList<>();
   private final File productFile = new File("Exercice3/Storage/products.dat");
}
```

**Key Methods:**

1. **Get All Products**
```java
@Override
public List<Product> getAll() {
    if (productFile.length() == 0) {
        return products;
    }
    try (ObjectInputStream objectInputStream = 
             new ObjectInputStream(new FileInputStream(productFile))) {
        products = (List<Product>) objectInputStream.readObject();
        return products;
    } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException("Error loading products: " + e.getMessage(), e);
    }
}
```
**Explanation:**
- Checks if the storage file is empty
- Uses object serialization for loading data
- Implements proper resource management with try-with-resources
- Handles potential exceptions appropriately

2. **Add Product**
```java
@Override
public Product add(Product product) {
    if (product != null) {
        products.add(product);
    }
    return product;
}
```
**Explanation:**
- Validates input to prevent null products
- Adds product to in-memory list
- Returns the added product for confirmation

3. **Delete Product**
```java
@Override
public boolean delete(String name) {
    if (name == null) {
        throw new IllegalArgumentException("Name cannot be null.");
    }
    return products.removeIf(product -> 
        product.getName().equalsIgnoreCase(name));
}
```
**Explanation:**
- Validates input name
- Use List `removeIf` for efficient removal
- Case-insensitive name comparison
- Returns success/failure status

4. **Find Product by Name**
```java
@Override
public Product findByName(String name) {
    if (name == null) {
        throw new IllegalArgumentException("Name cannot be null.");
    }
    return products.stream()
            .filter(product -> product.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
}
```
**Explanation:**
- Implements efficient search using streams
- Case-insensitive name matching
- Returns first matching product or null
- Input validation for null safety

5. **Save All Products**
```java
@Override
public void saveAll() {
    try (ObjectOutputStream objectOutputStream = 
             new ObjectOutputStream(new FileOutputStream(productFile))) {
        objectOutputStream.writeObject(products);
    } catch (IOException e) {
        throw new RuntimeException("Error saving products: " + e.getMessage(), e);
    }
}
```
**Explanation:**
- Uses object serialization for persistence
- Implements proper resource management
- Handles I/O exceptions appropriately

#### 3. Client Management Implementation

Similar structure to Product Management, but with client-specific fields and validation.

##### Client Class (`Client.java`)
```java
public class Client implements Serializable {
    private String lastName;
    private String firstName;
    private String address;
    private String phone;
    private String email;
    
    // Constructor and methods similar to Product class
}
```

##### Client Service Implementation (`ClientServiceImpl.java`)
Implements the same `IService` interface but with client-specific logic.

### Main Application Implementation

#### Product Management Application
```java
public class ProductsApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductServiceImpl productService = new ProductServiceImpl();

        try {
            productService.getAll();
        } catch (Exception e) {
            System.out.println("Error loading product data: " + e.getMessage());
        }

        boolean inRunMode = true;
        while (inRunMode) {
            menu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    // Display all products
                    System.out.println("===== All Our Products =====");
                    List<Product> products = productService.getProducts();
                    if (products.isEmpty()) {
                        System.out.println("No product was found!");
                    } else {
                        products.forEach(System.out::println);
                    }
                }
                case "2" -> {
                    // Search product
                    System.out.println("===== Search a Product Process =====");
                    String productNameToSearch = nameValidation(scanner);
                    Product resultProduct = productService.findByName(productNameToSearch);
                    System.out.println(
                            (resultProduct != null) ?
                                    resultProduct :
                                    String.format("There is no product with the name '%s'!", 
                                                productNameToSearch)
                    );
                }
                case "3" -> {
                    // Add product
                    System.out.println("===== Add a Product Process =====");
                    productService.add(productAssembler(scanner));
                    System.out.println("Product added successfully");
                }
                case "4" -> {
                    // Delete product
                    System.out.println("===== Delete a Product Process =====");
                    String productNameToDelete = nameValidation(scanner);
                    System.out.println(
                            productService.delete(productNameToDelete) ?
                                    "Product was deleted successfully" :
                                    String.format("No product was found with the name '%s'!", 
                                                productNameToDelete)
                    );
                }
                case "5" -> {
                    // Save all products
                    System.out.println("===== Save All Product Database Process =====");
                    productService.saveAll();
                    System.out.println("All products was saved successfully");
                }
                case "6" -> {
                    System.out.println("Exiting the program. Goodbye!");
                    inRunMode = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
```

**Main Method Explanation:**

1. **Initialization Phase**
   ```java
   Scanner scanner = new Scanner(System.in);
   ProductServiceImpl productService = new ProductServiceImpl();
   ```
   - Creates scanner for user input
   - Initializes product service
   - Loads existing products from storage

2. **Main Program Loop**
   - Displays menu options
   - Processes user input
   - Handles different operations based on choice
   - Maintains program state

3. **Operation Handling**
   - Case 1: Display all products
   - Case 2: Search for a product
   - Case 3: Add new product
   - Case 4: Delete existing product
   - Case 5: Save to database
   - Case 6: Exit program

4. **Error Handling**
   - Input validation
   - Exception management
   - User feedback
   - Resource cleanup

### Sample Interactions

#### Product Management
```
===== Product Management Menu =====
1. Display the list of products
2. Search for a product by name
3. Add a new product
4. Delete a product by name
5. Save product database
6. Quit
Please enter your choice: 3

===== Add a Product Process =====
Enter the information about the product:
Name: MacBook Pro
Brand: Apple
Price: 1299.99
Description: 14-inch Laptop with M1 Pro
Stock quantity: 50
Product added successfully
```

#### Client Management
```
===== Client Management Menu =====
1. Display all clients
2. Search for a client
3. Add a new client
4. Delete a client
5. Save client database
6. Quit
Please enter your choice: 3

===== Add a Client Process =====
Enter the information about the client:
Last Name: TOUHAMI
First Name: Otmane
Address: Casablanca
Phone number: 061539876
Email: Otmane.touhami@email.com
Client added successfully
```

### Technical Implementation Details

1. **File Storage**
   - Binary serialization for data persistence
   - Error recovery mechanisms
   - Resource management

2. **Data Validation**
   - Input validation for all fields
   - Type checking
   - Null safety
   - Format verification

3. **Error Handling**
   - Exception management
   - User-friendly messages
   - Resource cleanup
   - Recovery procedures


## Conclusion
This laboratory project successfully demonstrates the implementation of advanced Java programming concepts including generics, file I/O operations, and object serialization. Through three comprehensive exercises, we've built robust solutions for directory management, phone directory operations, and a generic service implementation.

Key achievements include:
- Implementation of type-safe generic interfaces
- Efficient file-based data persistence
- Robust error handling and validation
- Clean and maintainable code structure
- Practical application of design patterns

The project serves as a solid foundation for understanding and implementing enterprise-level Java applications with emphasis on code reusability, maintainability, and type safety.
