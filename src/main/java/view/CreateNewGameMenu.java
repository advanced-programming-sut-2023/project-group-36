package view;

import controller.CreateNewGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateNewGameMenu extends Application{
    public static int capacity;
    public static Pane root;
    public static CreateNewGameController controller;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        CreateNewGameMenu.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/CreateNewGame.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        controller = loader.getController();
        controller.menuInitialize();
    }

}
