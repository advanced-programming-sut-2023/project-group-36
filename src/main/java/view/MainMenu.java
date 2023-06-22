package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("hi");
        Pane pane=new Pane();
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}
