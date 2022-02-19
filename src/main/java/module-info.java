module com.example.museumrest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.museumrest to javafx.fxml;
    exports com.example.museumrest;
}