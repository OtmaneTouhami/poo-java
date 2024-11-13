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
