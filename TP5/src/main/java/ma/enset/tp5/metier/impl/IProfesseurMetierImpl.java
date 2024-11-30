package ma.enset.tp5.metier.impl;

import ma.enset.tp5.dao.IProfesseurDAO;
import ma.enset.tp5.metier.IProfesseurMetier;
import ma.enset.tp5.model.Professeur;

import java.sql.SQLException;
import java.util.List;

public class IProfesseurMetierImpl implements IProfesseurMetier {
    IProfesseurDAO professeurDAO;

    public IProfesseurMetierImpl(IProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public IProfesseurDAO getProfesseurDAO() {
        return professeurDAO;
    }

    public void setProfesseurDAO(IProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    @Override
    public List<Professeur> getAllProfesseur() {
        return professeurDAO.getAll();
    }

    @Override
    public List<Professeur> getProfesseurByKey(String key) throws SQLException {
        return professeurDAO.getByKey(key);
    }

    @Override
    public Professeur addProfesseur(Professeur professeur) {
        try {
            if (
                    professeurDAO.getByCin(professeur.getCin()) == null
                            && professeurDAO.getByEmail(professeur.getEmail()) == null
            ) {
                return professeurDAO.save(professeur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteProfesseur(int id) {
        return professeurDAO.delete(id) > 0;
    }

    @Override
    public Professeur updateProfesseur(Professeur professeur) throws SQLException {
        Professeur existingByEmail = professeurDAO.getByEmail(professeur.getEmail());
        Professeur existingByCin = professeurDAO.getByCin(professeur.getCin());

        boolean profWithSameEmail = existingByEmail != null && existingByEmail.getId_prof() != professeur.getId_prof();
        boolean profWithSameCin = existingByCin != null && existingByCin.getId_prof() != professeur.getId_prof();

        if (profWithSameEmail || profWithSameCin) {
            return null;
        }

        return professeurDAO.save(professeur);
    }

    @Override
    public boolean attachToDepartement(int prof_id, int depart_id) throws SQLException {
        return professeurDAO.attach(prof_id, depart_id) > 0;
    }
}
