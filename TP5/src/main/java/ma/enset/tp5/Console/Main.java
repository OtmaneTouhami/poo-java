package ma.enset.tp5.Console;

import ma.enset.tp5.dao.IDepartementDAO;
import ma.enset.tp5.dao.IProfesseurDAO;
import ma.enset.tp5.dao.impl.IDepartementDAOImpl;
import ma.enset.tp5.dao.impl.IProfesseurDAOImpl;
import ma.enset.tp5.metier.IDepartementMetier;
import ma.enset.tp5.metier.IProfesseurMetier;
import ma.enset.tp5.metier.impl.IDepartementMetierImpl;
import ma.enset.tp5.metier.impl.IProfesseurMetierImpl;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        IProfesseurDAO professeurDAO = new IProfesseurDAOImpl();
        IProfesseurMetier professeurMetier = new IProfesseurMetierImpl(professeurDAO);

        IDepartementDAO departementDAO = new IDepartementDAOImpl();
        IDepartementMetier departementMetier = new IDepartementMetierImpl(departementDAO);

        try {
            // Test data
            createDepartments(departementMetier);
            createProfessors(professeurMetier);
            testGetAllDepartments(departementMetier);
            testGetAllProfessors(professeurMetier);
            testAttachProfessorToDepartment(professeurMetier);
            testUpdateAndDeleteDepartment(departementMetier);
            testSearchAndUpdateProfessor(professeurMetier);
            testGetAllProfessorsInDepartment(departementMetier);
            testDeleteProfessor(professeurMetier);
        } catch (Exception e) {
            System.out.println("An error occurred during testing: " + e.getMessage());
        }
    }

    private static void createDepartments(IDepartementMetier departementMetier) {
        System.out.println("\n=== Creating Departments ===");
        List.of(
                new Departement("Informatique"),
                new Departement("Mathématiques"),
                new Departement("Physique")
        ).forEach(departementMetier::addDepartement);
    }

    private static void createProfessors(IProfesseurMetier professeurMetier) {
        System.out.println("\n=== Creating Professors ===");
        List.of(
                new Professeur("El Amrani", "Karim", "karim.elamrani@gmail.com", "BK432156", "0661234567", "Hay Riad, Rabat", LocalDate.of(2006, 9, 1)),
                new Professeur("Bensouda", "Nadia", "nadia.bensouda@gmail.com", "BE789012", "0662345678", "Maârif, Casablanca", LocalDate.of(2008, 10, 15)),
                new Professeur("Tazi", "Mohammed", "mohammed.tazi@gmail.com", "BJ567890", "0663456789", "Guéliz, Marrakech", LocalDate.of(2010, 1, 10)),
                new Professeur("Alaoui", "Fatima", "fatima.alaoui@gmail.com", "BH234567", "0664567890", "Quartier Hassan, Rabat", LocalDate.of(2012, 3, 20)),
                new Professeur("Benjelloun", "Youssef", "youssef.benjelloun@gmail.com", "BA678901", "0665678901", "Anfa, Casablanca", LocalDate.of(2015, 5, 5))
        ).forEach(professeurMetier::addProfesseur);
    }

    private static void testGetAllDepartments(IDepartementMetier departementMetier) {
        System.out.println("\n=== All Departments ===");
        departementMetier.getAllDepartments().forEach(System.out::println);
    }

    private static void testGetAllProfessors(IProfesseurMetier professeurMetier) {
        System.out.println("\n=== All Professors ===");
        professeurMetier.getAllProfesseur().forEach(System.out::println);
    }

    private static void testAttachProfessorToDepartment(IProfesseurMetier professeurMetier) throws SQLException {
        System.out.println("\n=== Attach Professor to Department ===");
        boolean isAttached = professeurMetier.attachToDepartement(1, 7);
        System.out.println(isAttached ? "Professor successfully attached to department!" : "Failed to attach professor to department.");
    }

    private static void testUpdateAndDeleteDepartment(IDepartementMetier departementMetier) {
        System.out.println("\n=== Update and Delete Department ===");
        Departement department = departementMetier.getAllDepartments().get(0);
        department.setNom("Computer Science");
        Departement updatedDepartment = departementMetier.updateDepartment(department);
        System.out.println("Updated Department: " + updatedDepartment);

        boolean isDeleted = departementMetier.deleteDepartment(department.getId_depart());
        System.out.println(isDeleted ? "Department deleted successfully!" : "Failed to delete department.");
    }

    private static void testSearchAndUpdateProfessor(IProfesseurMetier professeurMetier) throws SQLException {
        System.out.println("\n=== Search and Update Professor ===");
        List<Professeur> profs = professeurMetier.getProfesseurByKey("Alaoui");
        if (!profs.isEmpty()) {
            Professeur prof = profs.get(0);
            System.out.println("Found Professor: " + prof);

            prof.setEmail("updated.alaoui@gmail.com");
            Professeur updatedProf = professeurMetier.updateProfesseur(prof);
            System.out.println("Updated Professor: " + updatedProf);
        } else {
            System.out.println("No professor found with the keyword!");
        }
    }

    private static void testGetAllProfessorsInDepartment(IDepartementMetier departementMetier) {
        System.out.println("\n=== All Professors in Department ===");
        departementMetier.getAllProfInDepartment(2).forEach(System.out::println);
    }

    private static void testDeleteProfessor(IProfesseurMetier professeurMetier) throws SQLException {
        System.out.println("\n=== Search and Delete a Professor  ===");
        Professeur profToDelete = professeurMetier.getProfesseurByKey("Benjelloun").get(0);
        System.out.println("Found Professor: " + profToDelete);
        System.out.println(
                professeurMetier.deleteProfesseur(profToDelete.getId_prof()) ?
                        "Professor deleted successfully" :
                        "Something went wrong!"
        );
    }
}
