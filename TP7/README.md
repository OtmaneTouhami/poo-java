# Java Multithreading Exercises Documentation

This documentation covers two exercises focusing on Java multithreading concepts. Each exercise demonstrates different aspects of thread implementation and synchronization.

## Table of Contents
- [Exercise 1: Concurrent Number Printing](#exercise-1-concurrent-number-printing)
  - [Overview](#overview)
  - [Code Structure and Implementation](#code-structure-and-implementation)
  - [Execution Output](#execution-output)
- [Exercise 2: Parallel Sum Calculation](#exercise-2-parallel-sum-calculation)
  - [Overview](#overview-1)
  - [Code Structure and Implementation](#code-structure-and-implementation-1)
  - [Execution Output](#execution-output-1)
- [Key Concepts Demonstrated](#key-concepts-demonstrated)
- [Project Structure](#project-structure)
- [Conclusion](#conclusion)

## Exercise 1: Concurrent Number Printing

### Overview
This exercise demonstrates basic thread creation and execution using the `Runnable` interface. It creates multiple threads that print numbers concurrently.

### Code Structure and Implementation

#### Talkative Class
```java
public class Talkative implements Runnable {
    private static int number;

    public Talkative(int number) {
        Talkative.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(number);
        }
    }
}
```

**Detailed Explanation:**
- The `Talkative` class implements the `Runnable` interface, making it suitable for thread execution
- It has a static field `number` that stores the value to be printed
- The constructor takes an integer parameter to set the number to be printed
- The `run()` method contains the code that will be executed by each thread:
  - It prints the assigned number 100 times
  - Each thread will execute this method independently

#### Main Method
```java
public static void main(String[] args) {
    Thread task1 = new Thread(new Talkative(1));
    // ... (creation of tasks 2-9)
    Thread task10 = new Thread(new Talkative(10));

    List<Thread> threadList = List.of(task1, task2, task3, task4, task5, 
                                    task6, task7, task8, task9, task10);

    threadList.forEach(Thread::start);
}
```

**Detailed Explanation:**
- Creates 10 threads, each with a different number (1-10)
- Stores all threads in a list for easier management
- Starts all threads using a forEach loop with method reference
- The threads will run concurrently, resulting in interleaved output of numbers

### Execution Output
When running the main program, here's the actual output:

```
10
10
10
... (repeated 1000 times)
```

This output reveals an interesting behavior in our implementation. Since we're using a static variable `number` in the `Talkative` class, all threads end up sharing the same value (10, which was set by the last thread created). This demonstrates an important concept in multithreading: the need for proper variable scope and thread safety. To see different numbers being printed by different threads, we should make the `number` variable an instance variable instead of static.

## Exercise 2: Parallel Sum Calculation

### Overview
This exercise implements a parallel sum calculation using multiple threads. It divides a list of numbers into segments and calculates partial sums concurrently.

### Code Structure and Implementation

#### Sommeur Class
```java
public class Sommeur implements Runnable {
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
```

**Detailed Explanation:**
- The `Sommeur` class implements `Runnable` for thread execution
- Instance variables:
  - `numbers`: List of integers to process
  - `begin`: Starting index of the segment
  - `end`: Ending index of the segment
  - `sum`: Stores the partial sum result
- The `run()` method:
  - Takes a sublist of numbers from begin to end indices
  - Uses Java streams to calculate the sum efficiently
  - Stores the result in the sum variable
- `getSomme()` method returns the calculated partial sum

#### Main Class
```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, 6, 8, 27, -15, 24, 76, 12, 24, 19, 34, -54);

        Sommeur sommeur1 = new Sommeur(numbers, 0, 3);
        Sommeur sommeur2 = new Sommeur(numbers, 4, 7);
        Sommeur sommeur3 = new Sommeur(numbers, 8, 12);

        Thread task1 = new Thread(sommeur1);
        Thread task2 = new Thread(sommeur2);
        Thread task3 = new Thread(sommeur3);

        // Start threads
        task1.start();
        task2.start();
        task3.start();

        // Wait for completion
        try {
            task1.join();
            task2.join();
            task3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Calculate final sum
        int sum = sommeur1.getSomme() + sommeur2.getSomme() + sommeur3.getSomme();
        System.out.printf("The sum is %d", sum);
    }
}
```

**Detailed Explanation:**
- Creates a list of integers to be summed
- Divides the list into three segments:
  - First segment: indices 0-3
  - Second segment: indices 4-7
  - Third segment: indices 8-12
- Creates three Sommeur instances, each responsible for a segment
- Creates and starts three threads to process segments concurrently
- Uses `join()` to wait for all threads to complete
- Calculates the final sum by adding the partial sums from each thread
- Prints the final result

### Execution Output
When running the parallel sum calculation with the sample list `[1, 3, 6, 8, 27, -15, 24, 76, 12, 24, 19, 34, -54]`, here's the actual output:

```
The sum is 165
```

The program successfully calculates the total sum by dividing the work among three threads. Each thread processes its assigned segment silently and the final result (165) is computed by combining the partial sums. This demonstrates efficient parallel processing, though we could enhance the output by adding logging statements to show the partial sums calculated by each thread.

## Key Concepts Demonstrated
1. Thread Creation and Management
2. Parallel Processing
3. Thread Synchronization using join()
4. Data Partitioning for Parallel Processing
5. Stream API Usage for Efficient Calculations

## Project Structure
The project is structured as follows:
```
TP7
│   .gitignore
│   README.md
│       
├───Exercice1
│       Talkative.java
│       
├───Exercice2
│       Main.java
│       Sommeur.java
```

## Conclusion
These exercises effectively demonstrate key concepts in Java multithreading, from basic thread creation to parallel processing and synchronization. Through practical implementation, we've seen how concurrent programming can be used to handle multiple tasks simultaneously (Exercise 1) and improve performance through parallel processing (Exercise 2). The exercises showcase both the power and complexity of multithreading, highlighting important considerations such as thread safety and coordination.