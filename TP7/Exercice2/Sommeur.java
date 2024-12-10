package Exercice2;

import java.util.List;

public class Sommeur implements Runnable{
    private List<Integer> numbers;
    private int begin;
    private int end;
    private int sum;

    public Sommeur(List<Integer> numbers, int begin, int end) {
        this.numbers = numbers;
        this.begin = begin;
        this.end = end;
    }

    public int getSomme() {
        return sum;
    }

    @Override
    public void run() {
        sum = numbers.subList(begin, end + 1)
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
