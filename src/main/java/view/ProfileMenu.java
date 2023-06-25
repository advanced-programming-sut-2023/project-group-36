package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ApplicationManager;

public class ProfileMenu extends Application {
    Timeline timeline;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        VBox info=new VBox();
        info.setAlignment(Pos.TOP_CENTER);
        Label username=new Label("username = "+"");//incomplete
        Label nickname=new Label("nickname = "+"");
        Label email=new Label("email = "+"");
        Label slogan=new Label("slogan = "+"");
        ImageView avatar=new ImageView(new Image(ProfileMenu.class.getResource(ApplicationManager.getCurrentUser().getAvatarAddress()).toString()));
        info.getChildren().add(avatar);
        info.getChildren().add(username);
        info.getChildren().add(nickname);
        info.getChildren().add(email);
        info.getChildren().add(slogan);


    }
}
