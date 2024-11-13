# Lab Report - Exercise 3: GroupsManager

## Description

In this exercise, I implemented a group management system using Java Collections. The objective was to demonstrate basic operations on `HashSet` collections, including finding the intersection and union of two groups.

## 1. GroupsManager Class

The `GroupsManager` class contains the main method and demonstrates various operations on two `HashSet` collections representing groups of names.

### Code

```java
package Collections.Exercice3;

import java.util.HashSet;

public class GroupsManager {
    public static void main(String[] args) {
        HashSet<String> groupeA = new HashSet<>();
        groupeA.add("Khalid");
        groupeA.add("Rafik");
        groupeA.add("Hassan");
        groupeA.add("Majda");
        groupeA.add("Morad");
        groupeA.add("Otmane");
        groupeA.add("Fatima");

        HashSet<String> groupeB = new HashSet<>();
        groupeB.add("Otmane");
        groupeB.add("Yacer");
        groupeB.add("Mohammed");
        groupeB.add("Hakim");
        groupeB.add("Lamia");
        groupeB.add("Fatima");

        HashSet<String> intersection = new HashSet<>(groupeA);
        intersection.retainAll(groupeB);
        System.out.println(intersection);

        HashSet<String> union = new HashSet<>(groupeA);
        union.addAll(groupeB);
        System.out.println(union);
    }
}
```

### Implementation Details

**HashSet Initialization and Adding Elements**:
```java
HashSet<String> groupeA = new HashSet<>();
groupeA.add("Khalid");
groupeA.add("Rafik");
groupeA.add("Hassan");
groupeA.add("Majda");
groupeA.add("Morad");
groupeA.add("Otmane");
groupeA.add("Fatima");

HashSet<String> groupeB = new HashSet<>();
groupeB.add("Otmane");
groupeB.add("Yacer");
groupeB.add("Mohammed");
groupeB.add("Hakim");
groupeB.add("Lamia");
groupeB.add("Fatima");
```
* Initializes two `HashSet` collections and adds names to each group.

**Finding the Intersection**:
```java
HashSet<String> intersection = new HashSet<>(groupeA);
intersection.retainAll(groupeB);
System.out.println(intersection);
```
* Creates a new `HashSet` for the intersection of `groupeA` and `groupeB`.
* Uses `retainAll` to keep only the elements that are present in both sets.
* Prints the intersection.

**Finding the Union**:
```java
HashSet<String> union = new HashSet<>(groupeA);
union.addAll(groupeB);
System.out.println(union);
```
* Creates a new `HashSet` for the union of `groupeA` and `groupeB`.
* Uses `addAll` to include all elements from both sets.
* Prints the union.

## 2. Execution and Results

When the program is executed:

1. The intersection of `groupeA` and `groupeB` is displayed.
2. The union of `groupeA` and `groupeB` is displayed.

### Test Case
```
[Otmane, Fatima]
[Khalid, Morad, Hakim, Otmane, Fatima, Rafik, Majda, Mohammed, Yacer, Lamia, Hassan]
```

## Conclusion

Through this exercise, I demonstrated basic operations on `HashSet` collections using Java Collections. The `GroupsManager` class showcases finding the intersection and union of two groups, illustrating fundamental set manipulation techniques.