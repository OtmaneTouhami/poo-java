package ma.enset.tp5.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Professeur {
    private Integer id_prof;
    private String nom;
    private String prenom;
    private String cin;
    private String addresse;
    private String telephone;
    private String email;
    private LocalDate date_recrutement;
    private Departement departement;

    public Professeur(
            String nom,
            String prenom,
            String email,
            String cin,
            String telephone,
            String addresse,
            LocalDate date_recrutement
    ) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.addresse = addresse;
        this.telephone = telephone;
        this.email = email;
        this.date_recrutement = date_recrutement;
    }

    public Professeur() {
    }

    public Integer getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate_recrutement() {
        return date_recrutement;
    }

    public void setDate_recrutement(LocalDate date_recrutement) {
        this.date_recrutement = date_recrutement;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public static Professeur professorAssembler(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        Professeur professeur = new Professeur();
        professeur.setId_prof(resultSet.getInt("id_prof"));
        professeur.setNom(resultSet.getString("nom"));
        professeur.setPrenom(resultSet.getString("prenom"));
        professeur.setCin(resultSet.getString("cin"));
        professeur.setAddresse(resultSet.getString("adresse"));
        professeur.setDate_recrutement(resultSet.getDate("date_recrutement").toLocalDate());
        professeur.setEmail(resultSet.getString("email"));
        professeur.setTelephone(resultSet.getString("telephone"));
        return professeur;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "id=" + id_prof +
                ", nom='" + nom + '\'' +
                ", prénom='" + prenom + '\'' +
                ", CIN='" + cin + '\'' +
                ", adresse='" + addresse + '\'' +
                ", tél='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", recrutement=" + (date_recrutement != null ? date_recrutement.toString() : "Non définie") +
                ", département=" + (departement != null ? departement.getNom() : "Non affecté") +
                "}";
    }
}

