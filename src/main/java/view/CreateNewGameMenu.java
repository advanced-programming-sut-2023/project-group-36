package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ApplicationManager;
import model.User;

public class CreateNewGameMenu extends Application{
    public static Pane root;
    public static CreateNewGameController controller;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        addUsers();
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

    public void addUsers(){
        User user1 = new User("ali","123","1","1","1","1",1);
        User user2 = new User("mohammad","123","1","1","1","1",1);
        User user3 = new User("amir","123","1","1","1","1",1);
        ApplicationManager.addUser(user1);
        ApplicationManager.addUser(user2);
        ApplicationManager.addUser(user3);
    }

}
