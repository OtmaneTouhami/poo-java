package ma.enset.tp5.dao.impl;

import ma.enset.tp5.dao.IDepartementDAO;
import ma.enset.tp5.dao.SignletonConnexionDB;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IDepartementDAOImpl implements IDepartementDAO {

    public static Connection prepareConnection() {
        try {
            return SignletonConnexionDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Departement save(Departement departement) {
        if (departement.getId_depart() == 0) {
            String insertQuery = "INSERT INTO department (nom) VALUES (?)";
            try (
                    Connection connection = prepareConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            insertQuery,
                            Statement.RETURN_GENERATED_KEYS
                    )
            ) {
                preparedStatement.setString(1, departement.getNom());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            departement.setId_depart(generatedKeys.getInt(1));
                        }
                    }
                    return departement;
                }

            } catch (SQLException e) {
                System.out.println("Error during insert: " + e.getMessage());
            }
        } else {
            String updateQuery = "UPDATE department SET nom = ? WHERE id_depart = ?";
            try (
                    Connection connection = prepareConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
            ) {
                preparedStatement.setString(1, departement.getNom());
                preparedStatement.setInt(2, departement.getId_depart());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return departement;
                }
            } catch (SQLException e) {
                System.out.println("Error during update: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Departement> getAll() {
        String query = "SELECT * FROM department";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Departement> departments = new ArrayList<>();

            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setId_depart(resultSet.getInt("id_depart"));
                departement.setNom(resultSet.getString("nom"));
                departments.add(departement);
            }
            return departments;
        } catch (SQLException e) {
            System.out.println("Error retrieving departments: " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public Departement getById(int id) {
        String query = "SELECT * FROM department WHERE id_depart = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Departement.departementAssembler(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving department by ID: " + id, e);
        }
        return null;
    }

    @Override
    public Departement getByName(String name) {
        String query = "SELECT * FROM department WHERE nom = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Departement.departementAssembler(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving department by name: " + name, e);
        }
        return null;
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM department WHERE id_depart = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting department with ID " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<Professeur> getAllProf(int id) {
        String query = "SELECT * FROM professor WHERE id_depart = ?";
        try (
                Connection connection = prepareConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Professeur> professeurs = new ArrayList<>();
                IProfesseurDAOImpl professeurDAO = new IProfesseurDAOImpl();
                while (resultSet.next()) {
                    professeurs.add(professeurDAO.getProfesseur(resultSet));
                }
                return professeurs;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving professors for department with ID: " + id, e);
        }
    }
}
