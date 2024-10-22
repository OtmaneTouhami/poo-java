import java.util.Scanner;

public class Exercice1 {
    public static void main(String[] args) {

        NoteManager notes = new NoteManager();
        Scanner sc = new Scanner(System.in);

        // Demande à l'utilisateur d'entrer le nombre total d'étudiants
        System.out.print("Le nombre total de vos etudiants: ");
        int nbStudents = sc.nextInt();
        // Appel à la méthode pour ajouter les notes des étudiants
        notes.add(nbStudents);

        // Affiche la moyenne des notes
        System.out.println("La moyenne de vos etudiants est: " + notes.average());

        // Affiche la note minimale et maximale
        notes.displayMinMax();

        // Affiche les notes triées en ordre croissant
        notes.sortedDisplay();

        // Demande à l'utilisateur de chercher une note spécifique
        System.out.print("Entrez la note que vous voulez chercher: ");
        float note = sc.nextFloat();
        // Compte combien d'étudiants ont obtenu cette note
        notes.noteCount(note);
    }
}
