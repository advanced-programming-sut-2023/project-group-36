module Project_Phase_2{
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.graphics;
        exports view;
        opens view to javafx.fxml, com.google.gson;
        exports Chat;
        opens Chat to javafx.fxml;
        exports model;
        requires com.google.gson;
        requires javafx.media;
    requires java.sql;
    requires java.xml.crypto;
    opens model to com.google.gson;
    opens model.Buildings to com.google.gson;
    opens model.Peoples to com.google.gson;


}