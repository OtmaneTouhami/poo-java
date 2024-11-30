module ma.enset.tp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens ma.enset.tp5.controller to javafx.fxml;
    opens ma.enset.tp5.model to javafx.base;

    exports ma.enset.tp5;
}