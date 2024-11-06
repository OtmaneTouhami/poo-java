# Lab Report
# Exercise 1: Student Grade Management

## Description

In this exercise, I implemented a Java program to manage student grades. The program allows users to input grades, calculate statistical data, and perform various operations on the grades. The objective was to create a `NoteManager` class to encapsulate the grade management logic and provide a user-friendly interface for interacting with the grades.

## 1. NoteManager Class

The `NoteManager` class is responsible for managing student grades, including adding grades, calculating the average, finding the minimum and maximum grades, sorting the grades, and counting the occurrences of a specific grade.

### Code

```java
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
```

### Implementation Details

* **Attributes**: The class has an attribute `notes` which is an array of floats to store the grades.
* **Methods**:
  * `add(int nbNotes)`: Prompts the user to input grades and stores them in the `notes` array.
  * `average()`: Calculates and returns the average of the grades.
  * `displayMinMax()`: Finds and displays the minimum and maximum grades.
  * `sortedDisplay()`: Sorts and displays the grades in ascending order.
  * `noteCount(float note)`: Counts and displays the number of occurrences of a specific grade.

## 2. Exercice1 Class

The `Exercice1` class contains the main method to interact with the user and manage student grades using the `NoteManager` class.

### Code

```java
import java.util.Scanner;

public class Exercice1 {
    public static void main(String[] args) {
        NoteManager notes = new NoteManager();
        Scanner sc = new Scanner(System.in);

        System.out.print("Le nombre total de vos etudiants: ");
        int nbStudents = sc.nextInt();
        notes.add(nbStudents);

        System.out.println("La moyenne de vos etudiants est: " + notes.average());
        notes.displayMinMax();
        notes.sortedDisplay();

        System.out.print("Entrez la note que vous voulez chercher: ");
        float note = sc.nextFloat();
        notes.noteCount(note);
    }
}
```

### Implementation Details

* **Main Method**:
  * `NoteManager` Object Creation: A `NoteManager` object `notes` is created.
  * User Interaction: The user is prompted to input the number of students and their grades.
  * Statistical Operations: The program calculates and displays the average, minimum, and maximum grades, sorts and displays the grades, and counts the occurrences of a specific grade.

## 3. Execution and Results

When the program is executed, the user is prompted to input the number of students and their grades. The program then calculates and displays the average, minimum, and maximum grades, sorts and displays the grades, and counts the occurrences of a specific grade.

### Test Case

```
Le nombre total de vos etudiants: 10
Entrez la note numero 1: 12.5
Entrez la note numero 2: 19
Entrez la note numero 3: 11
Entrez la note numero 4: 10.5
Entrez la note numero 5: 17
Entrez la note numero 6: 9.25
Entrez la note numero 7: 16
Entrez la note numero 8: 14.5
Entrez la note numero 9: 11
Entrez la note numero 10: 18

La moyenne de vos etudiants est: 13.875
La note minimale est: 9.25
La note maximale est: 19.0

Les notes de vos etudiants en ordre croissant:
9.25
10.5
11.0
11.0
12.5
14.5
16.0
17.0
18.0
19.0

Entrez la note que vous voulez chercher: 11
Le nombre d'etudiants ayant eu la note 11.0 est: 2
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of a class in Java to manage student grades. By creating instances of `NoteManager` and providing a user-friendly interface for interacting with the grades, the exercise illustrates object-oriented programming principles and class interactions.

# Exercise 2: French Verb Conjugation

## Description

In this exercise, I implemented a Java program to conjugate first group French verbs in the present tense. The program validates the input verb, ensuring it ends with "er" and is not the irregular verb "aller". The objective was to create a user-friendly interface for conjugating regular French verbs.

## 1. Exercice2 Class

The `Exercice2` class contains methods to validate the input verb, extract its root, and conjugate it in the present tense.

### Code

```java
import java.util.Scanner;

public class Exercice2 {
    public static void printLine(String pronoun, String root, String end) {
        System.out.println(pronoun + " " + root + end);
    }

