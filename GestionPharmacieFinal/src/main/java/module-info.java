module com.example.gestfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires com.jfoenix;


    opens com.example.gestfinal to javafx.fxml;
    exports com.example.gestfinal;
    exports com.example.gestfinal.Classes;
    opens com.example.gestfinal.Classes to javafx.fxml;
    exports com.example.gestfinal.Controllers;
    opens com.example.gestfinal.Controllers to javafx.fxml;
}