package Exercice1;

public class TropViteException extends Exception {
    public TropViteException(int speed) {
        super("C'est une exception de type TropViteException. Vitesse en cause : " + speed);
    }
}
