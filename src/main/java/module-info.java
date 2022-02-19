module com.example.museumrest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.museumrest to javafx.fxml, com.google.gson;
    exports com.example.museumrest;
}