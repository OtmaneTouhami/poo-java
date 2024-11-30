package ma.enset.tp5.metier;

import ma.enset.tp5.model.Professeur;

import java.sql.SQLException;
import java.util.List;

public interface IProfesseurMetier {
    List<Professeur> getAllProfesseur();
    List<Professeur> getProfesseurByKey(String key) throws SQLException;
    Professeur addProfesseur(Professeur professeur);
    boolean deleteProfesseur(int id);
    Professeur updateProfesseur(Professeur professeur) throws SQLException;
    boolean attachToDepartement(int prof_id, int depart_id) throws SQLException;
}
