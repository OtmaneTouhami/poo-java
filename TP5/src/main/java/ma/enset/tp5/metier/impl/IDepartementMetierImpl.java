package ma.enset.tp5.metier.impl;

import ma.enset.tp5.dao.IDepartementDAO;
import ma.enset.tp5.metier.IDepartementMetier;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.util.List;

public class IDepartementMetierImpl implements IDepartementMetier {
    IDepartementDAO departementDAO;

    public IDepartementMetierImpl(IDepartementDAO dao) {
        this.departementDAO = dao;
    }

    public IDepartementDAO getDepartementDAO() {
        return departementDAO;
    }

    public void setDepartementDAO(IDepartementDAO dao) {
        this.departementDAO = dao;
    }

    @Override
    public Departement addDepartement(Departement departement) {
        Departement d = departementDAO.getByName(departement.getNom());
        if (d == null) {
            return departementDAO.save(departement);
        }
        return null;
    }

    @Override
    public List<Departement> getAllDepartments() {
        return departementDAO.getAll();
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departementDAO.delete(id) > 0;
    }

    @Override
    public Departement updateDepartment(Departement departement) {
        if (departement.getId_depart() > 0 && departementDAO.getById(departement.getId_depart()) != null) {
            return departementDAO.save(departement);
        }
        return null;
    }

    @Override
    public List<Professeur> getAllProfInDepartment(int id) {
        return departementDAO.getAllProf(id);
    }
}