    public static void main(String[] args) {
        System.out.println("Conjuguer un verbe régulier au présent");
        Scanner scanner = new Scanner(System.in);
        String verb;

        do {
            System.out.print("Entrer un verbe régulier: ");
            verb = scanner.nextLine().trim();
            String terminaison = verb.substring(verb.length() - 2);

            if (terminaison.equalsIgnoreCase("er") && !verb.equalsIgnoreCase("aller")) {
                break;
            } else {
                if (verb.equalsIgnoreCase("aller")) {
                    System.out.println("Le verbe 'aller' est irrégulier");
                } else {
                    System.out.println("Le verbe doit se terminer par 'er'");
                }
            }
        } while (true);

        String verbRoot = verb.substring(0, verb.length() - 2);
        String[] pronoms = {"Je", "Tu", "Il/Elle/On", "Nous", "Vous", "Ils/Elles"};
        String[] terminaisons = {"e", "es", "e", "ons", "ez", "ent"};

        for (int i = 0; i < pronoms.length; i++) {
            char firstLetter = verbRoot.charAt(0);
            char lastLetterOnRoot = verbRoot.charAt(verbRoot.length() - 1);
            String lastTwoLetterOnRoot = verbRoot.substring(verbRoot.length() - 2);

            if ((lastTwoLetterOnRoot.equalsIgnoreCase("el") || lastTwoLetterOnRoot.equalsIgnoreCase("et")) && (i != 4 && i != 3)) {
                printLine(pronoms[i], verbRoot + lastLetterOnRoot, terminaisons[i]);
            } else if (lastLetterOnRoot == 'g' && i == 3) {
                printLine(pronoms[i], verbRoot + "e", terminaisons[i]);
            } else if (lastLetterOnRoot == 'c' && i == 3) {
                printLine(pronoms[i], verbRoot.substring(0, verbRoot.length() - 1) + "ç" , terminaisons[i] );
            } else if (lastLetterOnRoot == 'y' && (i == 0 || i == 1 || i == 2 || i == 5)) {
                printLine(pronoms[i], verbRoot.substring(0, verbRoot.length() - 1) + "i", terminaisons[i] );
            } else if ("aeiouh".contains(String.valueOf(firstLetter)) && i == 0) {
                printLine("j'", verbRoot, terminaisons[i] );
            } else {
                printLine(pronoms[i], verbRoot, terminaisons[i]);
            }
        }
    }
}
```

### Implementation Details

### Implementation Details

* **printLine Method**: This method takes three parameters: a pronoun, the root of the verb, and the ending. It constructs a conjugated verb line by concatenating these parameters and prints the result.
* **Main Method**:
  * **Input Validation**: The user is prompted to input a verb. The program checks if the verb ends with \`er\` and is not \`aller\`. If the input is invalid, the user is prompted again until a valid verb is entered.
  * **Root Extraction**: Once a valid verb is entered, the program extracts the root of the verb by removing the last two characters (\`er\`).
  * **Conjugation**: The program iterates over a list of pronouns and their corresponding endings. For each pronoun, it constructs the conjugated form of the verb by appending the appropriate ending to the root and prints the result.
    * **Examples of Endings**:
      * \`Je\` - \`e\`
      * \`Tu\` - \`es\`
      * \`Il/Elle/On\` - \`e\`
      * \`Nous\` - \`ons\`
      * \`Vous\` - \`ez\`
      * \`Ils/Elles\` - \`ent\`
    * **Unusual Forms**:
      * Verbs ending in \`el\` or \`et\`: The last letter is doubled before adding the ending (except for \`nous\` and \`vous\` forms).
      * Verbs ending in \`g\`: An \`e\` is added before the \`ons\` ending in the \`nous\` form.
      * Verbs ending in \`c\`: The \`c\` is changed to \`ç\` before adding the \`ons\` ending in the \`nous\` form.
      * Verbs ending in \`y\`: The \`y\` is changed to \`i\` before adding the endings for \`je\`, \`tu\`, \`il/elle/on\`, and \`ils/elles\` forms.
      * Verbs starting with a vowel or \`h\`: The pronoun \`je\` is contracted to \`j'\`.
## 2. Execution and Results

When the program is executed, the user is prompted to input a regular French verb. The program validates the input and conjugates the verb in the present tense.

### Test Case

```
Conjuguer un verbe régulier au présent
Entrer un verbe régulier: finir
Le verbe doit se terminer par 'er'

Entrer un verbe régulier: aller
Le verbe 'aller' est irrégulier

Entrer un verbe régulier: commancer
Je commance
Tu commances
Il/Elle/On commance
Nous commançons
Vous commancez
Ils/Elles commancent
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of a class in Java to conjugate regular French verbs. By validating the input verb and providing a user-friendly interface for conjugation, the exercise illustrates string manipulation and input validation principles.

