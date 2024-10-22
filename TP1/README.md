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

## Exercise 4: Letter Frequency Analysis
### Features
- Case-insensitive letter frequency counter
- Input limitation to 100 characters
- Display of occurring letters only

### Technical Details
- 26-position array for occurrence storage
- Optimized counting algorithm
- Clear result presentation

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