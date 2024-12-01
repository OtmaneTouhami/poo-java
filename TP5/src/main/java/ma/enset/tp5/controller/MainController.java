package ma.enset.tp5.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    private StackPane contentPane;

    // Method to load the Department view
    @FXML
    private void showDepartmentView() {
        loadView("/ma/enset/tp5/view/department-view.fxml");
    }

    // Method to load the Professor view
    @FXML
    private void showProfessorView() {
        loadView("/ma/enset/tp5/view/professor-view.fxml");
    }

    // Helper method to load a view into the StackPane
    private void loadView(String fxmlFile) {
        try {
            Node view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
