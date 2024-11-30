package ma.enset.tp5.dao;

import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.util.List;

public interface IDepartementDAO {
    Departement save(Departement departement);
    List<Departement> getAll();
    Departement getById(int id);
    Departement getByName(String name);
    int delete(int id);
    List<Professeur> getAllProf(int id);
}
