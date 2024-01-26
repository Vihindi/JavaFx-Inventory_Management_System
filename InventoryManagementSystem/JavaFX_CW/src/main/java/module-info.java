module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.jfoenix;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}