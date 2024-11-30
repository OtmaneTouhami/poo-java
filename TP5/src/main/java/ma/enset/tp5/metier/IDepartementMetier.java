package ma.enset.tp5.metier;

import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.util.List;

public interface IDepartementMetier {
    Departement addDepartement(Departement departement);
    List<Departement> getAllDepartments();
    boolean deleteDepartment(int id);
    Departement updateDepartment(Departement departement);
    List<Professeur> getAllProfInDepartment(int id);
}
