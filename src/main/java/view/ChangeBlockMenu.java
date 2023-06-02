package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Block;

public class ChangeBlockMenu extends Application {

    public static Stage stage = new Stage();
    public static Pane pane;
    @Override
    public void start(Stage stage) throws Exception {
        ChangeBlockMenu.stage = stage;
        pane = new Pane();
        pane.setPrefSize(300,300);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("game");
        stage.show();

    }

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

}
