package ma.enset.tp5.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ma.enset.tp5.metier.IDepartementMetier;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;
import ma.enset.tp5.metier.IProfesseurMetier;

public class ProfessorController {
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField cinField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private ComboBox<Departement> departmentCombo;
    @FXML
    private TableView<Professeur> professorTable;

    private IProfesseurMetier professeurMetier;
    private IDepartementMetier departementMetier;
    private Professeur selectedProfessor;

    @FXML
    public void initialize() {
        // Initialize the table columns and load data
        setupTableColumns();
        loadDepartments();
        loadProfessors();
    }

    @FXML
    private void handleAdd() {
        if (validateForm()) {
            Professeur prof = createProfessorFromForm();
            professeurMetier.addProfesseur(prof);
            loadProfessors();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        if (selectedProfessor != null && validateForm()) {
            updateProfessorFromForm();
            loadProfessors();
            clearForm();
        }
    }

    @FXML
    private void handleClear() {
        clearForm();
    }

    private void setupTableColumns() {
        // Setup table columns
    }

    private void loadProfessors() {
        // Load professors into table
    }

    private void loadDepartments() {
        // Load departments into combo box
    }

    private void clearForm() {
        // Clear all form fields
    }

    private boolean validateForm() {
        // Validate form fields
        return true;
    }

    private Professeur createProfessorFromForm() {
        // Create new professor from form data
        return null;
    }

    private void updateProfessorFromForm() {
        // Update selected professor with form data
    }

    public void setProfesseurMetier(IProfesseurMetier professeurMetier) {
        this.professeurMetier = professeurMetier;
    }

    public void setDepartementMetier(IDepartementMetier departementMetier) {
        this.departementMetier = departementMetier;
    }

    public void refreshData() {
        // Reload professor data
        loadProfessors();
    }
}