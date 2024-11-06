# Lab Report - Exercise 1: TropViteException


## Description

In this exercise, I implemented a custom exception class, TropViteException, along with a Vehicle class that uses this exception to handle cases where speed exceeds a certain threshold. The objective was to throw an exception when the speed exceeds 90 km/h and demonstrate exception handling by displaying the call stack.

## 1. TropViteException Class

The `TropViteException` class inherits from the Exception class and represents a custom exception that is triggered when a speed is excessive.

### Code

```java
package Exercise1;

public class TropViteException extends Exception {
    public TropViteException(int speed) {
        super("C'est une exception de type TropViteException. Vitesse en cause : " + speed);
    }
}
```

### Implementation Details

* **Inheritance**: `TropViteException` inherits from Exception, making it a checked exception. This means it must be handled with a try-catch block or declared in the method signature.
* **Constructor**: The `TropViteException` constructor takes an integer (speed) as a parameter, representing the speed. It calls the Exception constructor via super(), passing a message describing the exception with the speed that triggered it.
* **Message**: The message passed to super is "C'est une exception de type `TropViteException`. Vitesse en cause : " + speed, which provides information about what caused the exception when it's displayed.

## 2. Vehicule Class

The `Vehicule` class contains no attributes and has a speed testing method. It handles speed limit verification and triggers `TropViteException` if this limit is exceeded.

### Code

```java
package Exercice1;

public class Vehicule {

    public Vehicule() {
    }

    public void testVitesse(int speed) throws TropViteException {
        if (speed > 90) throw new TropViteException(speed);
    }
    
    // main method here
}
```

### Implementation Details

* **Empty Constructor**: The `Vehicule` constructor is empty and contains no initialization as there are no attributes in this class.

* **testSpeed Method**: This method takes an integer speed as a parameter. If the speed is greater than 90, it throws a TooFastException with the offending speed.
    * The condition `if (speed > 90)` checks if the speed is excessive.
    * The statement `throw new TropViteException(speed);` creates and throws a new instance of `TropViteException`, passing the speed to the constructor.

* **Main Method**:
```java
public static void main(String[] args) {
        Vehicule v = new Vehicule();
        try {
            v.testVitesse(66);
            v.testVitesse(120);
        } catch (TropViteException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
``` 
  * `Vehicule` Object Creation: A `Vehicule` object v is created.
  * testSpeed Calls: The testSpeed method is called with two values: 66 (below 90, so no exception is thrown) and 120 (above 90, which triggers `TropViteException`).
  * Exception Handling: A try-catch block is used to handle `TropViteException`. If the exception is thrown, the associated message is displayed via `System.out.println(e.getMessage());` and show the stack trace with `e.printStackTrace();`.

## 3. Execution and Results

When the program is executed:

1. The first call `v.testSpeed(66);` doesn't exceed the speed limit (90), so nothing happens.
2. The second call `v.testSpeed(120);` exceeds the limit, which triggers `TropViteException`. The exception is caught in the catch block, and the message is displayed: "C'est une exception de type `TropViteException`. Vitesse en cause : 120". And The exception call stack is displayed, showing the methods and lines of code that led to the exception.

### Test Case
```
C'est une exception de type TropViteException. Vitesse en cause : 120
Exercice1.TropViteException: C'est une exception de type TropViteException. Vitesse en cause : 120
	at Exercice1.Vehicule.testVitesse(Vehicule.java:9)
	at Exercice1.Vehicule.main(Vehicule.java:16)
```
## Conclusion

Through this exercise, I demonstrated the creation and usage of a custom exception in Java, allowing for the handling of specific cases (in this instance, excessive speed). By triggering TooFastException when the speed is too high, the Vehicle class incorporates a control to prevent unacceptable speed values, illustrating object-oriented programming principles and exception handling concepts.