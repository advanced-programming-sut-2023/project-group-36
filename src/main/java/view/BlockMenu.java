package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GBlock;

public class BlockMenu extends Application {

    public static Stage stage;
    public static Pane root;

    public static BlockMenuController controller;

    public static GBlock gBlock;

    @Override
    public void start(Stage stage) throws Exception {
        BlockMenu.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/BlockSelect.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        controller = loader.getController();
        controller.menuInitialize();
    }

}
