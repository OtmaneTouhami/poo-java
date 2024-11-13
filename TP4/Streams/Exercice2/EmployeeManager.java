package Streams.Exercice2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employe> employees = new ArrayList<>();
        employees.add(new Employe("Yassir", "Informatique", 25000));
        employees.add(new Employe("Nadia", "Logistique", 22000));
        employees.add(new Employe("Rachid", "Ressources humaines", 24000));
        employees.add(new Employe("Fatima", "Finance", 23000));
        employees.add(new Employe("Hassan", "Marketing", 25000));
        employees.add(new Employe("Sara", "Ressources humaines", 26000));
        employees.add(new Employe("Ahmed", "Ventes", 27000));
        employees.add(new Employe("Khalid", "Logistique", 28000));
        employees.add(new Employe("Soukaina", "Informatique", 29000));
        employees.add(new Employe("Otmane", "Informatique", 30000));

        double sumSalary = employees.stream()
                .mapToDouble(Employe::getSalaire)
                .sum();

        System.out.println("Total salary of all employees: " + sumSalary);

        List<Employe> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employe::getNom))
                .toList();

        System.out.println("Employees sorted by name: " + sortedEmployees);

        double salaryToSearch;
        try {
            System.out.print("Enter a salary to search: ");
            salaryToSearch = sc.nextDouble();
            sc.nextLine();

            List<Employe> employeesWithGivenSalary = employees.stream()
                    .filter(employe -> employe.getSalaire() >= salaryToSearch)
                    .toList();

            System.out.println("Employees with salary greater than or equal to " + salaryToSearch + ": " + employeesWithGivenSalary);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid salary.");
        } finally {
            sc.close();
        }

        Employe employeeWithMaxSalary = employees.stream()
                .reduce((max, employee) -> employee.getSalaire() > max.getSalaire() ? employee : max)
                .orElse(null);

        System.out.println("Employee with the highest salary: " + employeeWithMaxSalary);

        String allEmployeesNames = employees.stream()
                .map(Employe::getNom)
                .reduce((names, employeeName) -> names + " - " + employeeName)
                .orElse("No employees found");

        System.out.println("All employee names: " + allEmployeesNames);
    }
}
