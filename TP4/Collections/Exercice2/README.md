# Lab Report - Exercise 2: GradesManager

## Description

In this exercise, I implemented a grades management system using Java Collections. The objective was to demonstrate basic operations on a `HashMap` of student grades, including adding, removing, updating, and calculating statistics.

## 1. GradesManager Class

The `GradesManager` class contains the main method and demonstrates various operations on a `HashMap` of student grades.

### Code

```java
package Collections.Exercice2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

public class GradesManager {
    public static void main(String[] args) {
        HashMap<String, Double> grades = new HashMap<>();
        grades.put("Hassan", 12.5);
        grades.put("Khalid", 20.0);
        grades.put("Morad", 9.5);
        grades.put("Mona", 19.0);
        grades.put("Latifa", 14.5);

        System.out.println("The list of students and their grades is: ");
        grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));

        grades.replace("Morad", 11.0);

        System.out.println("The list of students and their grades after Morad's grade was updated is: ");
        grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));

        grades.remove("Latifa");

        System.out.println("The list of students and their grades after Latifa was removed is: ");
        grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));

        System.out.println("The size of the map is: " + grades.size());

        Double gradesSum = grades.values()
                .stream()
                .reduce(0D, Double::sum);

        System.out.println("The average grade is: " + gradesSum / grades.size());

        Double maxGrade = grades.values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(null);

        Double minGrade = grades.values()
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(null);

        System.out.println("The max grade is: " + maxGrade);
        System.out.println("The min grade is: " + minGrade);

        Optional<HashMap.Entry<String, Double>> gradeWith20 = grades.entrySet()
                .stream()
                .filter(grade -> grade.getValue() == 20.0)
                .findFirst();

        System.out.println(
                gradeWith20.map(grade -> "The student " + grade.getKey() + " has a grade of 20.")
                        .orElse("None of the students has a grade of 20.")
        );
    }
}
```

### Implementation Details

**HashMap Initialization and Adding Grades**:
```java
HashMap<String, Double> grades = new HashMap<>();
grades.put("Hassan", 12.5);
grades.put("Khalid", 20.0);
grades.put("Morad", 9.5);
grades.put("Mona", 19.0);
grades.put("Latifa", 14.5);
```
* Initializes a `HashMap` of student names and their grades, and adds five entries.

**Displaying Grades**:
```java
System.out.println("The list of students and their grades is: ");
grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));
```
* Iterates over the `HashMap` and prints each student's name and grade.

**Updating a Grade**:
```java
grades.replace("Morad", 11.0);
```
* Updates Morad's grade to 11.0.

**Displaying Updated Grades**:
```java
System.out.println("The list of students and their grades after Morad's grade was updated is: ");
grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));
```
* Iterates over the `HashMap` and prints each student's name and updated grade.

**Removing a Grade**:
```java
grades.remove("Latifa");
```
* Removes Latifa's entry from the `HashMap`.

**Displaying Grades After Removal**:
```java
System.out.println("The list of students and their grades after Latifa was removed is: ");
grades.forEach((name, grade) -> System.out.println(name + " has a grade of " + grade));
```
* Iterates over the `HashMap` and prints each student's name and grade after removal.

**Displaying the Size of the Map**:
```java
System.out.println("The size of the map is: " + grades.size());
```
* Prints the size of the `HashMap`.

**Calculating and Displaying the Average Grade**:
```java
Double gradesSum = grades.values()
        .stream()
        .reduce(0D, Double::sum);

System.out.println("The average grade is: " + gradesSum / grades.size());
```

1. **Calculating the Sum of All Grades**:
    - The `grades.values()` method returns a collection of all the grade values in the `HashMap`.
    - The `stream()` method converts this collection into a stream.
    - The `reduce(0D, Double::sum)` method is used to sum all the elements in the stream. Here, `0D` is the identity value (starting point), and `Double::sum` is a method reference that adds two `Double` values.

2. **Printing the Average Grade**:
    - The sum of all grades is divided by the size of the `HashMap` (number of students) to get the average grade.
    - The `System.out.println` method is used to print the average grade.


