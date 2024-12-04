package ma.enset.tp5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SchoolApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(SchoolApplication.class.getResource("view/main-view.fxml"));

            // Create the scene
            Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
            // Configure the stage
            stage.setTitle("School Management System");
            stage.setMinHeight(600);

            // Set the scene and show the stage
            stage.setScene(scene);
            stage.show();

            // Handle window close request
            stage.setOnCloseRequest(event -> Platform.exit());

        } catch (IOException e) {
            showErrorDialog("Application Error",
                    "Failed to start the application",
                    "Error loading FXML file: " + e.getMessage());
            Platform.exit();
        } catch (Exception e) {
            showErrorDialog("Unexpected Error",
                    "An unexpected error occurred",
                    e.getMessage());
            Platform.exit();
        }
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Main method to launch the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
            Platform.exit();
        }
    }
}