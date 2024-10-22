import java.util.Scanner;

public class Exercice3 {

    // Méthode pour afficher le menu
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1. Entrez un nouveau message");
        System.out.println("2. Afficher un message");
        System.out.println("3. Inverser le message");
        System.out.println("4. Nombre de mots dans le message");
        System.out.println("0. Quitter");
    }

    public static void main(String[] args) {
        String message = "";
        Scanner scanner = new Scanner(System.in);

        do {
            // Afficher le menu
            menu();
            System.out.print("Choix: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Entrez un message: ");
                    message = scanner.nextLine();
                    break;
                case 2:
                    // Vérification pour éviter d'afficher un message vide
                    if (message.isEmpty()) {
                        System.out.println("Aucun message à afficher.");
                    } else {
                        StringManipulator.display(message);
                    }
                    break;
                case 3:
                    if (message.isEmpty()) {
                        System.out.println("Aucun message à inverser.");
                    } else {
                        System.out.println("Message inversé: " + StringManipulator.reverse(message));
                    }
                    break;
                case 4:
                    if (message.isEmpty()) {
                        System.out.println("Aucun message pour compter les mots.");
                    } else {
                        System.out.println("Nombre de mots: " + StringManipulator.wordNumber(message));
                    }
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    // Quitter le programme
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
            System.out.println("Frappez une touche pour revenir au menu");
            // Pause avant de revenir au menu
            scanner.nextLine();
        } while (true);
    }
}
