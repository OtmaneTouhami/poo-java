package Exercice1;

public class Vehicule {

    public Vehicule() {
    }

    public void testVitesse(int speed) throws TropViteException {
        if (speed > 90) throw new TropViteException(speed);
    }

    public static void main(String[] args) {
        Vehicule v = new Vehicule();
        try {
            v.testVitesse(66);
            v.testVitesse(120);
        } catch (TropViteException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
