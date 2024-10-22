import java.util.Scanner;

public class Exercice4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text;
        // Alphabet pour comparer les lettres
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        // Tableau pour stocker le nombre d'occurrences des lettres
        int[] nb_occurrences = new int[26];

        // Demande à l'utilisateur de saisir une ligne de texte (max 100 caractères)
        do {
            System.out.println("Entrez une ligne de texte (max. 100 caractères) :");
            text = sc.nextLine();
            if (text.length() > 100) {
                System.out.println("Le texte ne doit pas dépasser 100 caractères.");
            }
        } while (text.length() > 100); // Répète jusqu'à ce que la longueur du texte soit ≤ 100

        // Parcourt chaque lettre de l'alphabet
        for (char a : alpha.toCharArray()) {
            // Compteur pour chaque lettre
            int counter = 0;

            // Parcourt chaque caractère du texte entré par l'utilisateur
            for (char c : text.toCharArray()) {
                // Compare la lettre courante en minuscule et majuscule
                if (c == a || c == Character.toUpperCase(a)) {
                    counter++;
                }
            }
            // Stocke le nombre d'occurrences dans le tableau
            nb_occurrences[alpha.indexOf(a)] = counter;
        }

        // Affiche les résultats pour chaque lettre qui apparaît au moins une fois
        System.out.println("La chaîne " + text + " contient :");
        for (int i = 0; i < nb_occurrences.length; i++) {
            if (nb_occurrences[i] > 0) {
                // Affiche la lettre en majuscule et le nombre de fois qu'elle apparaît
                System.out.println(nb_occurrences[i] + " fois la lettre '" + Character.toUpperCase(alpha.charAt(i)) + "'");
            }
        }
    }
}