**Finding and Displaying the Maximum and Minimum Grades**:
```java
Double maxGrade = grades.values()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(null);

Double minGrade = grades.values()
        .stream()
        .min(Comparator.naturalOrder())
        .orElse(null);

System.out.println("The max grade is: " + maxGrade);
System.out.println("The min grade is: " + minGrade);
```

1. **Finding the Maximum Grade**:
   - `grades.values()`: This method returns a collection of all the grade values in the `HashMap`.
   - `.stream()`: Converts the collection of grades into a stream.
   - `.max(Comparator.naturalOrder())`: Finds the maximum value in the stream using the natural order comparator (which compares `Double` values in ascending order).
   - `.orElse(null)`: If the stream is empty, it returns `null` instead of throwing an exception.

2. **Finding the Minimum Grade**:
   - `grades.values()`: Returns a collection of all the grade values in the `HashMap`.
   - `.stream()`: Converts the collection of grades into a stream.
   - `.min(Comparator.naturalOrder())`: Finds the minimum value in the stream using the natural order comparator.
   - `.orElse(null)`: If the stream is empty, it returns `null` instead of throwing an exception.

3. **Printing the Results**:
   - `System.out.println("The max grade is: " + maxGrade);`: Prints the maximum grade found.
   - `System.out.println("The min grade is: " + minGrade);`: Prints the minimum grade found.

**Finding and Displaying a Student with a Grade of 20**:
```java
Optional<HashMap.Entry<String, Double>> gradeWith20 = grades.entrySet()
        .stream()
        .filter(grade -> grade.getValue() == 20.0)
        .findFirst();

System.out.println(
        gradeWith20.map(grade -> "The student " + grade.getKey() + " has a grade of 20.")
                .orElse("None of the students has a grade of 20.")
);
```
1. **Finding a Student with a Grade of 20**:
   - `grades.entrySet()`: This method returns a set view of the mappings contained in the `HashMap`.
   - `.stream()`: Converts the set of entries into a stream.
   - `.filter(grade -> grade.getValue() == 20.0)`: Filters the stream to include only those entries where the grade value is 20.0.
   - `.findFirst()`: Finds the first entry in the filtered stream, if any, and returns it as an `Optional`.

2. **Printing the Result**:
   - `gradeWith20.map(grade -> "The student " + grade.getKey() + " has a grade of 20.")`: If an entry with a grade of 20 is found, this maps the entry to a string message.
   - `.orElse("None of the students has a grade of 20.")`: If no entry with a grade of 20 is found, this provides a default message.
   - `System.out.println(...)`: Prints the resulting message to the console.
## 2. Execution and Results

When the program is executed:

1. The list of students and their grades is displayed.
2. Morad's grade is updated.
3. The updated list of students and their grades is displayed.
4. Latifa's entry is removed.
5. The list of students and their grades after removal is displayed.
6. The size of the `HashMap` is displayed.
7. The average grade is calculated and displayed.
8. The maximum and minimum grades are found and displayed.
9. The student with a grade of 20 is found and displayed, if any.

### Test Case
```
The list of students and their grades is: 
Khalid has a grade of 20.0
Mona has a grade of 19.0
Morad has a grade of 9.5
Latifa has a grade of 14.5
Hassan has a grade of 12.5
The list of students and their grades after Morad's grade was updated is: 
Khalid has a grade of 20.0
Mona has a grade of 19.0
Morad has a grade of 11.0
Latifa has a grade of 14.5
Hassan has a grade of 12.5
The list of students and their grades after Latifa was removed is: 
Khalid has a grade of 20.0
Mona has a grade of 19.0
Morad has a grade of 11.0
Hassan has a grade of 12.5
The size of the map is: 4
The average grade is: 15.625
The max grade is: 20.0
The min grade is: 11.0
The student Khalid has a grade of 20.
```

## Conclusion

Through this exercise, I demonstrated basic operations on a `HashMap` of student grades using Java Collections. The `GradesManager` class showcases adding, removing, updating, and calculating statistics for grades, illustrating fundamental collection manipulation techniques.