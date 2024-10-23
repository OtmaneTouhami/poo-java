import java.util.Scanner;

public class Exercice2 {

    // Méthode pour afficher une ligne avec le pronom, le radical, et la terminaison
    public static void printLine(String pronoun, String root, String end) {
        System.out.println(pronoun + " " + root + end);
    }

    public static void main(String[] args) {
        // Affichage du but du programme
        System.out.println("Conjuguer un verbe régulier au présent");

        // Initialisation du scanner pour la saisie du verbe
        Scanner scanner = new Scanner(System.in);
        String verb;

        // Boucle pour vérifier que le verbe saisi est correct
        do {
            System.out.print("Entrer un verbe régulier: ");
            verb = scanner.nextLine().trim();
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
        String[] pronoms = {"Je", "Tu", "Il/Elle/On", "Nous", "Vous", "Ils/Elles"};
        String[] terminaisons = {"e", "es", "e", "ons", "ez", "ent"};

        // Conjugaison du verbe au présent
        for (int i = 0; i < pronoms.length; i++) {
            char firstLetter = verbRoot.charAt(0);
            char lastLetterOnRoot = verbRoot.charAt(verbRoot.length() - 1);
            String lastTwoLetterOnRoot = verbRoot.substring(verbRoot.length() - 2);

            // Gérer les cas spéciaux pour certains verbes réguliers
            if ((lastTwoLetterOnRoot.equalsIgnoreCase("el") || lastTwoLetterOnRoot.equalsIgnoreCase("et")) && (i != 4 && i != 3)) {
                printLine(pronoms[i], verbRoot + lastLetterOnRoot, terminaisons[i]);
            } else if (lastLetterOnRoot == 'g' && i == 3) {
                printLine(pronoms[i], verbRoot + "e", terminaisons[i]);
            } else if (lastLetterOnRoot == 'c' && i == 3) {
                printLine(pronoms[i], verbRoot.substring(0, verbRoot.length() - 1) + "ç" , terminaisons[i] );
            } else if (lastLetterOnRoot == 'y' && (i == 0 || i == 1 || i == 2 || i == 5)) {
                printLine(pronoms[i], verbRoot.substring(0, verbRoot.length() - 1) + "i", terminaisons[i] );
            } else if ("aeiouh".contains(String.valueOf(firstLetter)) && i == 0) {
                printLine("j'", verbRoot, terminaisons[i] );
            } else {
                printLine(pronoms[i], verbRoot, terminaisons[i]);
            }
        }
    }
}
