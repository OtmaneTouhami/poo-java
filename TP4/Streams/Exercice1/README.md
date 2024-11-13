# Lab Report - Exercise 1: WordsManipulator

## Description

In this exercise, I implemented a word manipulation system using Java Streams. The objective was to demonstrate various stream operations on a list of words, including filtering, mapping, and flattening.

## 1. WordsManipulator Class

The `WordsManipulator` class contains the main method and demonstrates various stream operations on a list of words.

### Code

```java
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
```

### Implementation Details

**List Initialization**:
```java
List<String> wordsList = new ArrayList<>(
        List.of(
                "apple", "banana", "cherry", "date", "elderberry",
                "fig", "grape", "honeydew", "kiwi", "lemon",
                "mango", "nectarine", "orange", "papaya", "quince"
        )
);
```
* Initializes a list of words.

**Words Containing 'a'**:
```java
List<String> wordsWithA = wordsList.stream()
        .filter(word -> word.contains("a"))
        .toList();
System.out.println(
        wordsWithA.isEmpty() ?
                "No words found containing 'a'." :
                "Words containing 'a': " + wordsWithA
);
```
* Uses `stream()` to create a stream from the list.
* Uses `filter()` to keep only words containing 'a'.
* Collects the filtered words into a list using `toList()`.
* Prints the list of words containing 'a'.

**Reversed Words with Length Greater Than 3**:
```java
List<String> reversedWords = wordsList.stream()
        .filter(word -> word.length() > 3)
        .map(word -> new StringBuilder(word).reverse().toString())
        .toList();
System.out.println("Reversed words with more than 3 characters: " + reversedWords);
```
* Uses `filter()` to keep only words with length greater than 3.
* Uses `map()` to reverse each word.
* Collects the reversed words into a list using `toList()`.
* Prints the list of reversed words.

**Flattened Characters from Words Containing 'e'**:
```java
List<String> flattenedWordsWithE = wordsList.stream()
        .filter(word -> word.contains("e"))
        .flatMap(word -> Arrays.stream(word.split("")))
        .toList();
System.out.println("Flattened characters from words containing 'e': " + flattenedWordsWithE);
```
* Uses `filter()` to keep only words containing 'e'.
* Uses `flatMap()` to split each word into characters and flatten the stream.
* Collects the characters into a list using `toList()`.
* Prints the list of characters.

**Words in Uppercase**:
```java
List<String> upperCaseWords = wordsList.stream()
        .map(String::toUpperCase)
        .toList();
System.out.println("Words in uppercase: " + upperCaseWords);
```
* Uses `map()` to convert each word to uppercase.
* Collects the uppercase words into a list using `toList()`.
* Prints the list of uppercase words.

**Length of Each Word**:
```java
List<Integer> wordsLength = wordsList.stream()
        .map(String::length)
        .toList();
System.out.println("Length of each word: " + wordsLength);
```
* Uses `map()` to get the length of each word.
* Collects the lengths into a list using `toList()`.
* Prints the list of word lengths.

**Flattened Characters from All Words**:
```java
List<Character> flattenedWord = wordsList.stream()
        .flatMap(word -> word.chars().mapToObj(c -> (char) c))
        .toList();
System.out.println("Flattened characters from all words: " + flattenedWord);
```
* Uses `flatMap()` to convert each word to a stream of characters and flatten the stream.
* Collects the characters into a list using `toList()`.
* Prints the list of characters.

**Words with Their Respective Index**:
```java
List<String> wordIndex = IntStream.range(0, wordsList.size())
        .mapToObj(index -> wordsList.get(index) + " - " + index)
        .toList();
System.out.println("Words with their respective index: " + wordIndex);
```
* Uses `IntStream.range()` to create a stream of indices.
* Uses `mapToObj()` to map each index to the corresponding word and its index.
* Collects the words with indices into a list using `toList()`.
* Prints the list of words with their respective indices.

## 2. Execution and Results

When the program is executed:

1. The words containing 'a' are displayed.
2. The reversed words with length greater than 3 are displayed.
3. The flattened characters from words containing 'e' are displayed.
4. The words in uppercase are displayed.
5. The length of each word is displayed.
6. The flattened characters from all words are displayed.
7. The words with their respective index are displayed.

### Test Case
```
Words containing 'a': [apple, banana, date, grape, mango, nectarine, orange, papaya]
Reversed words with more than 3 characters: [elppa, ananab, yrrehc, etad, yrrebredle, eparg, wedyenoh, iwik, nomel, ognam, eniratcen, egnaro, ayapap, ecniuq]
Flattened characters from words containing 'e': [a, p, p, l, e, c, h, e, r, r, y, d, a, t, e, e, l, d, e, r, b, e, r, r, y, g, r, a, p, e, h, o, n, e, y, d, e, w, l, e, m, o, n, n, e, c, t, a, r, i, n, e, o, r, a, n, g, e, q, u, i, n, c, e]
Words in uppercase: [APPLE, BANANA, CHERRY, DATE, ELDERBERRY, FIG, GRAPE, HONEYDEW, KIWI, LEMON, MANGO, NECTARINE, ORANGE, PAPAYA, QUINCE]
Length of each word: [5, 6, 6, 4, 10, 3, 5, 8, 4, 5, 5, 9, 6, 6, 6]
Flattened characters from all words: [a, p, p, l, e, b, a, n, a, n, a, c, h, e, r, r, y, d, a, t, e, e, l, d, e, r, b, e, r, r, y, f, i, g, g, r, a, p, e, h, o, n, e, y, d, e, w, k, i, w, i, l, e, m, o, n, m, a, n, g, o, n, e, c, t, a, r, i, n, e, o, r, a, n, g, e, p, a, p, a, y, a, q, u, i, n, c, e]
Words with their respective index: [apple - 0, banana - 1, cherry - 2, date - 3, elderberry - 4, fig - 5, grape - 6, honeydew - 7, kiwi - 8, lemon - 9, mango - 10, nectarine - 11, orange - 12, papaya - 13, quince - 14]
```

## Conclusion

Through this exercise, I demonstrated various stream operations on a list of words using Java Streams. The `WordsManipulator` class showcases filtering, mapping, and flattening techniques, illustrating fundamental stream manipulation concepts.