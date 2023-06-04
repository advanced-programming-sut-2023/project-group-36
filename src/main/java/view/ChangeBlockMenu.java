package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GBlock;

public class ChangeBlockMenu extends Application {

    public static Stage stage;
    public static Pane root;

    public static ChangeBlockMenuController controller;

    public static GBlock gBlock;

    @Override
    public void start(Stage stage) throws Exception {
        ChangeBlockMenu.stage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/ChangeBlock.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        controller = loader.getController();
        controller.menuInitialize();
    }


    /*

    public static void runChangeStage(Block block){
        Pane pane = new Pane();
        pane.setPrefSize(300,300);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        Button button = new Button("exit"+block.getX()+","+block.getY());
        button.setOnMouseClicked(event -> stage.close());
        button.setLayoutX(150);
        button.setLayoutY(250);
        pane.getChildren().add(button);
    }


     */

}
