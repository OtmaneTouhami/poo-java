# Lab Report - Exercise 1: TooFastException

## Description

In this exercise, I implemented a custom exception class, TooFastException, along with a Vehicle class that uses this exception to handle cases where speed exceeds a certain threshold. The objective was to throw an exception when the speed exceeds 90 km/h and demonstrate exception handling by displaying the call stack.

## 1. TooFastException Class

The TooFastException class inherits from the Exception class and represents a custom exception that is triggered when a speed is excessive.

### Code

```java
package Exercise1;

public class TooFastException extends Exception {
    public TooFastException(int speed) {
        super("This is a TooFastException. Speed at fault: " + speed);
    }
}
```

### Implementation Details

* **Inheritance**: TooFastException inherits from Exception, making it a checked exception. This means it must be handled with a try-catch block or declared in the method signature.
* **Constructor**: The TooFastException constructor takes an integer (speed) as a parameter, representing the speed. It calls the Exception constructor via super(), passing a message describing the exception with the speed that triggered it.
* **Message**: The message passed to super is "This is a TooFastException. Speed at fault: " + speed, which provides information about what caused the exception when it's displayed.

## 2. Vehicle Class

The Vehicle class contains no attributes and has a speed testing method. It handles speed limit verification and triggers TooFastException if this limit is exceeded.

### Code

```java
package Exercise1;

public class Vehicle {
    public Vehicle() {
    }

    public void testSpeed(int speed) throws TooFastException {
        if (speed > 90) throw new TooFastException(speed);
    }

    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        try {
            v.testSpeed(66);
            v.testSpeed(120);
        } catch (TooFastException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
```

### Implementation Details

* **Empty Constructor**: The Vehicle constructor is empty and contains no initialization as there are no attributes in this class.

* **testSpeed Method**: This method takes an integer speed as a parameter. If the speed is greater than 90, it throws a TooFastException with the offending speed.
    * The condition `if (speed > 90)` checks if the speed is excessive.
    * The statement `throw new TooFastException(speed);` creates and throws a new instance of TooFastException, passing the speed to the constructor.

* **Main Method**:
    * Vehicle Object Creation: A Vehicle object v is created.
    * testSpeed Calls: The testSpeed method is called with two values: 66 (below 90, so no exception is thrown) and 120 (above 90, which triggers TooFastException).
    * Exception Handling: A try-catch block is used to handle TooFastException. If the exception is thrown, the associated message is displayed via `System.out.println(e.getMessage());`.

## 3. Execution and Results

When the program is executed:

1. The first call `v.testSpeed(66);` doesn't exceed the speed limit (90), so nothing happens.
2. The second call `v.testSpeed(120);` exceeds the limit, which triggers TooFastException. The exception is caught in the catch block, and the message is displayed: "This is a TooFastException. Speed at fault: 120".

## Conclusion

Through this exercise, I demonstrated the creation and usage of a custom exception in Java, allowing for the handling of specific cases (in this instance, excessive speed). By triggering TooFastException when the speed is too high, the Vehicle class incorporates a control to prevent unacceptable speed values, illustrating object-oriented programming principles and exception handling concepts.