module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.json;

    requires java.desktop;
    requires java.sql;
    requires jdbc;


    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
}