package ma.enset.tp5.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Departement {
    private Integer id_depart;
    private String nom;

    public Departement(Integer id_depart, String nom) {
        this.id_depart = id_depart;
        this.nom = nom;
    }

    public Departement(String nom) {
        this.nom = nom;
    }

    public Departement() {
    }

    public Integer getId_depart() {
        return id_depart;
    }

    public void setId_depart(Integer id_depart) {
        this.id_depart = id_depart;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Departement departementAssembler(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setId_depart(resultSet.getInt("id_depart"));
        departement.setNom(resultSet.getString("nom"));
        return departement;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id_depart +
                ", nom='" + nom + '\'' +
                "}";
    }
}
