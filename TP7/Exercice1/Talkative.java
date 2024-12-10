package Exercice1;

import java.util.List;

public class Talkative implements Runnable {
    private static int number;

    public Talkative(int number) {
        Talkative.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        Thread task1 = new Thread(new Talkative(1));
        Thread task2 = new Thread(new Talkative(2));
        Thread task3 = new Thread(new Talkative(3));
        Thread task4 = new Thread(new Talkative(4));
        Thread task5 = new Thread(new Talkative(5));
        Thread task6 = new Thread(new Talkative(6));
        Thread task7 = new Thread(new Talkative(7));
        Thread task8 = new Thread(new Talkative(8));
        Thread task9 = new Thread(new Talkative(9));
        Thread task10 = new Thread(new Talkative(10));

        List<Thread> threadList = List.of(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10);

        threadList.forEach(Thread::start);
    }
}
