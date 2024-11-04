# Java Exception Handling Lab

This repository contains three exercises demonstrating custom exception handling in Java. Each exercise focuses on creating and using specific exception classes for different validation scenarios.

## Exercises

### [Exercise 1: TropViteException](Exercice1/README.md)
Implementation of a speed validation system that throws a custom exception when a vehicle's speed exceeds 90 km/h.
- Custom exception class: `TropViteException`
- Main class: `Vehicule`

### [Exercise 2: RacineCarreeException](Exercice2/README.md)
Implementation of a square root calculator that throws a custom exception when attempting to calculate the square root of a negative number.
- Custom exception class: `RacineCarreeException`
- Main class: `Calculateur`

### [Exercise 3: NoteInvalideException](Exercice3/README.md)
Implementation of a grade validation system that throws a custom exception when a grade is outside the valid range (0-20).
- Custom exception class: `NoteInvalideException`
- Main class: `Evaluateur`

## Project Structure
```
TP2/
├── Exercice1/
│   ├── README.md
│   ├── TropViteException.java
│   └── Vehicule.java
├── Exercice2/
│   ├── README.md
│   ├── RacineCarreeException.java
│   └── Calculateur.java
├── Exercice3/
│   ├── README.md
│   ├── NoteInvalideException.java
│   └── Evaluateur.java
└── README.md
```