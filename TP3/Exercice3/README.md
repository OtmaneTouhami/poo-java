# Lab Report - Exercise 3: NoteInvalideException

## Description

This exercise involves creating a custom exception called `NoteInvalideException`, which is thrown when a grade is invalid, i.e., outside the permitted range (0 to 20). We also implement an `Evaluateur` class that uses this exception to verify grade validity. The objective is to understand how to define and handle specific exceptions in Java.

## 1. NoteInvalideException Class

The `NoteInvalideException` class inherits from `Exception` and represents a custom exception triggered when the provided grade is not within the valid range.

### Code

```java
package Exercice3;

public class NoteInvalideException extends Exception {
    public NoteInvalideException(int note) {
        super("Exception de type NoteInvalideException. Note invalide : " + note);
    }
}
```

### Implementation Details

* **Inheritance**: `NoteInvalideException` inherits from the `Exception` class, making it a checked exception. Therefore, it must be handled with a `try-catch` block or declared in the method signature.
* **Constructor**: The `NoteInvalideException` constructor takes an integer `note` as a parameter. It calls the `Exception` constructor via `super()`, passing a message describing the exception and the invalid grade.
* **Message**: The message remains in French: `"Exception de type NoteInvalideException. Note invalide : " + note`, which indicates why the exception was thrown and which grade caused it.

## 2. Evaluateur Class

The `Evaluateur` class represents an evaluator that checks if a grade is within the permitted range.

### Code

```java
package Exercice3;

public class Evaluateur {

    public Evaluateur() {
    }

    public void verifierNote(int note) throws NoteInvalideException {
        if (note < 0 || note > 20) throw new NoteInvalideException(note);
    }

    public static void main(String[] args) {
        Evaluateur evaluateur = new Evaluateur();
        try {
            evaluateur.verifierNote(15);
            evaluateur.verifierNote(25);
        } catch (NoteInvalideException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### Implementation Details

* **Empty Constructor**: The `Evaluateur` class has an empty constructor as it has no attributes.

* **verifierNote Method**: This method takes an integer `note` as a parameter. It verifies if the grade is valid.
    * If the grade is less than 0 or greater than 20, it throws a `NoteInvalideException` with the grade value.
    * The condition `if (note < 0 || note > 20)` checks if the grade is outside the range [0, 20].
    * If the condition is true, `throw new NoteInvalideException(note);` creates and throws an exception with the faulty grade.

* **Main Method**:
    * **Evaluateur Object Creation**: An `evaluateur` object of type `Evaluateur` is created.
    * **verifierNote Calls**: The `verifierNote` method is called with two values: 15 (within range, so no exception) and 25 (outside range, triggers exception).
    * **Exception Handling**: The `try-catch` block captures `NoteInvalideException` when the grade is out of range. The error message is displayed using `System.out.println(e.getMessage());` and show the stack trace with `e.printStackTrace();`.

## 3. Execution and Results

During execution:

1. **First Call** `evaluateur.verifierNote(15);`:
* Grade 15 is within the valid range [0, 20], so no issue occurs.

2. **Second Call** `evaluateur.verifierNote(25);`:
* Grade 25 is outside the range, which triggers `NoteInvalideException`.
* The message `"Exception de type NoteInvalideException. Note invalide : 25"` is displayed. And The exception call stack is displayed, showing the methods and lines of code that led to the exception.

## Conclusion

This exercise demonstrates how to define a custom exception to handle inappropriate values in a specific context. By throwing `NoteInvalideException` when the grade is outside the permitted range, I've learned how to better structure code to handle specific errors and thus improve application robustness. The implementation shows the practical application of exception handling in real-world scenarios, such as grade validation in an educational system.