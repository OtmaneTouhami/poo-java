package Exercice2;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void menu() {
        System.out.println("\nContact Manager - Menu");
        System.out.println("1. Search for a phone number");
        System.out.println("2. Add a new contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. Update a contact's phone number");
        System.out.println("5. Exit the program");
        System.out.print("Please enter your choice: ");
    }

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

    public static List<Contact> handleSearch(List<Contact> contacts, String nameToSearch) {
        return contacts.stream()
                .filter(
                        contact -> contact.getName()
                                .toLowerCase()
                                .contains(nameToSearch.toLowerCase())
                )
                .toList();
    }

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

    public static boolean contactNameRule(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+[.\\-_]?[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static void main(String[] args) {
        String dirPath = "Exercice2/Phone-Book";
        File phoneBook = initializeDirectory(dirPath);

        if (phoneBook != null) {
            try (Scanner scanner = new Scanner(System.in)) {
                DossierContact dossierContact = DossierContact.getInstance(phoneBook);
                boolean inRunMode = true;
                while (inRunMode) {
                    menu();
                    String choice = scanner.nextLine().trim();
                    List<Contact> contacts = dossierContact.getContacts();
                    switch (choice) {
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
                        case "3":
                            Contact contactToDelete = handleOptions(scanner, contacts, "delete");
                            if (contactToDelete != null) {
                                boolean isDeleted = dossierContact
                                        .deleteContact(phoneBook, contactToDelete);
                                System.out.println(
                                        isDeleted ?
                                                "Contact deleted successfully" :
                                                "Something went wrong!"
                                );
                            }
                            break;
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
                        case "5":
                            System.out.println("Exiting the program. Goodbye!");
                            inRunMode = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        } else {
            System.err.println("Failed to initialize the phone book directory. Program exiting.");
        }
    }
}
