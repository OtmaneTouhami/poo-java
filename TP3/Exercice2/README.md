# Lab Report - Exercise 2: RacineCarreeException

## Description

This exercise involves creating a custom exception called `RacineCarreeException`, which is thrown when attempting to calculate the square root of a negative number, along with a `Calculateur` class that uses this exception to handle such situations. The objective is to demonstrate error handling in Java using a specific exception for cases where a negative number is provided.

## 1. RacineCarreeException Class

The `RacineCarreeException` class inherits from Exception and is used to indicate that a negative number was provided where it shouldn't be, in this case for a square root calculation.

### Code

```java
package Exercice2;

public class RacineCarreeException extends Exception {
    public RacineCarreeException(int n) {
        super("C'est une exception de type RacineCarreeException. Nombre négatif : " + n);
    }
}
```

### Implementation Details

* **Inheritance**: `RacineCarreeException` inherits from the Exception class, making it a checked exception that must be handled via a try-catch block or declared in the method signature.
* **Constructor**: The `RacineCarreeException` constructor takes an integer `n` as a parameter and passes a detailed message to the Exception constructor via super(). This message includes the integer to indicate which negative number caused the exception.
* **Message**: The message remains in French: `"C'est une exception de type RacineCarreeException. Nombre négatif : " + n`. It specifies the cause of the exception and the negative value in question.

## 2. Calculateur Class

The `Calculateur` class is a simple calculation class that provides a method to test if an integer is positive before attempting a square root calculation.

### Code

```java
package Exercice2;

public class Calculateur {

    public Calculateur() {
    }

    public void testRacineCarree(int n) throws RacineCarreeException {
        if (n < 0) throw new RacineCarreeException(n);
    }
    
     // main method here
}
```

### Implementation Details

* **Empty Constructor**: `Calculateur` has an empty constructor without initialization, as this class has no attributes.

* **testRacineCarree Method**: This method checks if an integer is negative.
    * If the integer `n` is less than 0, the method throws a `RacineCarreeException` with n as a parameter.
    * The condition `if (n < 0)` checks if the number is negative, and if so, the `RacineCarreeException` is triggered with `throw new RacineCarreeException(n);`.

* **Main Method**:
```java
public static void main(String[] args) {
    Calculateur c = new Calculateur();
    try {
        c.testRacineCarree(-5);
        c.testRacineCarree(25);
    } catch (RacineCarreeException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
``` 
  * `Calculateur` Object Creation: A `Calculateur` object `c` is created.
  * testRacineCarree Calls: The `testRacineCarree` method is called with two values: -5 (negative number that triggers the exception) and 25 (positive number that doesn't trigger the exception).
  * Exception Handling: The try-catch block captures the `RacineCarreeException` when the number is negative. The exception message is displayed with `System.out.println(e.getMessage());`, and `e.printStackTrace();` is used to display the complete exception call stack.

## 3. Execution and Results

During execution:

1. First call `c.testRacineCarree(-5);`:
* Since -5 is a negative number, it triggers `RacineCarreeException`.
* The exception is caught in the catch block
* The message `"C'est une exception de type RacineCarreeException. Nombre négatif : -5"` is displayed, followed by the call stack.

2. Second call `c.testRacineCarree(25);`:
* Since 25 is a positive number, there's no issue and the exception isn't triggered.

### Test Case
```
C'est une exception de type RacineCarreeException. Nombre négatif : -5
Exercice2.RacineCarreeException: C'est une exception de type RacineCarreeException. Nombre négatif : -5
	at Exercice2.Calculateur.testRacineCarree(Calculateur.java:11)
	at Exercice2.Calculateur.main(Calculateur.java:17)
```

## Conclusion

This exercise demonstrates the creation of a custom exception and the handling of errors related to incorrect input values (in this case, a negative number for a square root calculation). Managing this situation with `RacineCarreeException` allows for better code structuring by clearly defining abnormal situations. Through this implementation, I've learned how to create and handle custom exceptions in Java, making the code more robust and maintainable.