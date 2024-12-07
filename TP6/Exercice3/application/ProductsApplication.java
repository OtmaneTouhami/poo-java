package Exercice3.application;

import Exercice3.Product;
import Exercice3.impl.ProductServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductsApplication {

    public static void menu() {
        System.out.println("\n===== Product Management Menu =====");
        System.out.println("1. Display the list of products");
        System.out.println("2. Search for a product by name");
        System.out.println("3. Add a new product");
        System.out.println("4. Delete a product by name");
        System.out.println("5. Save products to database");
        System.out.println("6. Quit");
        System.out.print("Please enter your choice: ");
    }

    public static String nameValidation(Scanner scanner) {
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Product name cannot be empty. Please enter a valid name.");
            }
        }
        return name;
    }

    public static Product productAssembler(Scanner scanner) {
        System.out.println("Enter the information about the product:");

        String name = nameValidation(scanner);

        String brand = "";
        while (brand.isEmpty()) {
            System.out.print("Brand: ");
            brand = scanner.nextLine().trim();
            if (brand.isEmpty()) {
                System.out.println("Product brand cannot be empty. Please enter a valid name.");
            }
        }

        float price = -1;
        while (price < 0) {
            try {
                System.out.print("Price: ");
                price = scanner.nextFloat();
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        String description = "";
        while (description.isEmpty()) {
            System.out.print("Description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Product description cannot be empty. Please enter a valid name.");
            }
        }

        int stockQuantity = -1;
        while (stockQuantity < 0) {
            try {
                System.out.print("Stock quantity: ");
                stockQuantity = scanner.nextInt();
                if (stockQuantity < 0) {
                    System.out.println("Stock quantity cannot be negative. Please enter a valid stock quantity.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid stock quantity.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        return new Product(name, brand, price, description, stockQuantity);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductServiceImpl productService = new ProductServiceImpl();

        try {
            productService.getAll();
        } catch (Exception e) {
            System.out.println("Error loading product data: " + e.getMessage());
        }

        String choice;
        boolean inRunMode = true;

        while (inRunMode) {
            menu();
            choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("===== All Our Products =====");
                    List<Product> products = productService.getProducts();
                    if (products.isEmpty()) {
                        System.out.println("No product was found!");
                    } else {
                        products.forEach(System.out::println);
                    }
                    break;
                case "2":
                    System.out.println("===== Search a Product Process =====");
                    String productNameToSearch = nameValidation(scanner);
                    Product resultProduct = productService.findByName(productNameToSearch);
                    System.out.println(
                            (resultProduct != null) ?
                                    resultProduct :
                                    String.format("There is no product with the name '%s'!", productNameToSearch)
                    );
                    break;
                case "3":
                    System.out.println("===== Add a Product Process =====");
                    productService.add(productAssembler(scanner));
                    System.out.println("Product added successfully");
                    break;
                case "4":
                    System.out.println("===== Delete a Product Process =====");
                    String productNameToDelete = nameValidation(scanner);
                    System.out.println(
                            productService.delete(productNameToDelete) ?
                                    "Product was deleted successfully" :
                                    String.format("No product was found with the name '%s'!", productNameToDelete)
                    );
                    break;
                case "5":
                    System.out.println("===== Save All Product Database Process =====");
                    productService.saveAll();
                    System.out.println("All products was saved successfully");
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
