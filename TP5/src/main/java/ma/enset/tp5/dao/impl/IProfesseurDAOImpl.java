package ma.enset.tp5.dao.impl;

import ma.enset.tp5.dao.IDepartementDAO;
import ma.enset.tp5.dao.IProfesseurDAO;
import ma.enset.tp5.dao.SignletonConnexionDB;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IProfesseurDAOImpl implements IProfesseurDAO {

    public static Connection prepareConnection() {
        try {
            return SignletonConnexionDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professeur getProfesseur(ResultSet resultSet) throws SQLException {
        Professeur professeur = Professeur.professorAssembler(resultSet);
        Integer idDepart = (Integer) resultSet.getObject("id_depart");
        if (idDepart != null) {
            IDepartementDAO departementDAO = new IDepartementDAOImpl();
            Departement departement = departementDAO.getById(idDepart);
            professeur.setDepartement(departement);
        }
        return professeur;
    }

    @Override
    public Professeur getById(int id) throws SQLException {
        String query = "SELECT * FROM professor WHERE id_prof = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getProfesseur(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving professor by ID: " + id, e);
        }
        return null;
    }

    @Override
    public Professeur getByEmail(String email) throws SQLException {
        String query = "SELECT * FROM professor WHERE email = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getProfesseur(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving professor by Email: " + email, e);
        }
        return null;
    }

    @Override
    public Professeur getByCin(String cin) throws SQLException {
        String query = "SELECT * FROM professor WHERE cin = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, cin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getProfesseur(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving professor by CIN: " + cin, e);
        }
        return null;
    }

    @Override
    public List<Professeur> getByKey(String key) throws SQLException {
        String query = "SELECT * FROM professor WHERE nom LIKE ? OR prenom LIKE ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "%" + key + "%");
            preparedStatement.setString(2, "%" + key + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Professeur> professeurs = new ArrayList<>();
                while (resultSet.next()) {
                    professeurs.add(getProfesseur(resultSet));
                }
                return professeurs;
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving professor by Key: " + key, e);
        }
    }

    @Override
    public List<Professeur> getAll() {
        String query = "SELECT * FROM professor";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Professeur> professeurs = new ArrayList<>();

            while (resultSet.next()) {
                professeurs.add(getProfesseur(resultSet));
            }
            return professeurs;

        } catch (SQLException e) {
            System.out.println("Error retrieving professors: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public Professeur save(Professeur professeur) {
        if (professeur.getId_prof() == null) {
            String insertQuery = "INSERT INTO professor "
                    + "(nom, prenom, cin, adresse, telephone, email, date_recrutement) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (
                    Connection connection = prepareConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)
            ) {
                prepareProfessor(professeur, preparedStatement);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            professeur.setId_prof(generatedKeys.getInt(1));
                        }
                    }
                    return professeur;
                }

            } catch (SQLException e) {
                System.out.println("Error during insert: " + e.getMessage());
            }
        } else {
            String updateQuery = "UPDATE professor SET "
                    + "nom = ?, prenom = ?, cin = ?, adresse = ?, telephone = ?, email = ?, date_recrutement = ? "
                    + "WHERE id_prof = ?";
            try (
                    Connection connection = prepareConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
            ) {
                prepareProfessor(professeur, preparedStatement);
                preparedStatement.setInt(8, professeur.getId_prof());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return professeur;
                }
            } catch (SQLException e) {
                System.out.println("Error during update: " + e.getMessage());
            }
        }
        return null;
    }

    private void prepareProfessor(Professeur professeur, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, professeur.getNom());
        preparedStatement.setString(2, professeur.getPrenom());
        preparedStatement.setString(3, professeur.getCin());
        preparedStatement.setString(4, professeur.getAddresse());
        preparedStatement.setString(5, professeur.getTelephone());
        preparedStatement.setString(6, professeur.getEmail());
        preparedStatement.setDate(7, java.sql.Date.valueOf(professeur.getDate_recrutement()));    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM professor WHERE id_prof = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting professor with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public int attach(int prof_id, int depart_id) throws SQLException {
        String query = "UPDATE professor SET id_depart = ? WHERE id_prof = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, depart_id);
            preparedStatement.setInt(2, prof_id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
