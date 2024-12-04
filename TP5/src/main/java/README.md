# School Management Application

## Description
This is a JavaFX application for managing a school system. The application is built using:

- JavaFX for the graphical user interface
- MySQL database for data storage
- FXML for UI layout design
- Java modules for better code organization

The main application file (`SchoolApplication.java`) sets up the JavaFX window and loads the main view. The `module-info.java` file configures the necessary dependencies and exports for JavaFX and MySQL connectivity.

This application follows the MVC (Model-View-Controller) pattern for better code organization and maintainability.

## Understanding SchoolApplication.java

Let's look at the main file that starts our school management application:

```java
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
```

### What Each Part Does:

1. **The start() Method**
   - This is where the magic begins! When you run the app, this method:
   - Opens the main window design from a file called 'main-view.fxml'
   - Creates a window that's 1200 pixels wide and 600 pixels tall
   - Puts "School Management System" as the window title
   - Makes sure the window stays at least 600 pixels tall
   - Shows the window to the user
   - Sets up a way to properly close the app when you click the X button

2. **The showErrorDialog() Method**
   - This is our friendly error messenger! When something goes wrong:
   - Creates a pop-up window with a red error icon
   - Shows you what went wrong in an easy-to-read format
   - Waits for you to click OK before closing
   - Helps users understand problems instead of just crashing

3. **The main() Method**
   - This is where everything starts:
   - Launches the whole application
   - Watches for any problems that might happen
   - If something goes wrong, it:
     - Prints what went wrong
     - Shows the full error details
     - Closes the app safely

## Understanding module-info.java

Here's our module configuration file that sets up all the necessary connections:

```java
module ma.enset.tp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens ma.enset.tp5.controller to javafx.fxml;
    opens ma.enset.tp5.model to javafx.base;

    exports ma.enset.tp5;
}
```

### What Each Part Does:

1. **Module Declaration**
   - `module ma.enset.tp5`: This names our module, matching our package structure

2. **Required Dependencies**
   - `requires javafx.controls`: Brings in JavaFX's buttons, text fields, and other UI controls
   - `requires javafx.fxml`: Allows us to use FXML files for designing our UI
   - `requires java.sql`: Gives us the tools to work with databases
   - `requires mysql.connector.j`: Lets us connect to MySQL databases

3. **Module Openness**
   - `opens ma.enset.tp5.controller to javafx.fxml`: Allows JavaFX to work with our controller classes
   - `opens ma.enset.tp5.model to javafx.base`: Lets JavaFX access our data model classes
   - `exports ma.enset.tp5`: Makes our main package available to other modules

This configuration ensures that our application has access to all the necessary tools and libraries while maintaining proper encapsulation and security.

## Conclusion

This School Management System is built with modern Java technologies and follows best practices in software development. The combination of JavaFX for the interface, MySQL for data storage, and a modular architecture makes it both powerful and maintainable. The application provides a user-friendly way to manage school-related data while ensuring robust error handling and a smooth user experience.
