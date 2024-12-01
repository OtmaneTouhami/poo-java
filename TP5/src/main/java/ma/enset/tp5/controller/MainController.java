package ma.enset.tp5.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab professorTab;

    private ProfessorController professorController;

    @FXML
    public void initialize() {
        loadProfessorView();
        tabPane.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldTab, newTab) -> {
                    if (newTab == professorTab && professorController != null) {
                        professorController.refresh();
                    }
                });
    }

    private void loadProfessorView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(
                            getClass().getResource("/ma/enset/tp5/view/professor-view.fxml")
                    )
            );
            professorTab.setContent(loader.load());
            professorController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
