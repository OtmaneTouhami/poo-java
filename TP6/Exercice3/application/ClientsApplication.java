package Exercice3.application;

import Exercice3.Client;
import Exercice3.impl.ClientServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ClientsApplication {

    public static void menu() {
        System.out.println("\n===== Client Management Menu =====");
        System.out.println("1. Display the list of clients");
        System.out.println("2. Search for a client by last name");
        System.out.println("3. Add a new client");
        System.out.println("4. Delete a client by last name");
        System.out.println("5. Save clients to database");
        System.out.println("6. Quit");
        System.out.print("Please enter your choice: ");
    }

    public static String lastNameValidation(Scanner scanner) {
        String lastName = "";
        while (lastName.isEmpty()) {
            System.out.print("Last Name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Client last name cannot be empty. Please enter a valid last name.");
            }
        }
        return lastName;
    }

    public static Client clientAssembler(Scanner scanner) {
        System.out.println("Enter the information about the client:");
        
        String lastName = lastNameValidation(scanner);
        
        String firstName = "";
        while (firstName.isEmpty()) {
            System.out.print("First Name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("Client first name cannot be empty. Please enter a valid first name.");
            }
        }
        
        String address = "";
        while (address.isEmpty()) {
            System.out.print("Address: ");
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Client address cannot be empty. Please enter a valid address.");
            }
        }

        String phone = "";
        while (phone.isEmpty()) {
            System.out.print("Phone number: ");
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Client phone number cannot be empty. Please enter a valid phone.");
            }
        }

        String email = "";
        while (email.isEmpty()) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Client email cannot be empty. Please enter a valid email.");
            }
        }
        
        return new Client(lastName, firstName, address, phone, email);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientServiceImpl clientService = new ClientServiceImpl();

        try {
            clientService.getAll();
        } catch (Exception e) {
            System.out.println("Error loading clients data: " + e.getMessage());
        }

        String choice;
        boolean inRunMode = true;

        while (inRunMode) {
            menu();
            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("===== All Our Clients =====");
                    List<Client> clients = clientService.getClients();
                    if (clients.isEmpty()) {
                        System.out.println("No client was found!");
                    } else {
                        clients.forEach(System.out::println);
                    }
                    break;
                case "2":
                    System.out.println("===== Search a Client Process =====");
                    String clientLasNameToSearch = lastNameValidation(scanner);
                    Client resultClient = clientService.findByName(clientLasNameToSearch);
                    System.out.println(
                            (resultClient != null) ?
                                    resultClient :
                                    String.format("There is no client with the last name '%s'!", clientLasNameToSearch)
                    );
                    break;
                case "3":
                    System.out.println("===== Add a Client Process =====");
                    clientService.add(clientAssembler(scanner));
                    System.out.println("Client added successfully");
                    break;
                case "4":
                    System.out.println("===== Delete a Client Process =====");
                    String clientLastNameToDelete = lastNameValidation(scanner);
                    System.out.println(
                            clientService.delete(clientLastNameToDelete) ?
                                    "Client was deleted successfully" :
                                    String.format("No client was found with the last name '%s'!", clientLastNameToDelete)
                    );
                    break;
                case "5":
                    System.out.println("===== Save All Client Database Process =====");
                    clientService.saveAll();
                    System.out.println("All clients was saved successfully");
                    break;
                case "6":
                    System.out.println("Exiting the program. Goodbye!");
                    inRunMode = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
