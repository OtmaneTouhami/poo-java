package Streams.Exercice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WordsManipulator {
    public static void main(String[] args) {
        List<String> wordsList = new ArrayList<>(
                List.of(
                        "apple", "banana", "cherry", "date", "elderberry",
                        "fig", "grape", "honeydew", "kiwi", "lemon",
                        "mango", "nectarine", "orange", "papaya", "quince"
                )
        );

        // Words containing 'a'
        List<String> wordsWithA = wordsList.stream()
                .filter(word -> word.contains("a"))
                .toList();
        System.out.println(
                wordsWithA.isEmpty() ?
                        "No words found containing 'a'." :
                        "Words containing 'a': " + wordsWithA
        );

        // Reversed words with length greater than 3
        List<String> reversedWords = wordsList.stream()
                .filter(word -> word.length() > 3)
                .map(word -> new StringBuilder(word).reverse().toString())
                .toList();
        System.out.println("Reversed words with more than 3 characters: " + reversedWords);

        // Flattened characters from words containing 'e'
        List<String> flattenedWordsWithE = wordsList.stream()
                .filter(word -> word.contains("e"))
                .flatMap(word -> Arrays.stream(word.split("")))
                .toList();
        System.out.println("Flattened characters from words containing 'e': " + flattenedWordsWithE);

        // Words in uppercase
        List<String> upperCaseWords = wordsList.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Words in uppercase: " + upperCaseWords);

        // Length of each word
        List<Integer> wordsLength = wordsList.stream()
                .map(String::length)
                .toList();
        System.out.println("Length of each word: " + wordsLength);

        // Flattened characters from all words
        List<Character> flattenedWord = wordsList.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .toList();
        System.out.println("Flattened characters from all words: " + flattenedWord);

        // Words with their respective index
        List<String> wordIndex = IntStream.range(0, wordsList.size())
                .mapToObj(index -> wordsList.get(index) + " - " + index)
                .toList();
        System.out.println("Words with their respective index: " + wordIndex);
    }
}
