import java.util.Scanner;

public class Exercice2 {
    public static void main(String[] args) {
        // Affichage du but du programme
        System.out.println("Conjuguer un verbe régulier au présent");

        // Initialisation du scanner pour la saisie du verbe
        Scanner scanner = new Scanner(System.in);
        String verb;

        // Boucle pour vérifier que le verbe saisi est correct
        do {
            System.out.print("Entrer un verbe régulier: ");
            verb = scanner.nextLine();
            // Vérification que le verbe se termine par "er"
            String terminaison = verb.substring(verb.length() - 2);

            // Si la terminaison est "er" et que le verbe n'est pas "aller", on sort de la boucle
            if (terminaison.equalsIgnoreCase("er") && !verb.equalsIgnoreCase("aller")) {
                break;
            } else {
                // Si le verbe est "aller", un message d'erreur est affiché
                if (verb.equalsIgnoreCase("aller")) {
                    System.out.println("Le verbe 'aller' est irrégulier");
                } else {
                    // Si le verbe ne se termine pas par "er", on demande un autre verbe
                    System.out.println("Le verbe doit se terminer par 'er'");
                }
            }
        } while (true);

        // Extraction du radical du verbe (sans la terminaison "er")
        String verbRoot = verb.substring(0, verb.length() - 2);

        // Conjugaison du verbe au présent
        System.out.println("Je " + verbRoot + "e");
        System.out.println("Tu " + verbRoot + "es");
        System.out.println("Il/Elle/On " + verbRoot + "e");
        System.out.println("Nous " + verbRoot + "ons");
        System.out.println("Vous " + verbRoot + "ez");
        System.out.println("Ils/Elles " + verbRoot + "ent");
    }
}
