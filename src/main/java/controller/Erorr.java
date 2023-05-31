package controller;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Erorr extends Application {
    private String ErorrName="";
    private String ErorrDetails="";


    @Override
    public void start(Stage stage) throws Exception {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ErorrDetails);
        alert.setHeaderText(ErorrName);
        alert.showAndWait();

    }
    public void setError(String ErorrName,String ErorrDetails) throws Exception {
        this.ErorrDetails=ErorrDetails;
        this.ErorrName=ErorrName;
        this.start(new Stage());
    }
}
