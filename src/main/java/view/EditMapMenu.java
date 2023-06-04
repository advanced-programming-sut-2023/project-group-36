package view;
import controller.EditMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Government;
import model.Map;
import java.util.ArrayList;

public class EditMapMenu extends Application{
    public static Pane root;
    public static EditMapController controller;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        EditMap.map = new Map(50);
        EditMapMenu.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/EditMap.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        controller = loader.getController();
        System.out.println();
        controller.menuInitialize();
    }


}
