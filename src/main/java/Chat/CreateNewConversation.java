package Chat;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ApplicationManager;
import model.User;

public class CreateNewConversation extends Application {
    ChatClient client;

    public CreateNewConversation(ChatClient client) {
        this.client = client;
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox=new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        Label name=new Label("Chat name :");
        TextField chatName=new TextField();
        chatName.setMinWidth(100);
        SplitMenuButton users=new SplitMenuButton();
        for(User user: ApplicationManager.getUsers()){
            users.getItems().add(new CheckMenuItem(user.getUsername()));
        }
        HBox hBox=new HBox(name,chatName);
        hBox.setSpacing(25);
        Label usernames=new Label("Users :");
        HBox hBox1=new HBox();
        hBox1.getChildren().addAll(usernames,users);
        Button check=new Button("Create chat");
        vBox.getChildren().addAll(hBox,hBox1,check);
        stage.setScene(new Scene(vBox));
        stage.show();
        check.setOnMouseClicked(mouseEvent -> {

        });
    }
}
