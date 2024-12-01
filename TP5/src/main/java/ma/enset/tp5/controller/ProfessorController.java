package ma.enset.tp5.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import ma.enset.tp5.dao.impl.IDepartementDAOImpl;
import ma.enset.tp5.dao.impl.IProfesseurDAOImpl;
import ma.enset.tp5.metier.IDepartementMetier;
import ma.enset.tp5.metier.IProfesseurMetier;
import ma.enset.tp5.metier.impl.IDepartementMetierImpl;
import ma.enset.tp5.metier.impl.IProfesseurMetierImpl;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProfessorController {
    // Input Fields
    @FXML
    private TextField lastNameField;
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
    private TextField firstNameField;
    @FXML
    private TextField searchField;
    @FXML
    private Button addButton;

    // Elements
    @FXML
    private TableView<Professeur> professorTable;
    @FXML
    private TableColumn<Professeur, Integer> idColumn;
    @FXML
    private TableColumn<Professeur, String> lastNameColumn;
    @FXML
    private TableColumn<Professeur, String> firstNameColumn;
    @FXML
    private TableColumn<Professeur, String> cinColumn;
    @FXML
    private TableColumn<Professeur, String> addressColumn;
    @FXML
    private TableColumn<Professeur, String> phoneColumn;
    @FXML
    private TableColumn<Professeur, String> emailColumn;
    @FXML
    private TableColumn<Professeur, LocalDate> hireDateColumn;
    @FXML
    private TableColumn<Professeur, Departement> departmentColumn;
    @FXML
    private TableColumn<Professeur, Void> actionsColumn;

    private final ObservableList<Professeur> observableProfessors = FXCollections.observableArrayList();
    private final IProfesseurMetier professeurMetier = new IProfesseurMetierImpl(new IProfesseurDAOImpl());
    private Professeur selectedProfesseur = null;

    @FXML
    public void initialize() {
        bindAttributesToColumns();
        loadProfessors();
        addActionButtons();
        professorTable.setItems(observableProfessors);
    }

    public void bindAttributesToColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("departement"));
        departmentColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Departement departement, boolean empty) {
                super.updateItem(departement, empty);
                Professeur professeur = getTableRow().getItem();
                if (empty || professeur == null) {
                    setText(null);
                } else if (departement == null) {
                    setText("Not defined");
                } else {
                    setText(departement.getNom());
                }
            }
        });
    }

    public void loadProfessors() {
        observableProfessors.clear();
        List<Professeur> professeurs = professeurMetier.getAllProfesseur();
        observableProfessors.addAll(professeurs);
    }

    public void addActionButtons() {
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button attachBtn = new Button("Attach to a department");
            public final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox buttons = new HBox(15, attachBtn, editBtn, deleteBtn);

            {
                attachBtn.setStyle("-fx-background-color: #ffd45d; -fx-text-fill: white");
                editBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white");
                deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white");

                attachBtn.setOnAction(event -> {
                    Professeur professeur = getTableView().getItems().get(getIndex());
                    handleAttach(professeur);
                });

                editBtn.setOnAction(event -> {
                    Professeur professeur = getTableView().getItems().get(getIndex());
                    handleEdit(professeur);
                });

                deleteBtn.setOnAction(event -> {
                    Professeur professeur = getTableView().getItems().get(getIndex());
                    handleDelete(professeur);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons);
                }
            }
        });
    }

    public void handleAttach(Professeur professeur) {
        IDepartementMetier departementMetier = new IDepartementMetierImpl(new IDepartementDAOImpl());
        List<Departement> departments = departementMetier.getAllDepartments();

        List<String> choices = departments.stream()
                .map(Departement::getNom)
                .collect(Collectors.toList());

        String defaultChoice;

        if (professeur.getDepartement() != null) {
            defaultChoice = professeur.getDepartement().getNom();
            choices.add(0, "Detach");
        } else {
            defaultChoice = "Choose a department";
            choices.add(0, defaultChoice);
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(defaultChoice, choices);
        choiceDialog.setTitle("Attach To Department");
        choiceDialog.setHeaderText(
                "Attach a department to professor: " +
                        professeur.getPrenom() +
                        " " +
                        professeur.getNom()
        );
        choiceDialog.setContentText("Choose a Department:");

        Optional<String> result = choiceDialog.showAndWait();
        result.ifPresent(selected -> {
            try {
                if ("Detach".equals(selected)) {
                    // Detach the professor from their department
                    professeurMetier.attachToDepartement(professeur.getId_prof(), null);
                    professeur.setDepartement(null);
                } else if (!"Choose a department".equals(selected)) {
                    Departement selectedDepartement = departments.stream()
                            .filter(d -> d.getNom().equals(selected))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Department not found"));
                    professeurMetier.attachToDepartement(professeur.getId_prof(), selectedDepartement.getId_depart());
                    professeur.setDepartement(selectedDepartement);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadProfessors();
        });
    }

    public void handleEdit(Professeur professeur) {
        selectedProfesseur = professeur;
        firstNameField.setText(professeur.getPrenom());
        lastNameField.setText(professeur.getNom());
        cinField.setText(professeur.getCin());
        emailField.setText(professeur.getEmail());
        phoneField.setText(professeur.getTelephone());
        firstNameField.setText(professeur.getPrenom());
        addressField.setText(professeur.getAddresse());
        hireDatePicker.setValue(professeur.getDate_recrutement());
        addButton.setText("Update");
    }

    public void handleDelete(Professeur professeur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Professor");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText(
                "Are you sure you want to delete professor: " +
                        professeur.getPrenom() +
                        " " +
                        professeur.getNom() +
                        "?"
        );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            professeurMetier.deleteProfesseur(professeur.getId_prof());
            loadProfessors();
        }
    }

    public void handleAdd() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String cin = cinField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressField.getText().trim();
        LocalDate hireDate = hireDatePicker.getValue();
        if (
                firstName.isEmpty() ||
                        lastName.isEmpty() ||
                        phone.isEmpty() ||
                        cin.isEmpty() ||
                        email.isEmpty() ||
                        address.isEmpty() ||
                        hireDate == null
        ) {
            showAlert("All Fields are required! They Cannot be empty!");
            return;
        }
        if (!emailRule(email)) {
            showAlert("The email should be in this form: exemple-exemple@gmail.com");
            return;
        }
        if (selectedProfesseur != null) {
            selectedProfesseur.setNom(lastName);
            selectedProfesseur.setPrenom(firstName);
            selectedProfesseur.setTelephone(phone);
            selectedProfesseur.setAddresse(address);
            selectedProfesseur.setCin(cin);
            selectedProfesseur.setEmail(email);
            selectedProfesseur.setDate_recrutement(hireDate);
            try {
                professeurMetier.updateProfesseur(selectedProfesseur);
            } catch (SQLException e) {
                showAlert("An Error accrue in the time of the update of the professor");
                e.printStackTrace();
            }
        } else {
            Professeur newProfessor = new Professeur(lastName, firstName, email, cin, phone, address, hireDate);
            professeurMetier.addProfesseur(newProfessor);
        }
        loadProfessors();
        handleClear();
    }

    public void handleClear() {
        lastNameField.clear();
        firstNameField.clear();
        cinField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        hireDatePicker.setValue(null);
        selectedProfesseur = null;
        addButton.setText("Add");
    }

    public void handleSearch() throws SQLException {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            showAlert("The search Field con not be empty!");
            return;
        }
        List<Professeur> professeursWithKeyword = professeurMetier.getProfesseurByKey(keyword);
        if (!professeursWithKeyword.isEmpty()) {
            observableProfessors.clear();
            observableProfessors.addAll(professeursWithKeyword);
            searchField.clear();
        }
    }

    public void handleReset() {
        loadProfessors();
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean emailRule(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+[.-][a-zA-Z0-9]+@gmail.com$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}