package ma.enset.tp5.dao;

import ma.enset.tp5.model.Professeur;

import java.sql.SQLException;
import java.util.List;

public interface IProfesseurDAO {
    Professeur getById(int id) throws SQLException;
    Professeur getByEmail(String email) throws SQLException;
    Professeur getByCin(String cin) throws SQLException;
    List<Professeur> getByKey(String key) throws SQLException;
    List<Professeur> getAll();
    Professeur save(Professeur professeur);
    int delete(int id);
    int attach(int prof_id, int depart_id) throws SQLException;
}
