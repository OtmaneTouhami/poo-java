import java.util.Arrays;
import java.util.Scanner;

public class NoteManager {

    private float[] notes;

    public void add(int nbNotes) {
        notes = new float[nbNotes];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < nbNotes; i++) {
            System.out.print("Entrez la note numero " + (i + 1) + ": ");
            float note = sc.nextFloat();
            notes[i] = note;
        }
    }

    public float average() {
        float sum = 0;
        for (float n : notes) {
            sum += n;
        }
        return sum / notes.length;
    }

    public void displayMinMax() {
        float min = notes[0];
        float max = notes[0];
        for (float n : notes) {
            if (n < min) {
                min = n;
            }
            if (n > max) {
                max = n;
            }
        }
        System.out.println("La note minimale est: " + min);
        System.out.println("La note maximale est: " + max);
    }

    public void sortedDisplay() {
        float[] sortedNotes = Arrays.copyOf(notes, notes.length);
        Arrays.sort(sortedNotes);
        System.out.println("Les notes de vos etudiants en ordre croissant: ");
        for (float i : sortedNotes) {
            System.out.println(i);
        }
    }

    public void noteCount(float note) {
        int count = 0;
        for (float n : notes) {
            if (n == note) {
                count++;
            }
        }
        System.out.println("Le nombre d'etudiants ayant eu la note " + note + " est: " + count);
    }
}
