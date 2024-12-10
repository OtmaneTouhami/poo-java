package Exercice2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 6, 8, 27, -15, 24, 76, 12, 24, 19, 34, -54);

        Sommeur sommeur1 = new Sommeur(numbers, 0, 3);
        Sommeur sommeur2 = new Sommeur(numbers, 4, 7);
        Sommeur sommeur3 = new Sommeur(numbers, 8, 12);

        Thread task1 = new Thread(sommeur1);
        Thread task2 = new Thread(sommeur2);
        Thread task3 = new Thread(sommeur3);

        task1.start();
        task2.start();
        task3.start();

        try {
            task1.join();
            task2.join();
            task3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int sum = sommeur1.getSomme() + sommeur2.getSomme() + sommeur3.getSomme();
        System.out.printf("The sum is %d", sum);
    }
}