# Exercise 3: String Manipulation Interface

## Description

In this exercise, I implemented a Java program to perform various operations on a string input by the user. The program provides a menu-driven interface for the user to input a string, display it, reverse it, and count the number of words. The objective was to create a `StringManipulator` class to encapsulate the string manipulation logic and provide a user-friendly interface.

## 1. StringManipulator Class

The `StringManipulator` class contains methods to display a string, reverse a string, and count the number of words in a string.

### Code

```java
import java.util.Arrays;

public class StringManipulator {
    public static void display(String message) {
        System.out.println(message);
    }

    public static String reverse(String message) {
        StringBuilder reversedMessage = new StringBuilder();
        for (int i = message.length() - 1; i >= 0; i--) {
            reversedMessage.append(message.charAt(i));
        }
        return reversedMessage.toString();
    }

    public static int wordNumber(String message) {
        String trimmedMessage = message.trim();
        String[] words = trimmedMessage.split("\\s+");
        return words.length;
    }
}
```

### Implementation Details

* **display Method**: This method prints the given message.
* **reverse Method**: This method returns the reversed version of the given message.
* **wordNumber Method**: This method returns the number of words in the given message.

## 2. Exercice3 Class

The `Exercice3` class contains the main method to interact with the user and perform string manipulations using the `StringManipulator` class.

### Code

```java
import java.util.Scanner;

public class Exercice3 {
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1. Entrez un nouveau message");
        System.out.println("2. Afficher un message");
        System.out.println("3. Inverser le message");
        System.out.println("4. Nombre de mots dans le message");
        System.out.println("0. Quitter");
    }

    public static void main(String[] args) {
        String message = "";
        Scanner scanner = new Scanner(System.in);

        do {
            menu();
            System.out.print("Choix: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Entrez un message: ");
                    message = scanner.nextLine();
                    break;
                case 2:
                    if (message.isEmpty()) {
                        System.out.println("Aucun message à afficher.");
                    } else {
                        StringManipulator.display(message);
                    }
                    break;
                case 3:
                    if (message.isEmpty()) {
                        System.out.println("Aucun message à inverser.");
                    } else {
                        System.out.println("Message inversé: " + StringManipulator.reverse(message));
                    }
                    break;
                case 4:
                    if (message.isEmpty()) {
                        System.out.println("Aucun message pour compter les mots.");
                    } else {
                        System.out.println("Nombre de mots: " + StringManipulator.wordNumber(message));
                    }
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
            System.out.println("Frappez une touche pour revenir au menu");
            scanner.nextLine();
        } while (true);
    }
}
```

### Implementation Details

* **menu Method**: This method displays the menu options to the user. It prints out the available choices for the user to interact with the program, such as entering a new message, displaying the message, reversing the message, counting the number of words in the message, or quitting the program.
* **Main Method**:
  * **User Interaction**: The user is prompted to choose an option from the menu. Based on the user's choice, the program performs the corresponding action.
  * **String Operations**: The program performs the chosen string operation using the `StringManipulator` class. This includes:
    * **Entering a New Message**: The user can input a new message, which is stored in the `message` variable.
    * **Displaying the Message**: If a message has been entered, it is displayed using the `StringManipulator.display` method.
    * **Reversing the Message**: If a message has been entered, it is reversed using the `StringManipulator.reverse` method, and the reversed message is displayed.
    * **Counting the Number of Words**: If a message has been entered, the number of words in the message is counted using the `StringManipulator.wordNumber` method, and the count is displayed.
    * **Quitting the Program**: The user can choose to quit the program, which will terminate the execution.

## 3. Execution and Results

When the program is executed, the user is presented with a menu to perform various string operations. The user can input a string, display it, reverse it, and count the number of words.

### Test Case

