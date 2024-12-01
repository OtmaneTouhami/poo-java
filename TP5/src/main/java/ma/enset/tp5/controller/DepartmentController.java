package ma.enset.tp5.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.enset.tp5.dao.impl.IDepartementDAOImpl;
import ma.enset.tp5.metier.IDepartementMetier;
import ma.enset.tp5.metier.impl.IDepartementMetierImpl;
import ma.enset.tp5.model.Departement;
import ma.enset.tp5.model.Professeur;

import java.util.List;
import java.util.Optional;

public class DepartmentController {
    @FXML
    private Button addButton;
    @FXML
    private TextField departmentNameField;
    @FXML
    private TableView<Departement> departmentTable;
    @FXML
    private TableColumn<Departement, Integer> idColumn;
    @FXML
    private TableColumn<Departement, String> nameColumn;
    @FXML
    private TableColumn<Departement, Void> actionColumn;

    private final ObservableList<Departement> observableDepartments = FXCollections.observableArrayList();
    private final IDepartementMetier departementMetier = new IDepartementMetierImpl(new IDepartementDAOImpl());
    private Departement selectedDepartment = null;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_depart"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        loadDepartments();
        addActionButtons();
        departmentTable.setItems(observableDepartments);
    }

    public void loadDepartments() {
        observableDepartments.clear();
        List<Departement> departments = departementMetier.getAllDepartments();
        observableDepartments.addAll(departments);
    }

    public void addActionButtons() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewProfsBtn = new Button("View Professors");
            public final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox buttons = new HBox(15, viewProfsBtn, editBtn, deleteBtn);

            {
                viewProfsBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                editBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                viewProfsBtn.setOnAction(event -> {
                    Departement department = getTableView().getItems().get(getIndex());
                    handleView(department);
                });

                editBtn.setOnAction(event -> {
                    Departement department = getTableView().getItems().get(getIndex());
                    handleEdit(department);
                });

                deleteBtn.setOnAction(event -> {
                    Departement department = getTableView().getItems().get(getIndex());
                    handleDelete(department);
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

    private void handleView(Departement department) {
        List<Professeur> professeurs = departementMetier.getAllProfInDepartment(department.getId_depart());
        if (professeurs.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Department Professors");
            alert.setContentText("This department has no assigned professors yet!");
            alert.showAndWait();
        } else {
            // Create a new stage
            Stage professorStage = new Stage();
            professorStage.setTitle("Professors in " + department.getNom());

            // Create table
            TableView<Professeur> tableView = new TableView<>();

            // Create columns
            TableColumn<Professeur, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_prof"));

            TableColumn<Professeur, String> nomColumn = new TableColumn<>("Last Name");
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<Professeur, String> prenomColumn = new TableColumn<>("First Name");
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            TableColumn<Professeur, String> cinColumn = new TableColumn<>("CIN");
            cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));

            TableColumn<Professeur, String> emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Professeur, String> adresseColumn = new TableColumn<>("Adresse");
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("addresse"));

            TableColumn<Professeur, String> phoneColumn = new TableColumn<>("Phone");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

            TableColumn<Professeur, String> dateColumn = new TableColumn<>("Hire Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));

            // Add columns to table
            tableView.getColumns().addAll(
                    idColumn,
                    nomColumn,
                    prenomColumn,
                    cinColumn,
                    emailColumn,
                    adresseColumn,
                    phoneColumn,
                    dateColumn
            );

            // Add data to table
            tableView.getItems().addAll(professeurs);

            // Set column resize policy
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Create layout
            VBox vbox = new VBox(10);
            vbox.setPadding(new Insets(10));
            vbox.getChildren().add(tableView);

            // Create scene
            Scene scene = new Scene(vbox);
            professorStage.setScene(scene);

            // Set minimum dimensions
            professorStage.setMinWidth(600);
            professorStage.setMinHeight(400);

            // Make it modal
            professorStage.initModality(Modality.APPLICATION_MODAL);

            // Show the window
            professorStage.show();
        }
    }

    private void handleEdit(Departement department) {
        selectedDepartment = department;
        departmentNameField.setText(department.getNom());
        addButton.setText("Update");
    }

    private void handleDelete(Departement department) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Department");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete department: " + department.getNom() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            departementMetier.deleteDepartment(department.getId_depart());
            loadDepartments();
        }
    }

    @FXML
    public void handleAdd() {
        String departmentName = departmentNameField.getText().trim();
        if (departmentName.isEmpty()) {
            showAlert("Error", "Department name cannot be empty!");
            return;
        }

        if (selectedDepartment != null) {
            // Update existing department
            selectedDepartment.setNom(departmentName);
            departementMetier.updateDepartment(selectedDepartment);
            selectedDepartment = null;
        } else {
            // Add new department
            Departement newDepartment = new Departement();
            newDepartment.setNom(departmentName);
            departementMetier.addDepartement(newDepartment);
        }

        loadDepartments();
        handleClear();
    }

    @FXML
    public void handleClear() {
        departmentNameField.clear();
        selectedDepartment = null;
        addButton.setText("Add");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}