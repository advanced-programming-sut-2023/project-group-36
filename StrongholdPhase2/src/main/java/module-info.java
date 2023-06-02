module Project_Phase_2{
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.graphics;
        exports view;
        opens view to javafx.fxml, com.google.gson;
        exports model;
        requires com.google.gson;
        requires javafx.media;
        opens model to com.google.gson;
}