```
Menu
1. Entrez un nouveau message
2. Afficher un message
3. Inverser le message
4. Nombre de mots dans le message
0. Quitter
Choix: 1
Entrez un message: Je m'appele Otmane TOUHAMI
Frappez une touche pour revenir au menu

Menu
1. Entrez un nouveau message
2. Afficher un message
3. Inverser le message
4. Nombre de mots dans le message
0. Quitter
Choix: 2
Je m'appele Otmane TOUHAMI
Frappez une touche pour revenir au menu

Menu
1. Entrez un nouveau message
2. Afficher un message
3. Inverser le message
4. Nombre de mots dans le message
0. Quitter
Choix: 3
Message inversé: IMAHUOT enamtO eleppa'm eJ
Frappez une touche pour revenir au menu

Menu
1. Entrez un nouveau message
2. Afficher un message
3. Inverser le message
4. Nombre de mots dans le message
0. Quitter
Choix: 4
Nombre de mots: 4
Frappez une touche pour revenir au menu

Menu
1. Entrez un nouveau message
2. Afficher un message
3. Inverser le message
4. Nombre de mots dans le message
0. Quitter
Choix: 0
Au revoir!
```

## Conclusion

Through this exercise, I demonstrated the creation and usage of a class in Java to perform various string manipulations. By providing a user-friendly interface for interacting with the string, the exercise illustrates string manipulation and user input handling principles.

# Exercise 4: Letter Frequency Analysis

## Description

In this exercise, I implemented a Java program to count the occurrences of each letter in a given string. The program is case-insensitive and only counts letters from the alphabet. The objective was to create a user-friendly interface for analyzing letter frequencies in a string.

## 1. Exercice4 Class

The `Exercice4` class contains methods to read a string from the user, count the occurrences of each letter, and display the results.

### Code

```java
import java.util.Scanner;

public class Exercice4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text;
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] nb_occurrences = new int[26];

        do {
            System.out.println("Entrez une ligne de texte (max. 100 caractères) :");
            text = sc.nextLine();
            if (text.length() > 100) {
                System.out.println("Le texte ne doit pas dépasser 100 caractères.");
            }
        } while (text.length() > 100);

        for (char a : alpha.toCharArray()) {
            int counter = 0;
            for (char c : text.toCharArray()) {
                if (c == a || c == Character.toUpperCase(a)) {
                    counter++;
                }
            }
            nb_occurrences[alpha.indexOf(a)] = counter;
        }

        System.out.println("La chaîne " + text + " contient :");
        for (int i = 0; i < nb_occurrences.length; i++) {
            if (nb_occurrences[i] > 0) {
                System.out.println(nb_occurrences[i] + " fois la lettre '" + Character.toUpperCase(alpha.charAt(i)) + "'");
            }
        }
    }
}
```

### Implementation Details

* **Main Method**:
  * **Input Validation**: The user is prompted to input a string, which is validated to ensure it does not exceed 100 characters.
  * **Letter Counting**: The program counts the occurrences of each letter in the input string.
  * **Result Display**: The program displays the number of occurrences for each letter that appears at least once.

## 2. Execution and Results

When the program is executed, the user is prompted to input a string. The program counts the occurrences of each letter and displays the results.

### Test Case

```
Entrez une ligne de texte (max. 100 caractères) :
Dans un monde en constante évolution, l'importance de l'éducation et de la technologie ne cesse de croître. Il est essentiel de continuer à apprendre pour rester compétitif et s'adapter aux changements.
Le texte ne doit pas dépasser 100 caractères.

Entrez une ligne de texte (max. 100 caractères) :
Success is the result of hard work and persistence
La chaîne Success is the result of hard work and persistence contient :
2 fois la lettre 'A'
3 fois la lettre 'C'
2 fois la lettre 'D'
6 fois la lettre 'E'
1 fois la lettre 'F'
2 fois la lettre 'H'
2 fois la lettre 'I'
1 fois la lettre 'K'
1 fois la lettre 'L'
2 fois la lettre 'N'
2 fois la lettre 'O'
1 fois la lettre 'P'
4 fois la lettre 'R'
7 fois la lettre 'S'
3 fois la lettre 'T'
2 fois la lettre 'U'
1 fois la lettre 'W'
```
## Conclusion
Through this exercise, I demonstrated the creation and usage of a class in Java to analyze letter frequencies in a string. By providing a user-friendly interface for input and displaying the results, the exercise illustrates string manipulation, array handling, and user input validation principles.

# Lab Conclusion

This practical work demonstrates the fundamental concepts of Java programming, specifically focusing on array manipulation and string operations. Through these exercises, we've implemented various data structures and algorithms while maintaining clean code practices and proper error handling. The project showcases the application of object-oriented programming principles and the development of user-friendly console interfaces.

Key learning outcomes include:
- Array manipulation and sorting algorithms
- String processing and analysis
- Object-oriented design principles
- User input validation and error handling
- Console-based user interface development
