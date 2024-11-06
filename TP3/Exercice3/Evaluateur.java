package Exercice3;

public class Evaluateur {

    public Evaluateur() {
    }

    public void verifierNote(int note) throws NoteInvalideException {
        if (note < 0 || note > 20) throw new NoteInvalideException(note);
    }

    public static void main(String[] args) {
        Evaluateur evaluateur = new Evaluateur();
        try {
            evaluateur.verifierNote(15);
            evaluateur.verifierNote(25);
        } catch (NoteInvalideException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
