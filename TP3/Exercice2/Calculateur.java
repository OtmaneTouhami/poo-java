package Exercice2;

import Exercice1.TropViteException;

public class Calculateur {

    public Calculateur() {
    }

    public void testRacineCarree(int n) throws RacineCarreeException {
        if (n < 0) throw new RacineCarreeException(n);
    }

    public static void main(String[] args) {
        Calculateur c = new Calculateur();
        try {
            c.testRacineCarree(-5);
            c.testRacineCarree(25);
        } catch (RacineCarreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
