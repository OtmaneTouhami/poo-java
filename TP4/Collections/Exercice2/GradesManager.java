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

        //  boolean isThere20Grade = grades.values().stream().anyMatch(grade -> grade == 20.0);
        //  System.out.println(
        //          isThere20Grade ?
        //                  "There is a student with a grade of 20" :
        //                  "None of the students has a grade of 20."
        //  );

    }
}
