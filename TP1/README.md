# Java Arrays and String Manipulation

This folder contains the implementation of four exercises focused on array operations and string manipulation in Java.

## Exercise 1: Student Grade Management
### Features
- Dynamic grade input system
- Statistical operations:
  - Average calculation
  - Minimum and maximum grade display
  - Grade sorting in ascending order
  - Specific grade occurrence counter

### Implementation Details
- Created `NoteManager` class to encapsulate grade management logic
- Used float array for grade storage
- Implemented methods for:
  - Grade input
  - Statistical calculations
  - Sorted display
  - Grade searching and counting

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

## Exercise 2: French Verb Conjugation
### Features
- First group verb conjugation in present tense
- Input validation:
  - Checks for "er" ending
  - Excludes irregular verb "aller"
- Automatic conjugation for all persons

### Technical Details
- Root extraction algorithm
- Error handling for invalid inputs
- User-friendly console interface

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

## Exercise 3: String Manipulation Interface
### Features
Interactive menu with 4 options:
1. New message input
2. Message display
3. Message reversal
4. Word count

### Implementation Details
- `StringManipulator` utility class
- Error handling for empty messages
- Continuous loop interface with exit option
- Space-aware word counting

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

## Exercise 4: Letter Frequency Analysis
### Features
- Case-insensitive letter frequency counter
- Input limitation to 100 characters
- Display of occurring letters only

### Technical Details
- 26-position array for occurrence storage
- Optimized counting algorithm
- Clear result presentation

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

## Technical Requirements
- Java Development Kit (JDK) 8 or higher
- Console-based interface
- No external dependencies

## How to Run
1. Clone the repository
2. Compile the Java files:
   ```bash
   javac *.java
   ```
3. Run any exercise:
   ```bash
   java Exercice1  # For exercise 1
   java Exercice2  # For exercise 2
   java Exercice3  # For exercise 3
   java Exercice4  # For exercise 4
   ```

## Project Structure
```
.
├── Exercice1.java
├── Exercice2.java
├── Exercice3.java
├── Exercice4.java
├── NoteManager.java
├── StringManipulator.java
├── README.md
```

## Conclusion
This practical work demonstrates the fundamental concepts of Java programming, specifically focusing on array manipulation and string operations. Through these exercises, we've implemented various data structures and algorithms while maintaining clean code practices and proper error handling. The project showcases the application of object-oriented programming principles and the development of user-friendly console interfaces.

Key learning outcomes include:
- Array manipulation and sorting algorithms
- String processing and analysis
- Object-oriented design principles
- User input validation and error handling
- Console-based user interface development

This work was completed as part of the Java Programming course at ENSET.