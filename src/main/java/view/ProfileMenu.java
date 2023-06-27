package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.ApplicationManager;

public class ProfileMenu extends Application {
    Timeline timeline;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setMinHeight(720);
        pane.setMinWidth(1080);
        VBox info=new VBox();
        info.setAlignment(Pos.TOP_CENTER);
        info.setMinWidth(200);
        info.setMinHeight(700);
        Image avatar=new Image(getClass().getResource("/images/Avatars/1.png").openStream());
        ImageView avatarView=new ImageView(new Image(getClass().getResource("/images/Avatars/1.png").openStream()));
        avatarView.setFitHeight(100);
        avatarView.setFitWidth(100);

        info.getChildren().add(avatarView);
        info.setLayoutX(0);
        info.setLayoutY(0);
        /*Label CurrentUsername=new Label("Username :\n"+ApplicationManager.getCurrentUser().getUsername());
        Label CurrentNickname=new Label("Nickname :\n"+ApplicationManager.getCurrentUser().getNickname());
        Label CurrnetEmail=new Label("Email :\n"+ApplicationManager.getCurrentUser());
        Label CurrentSlogan=new Label("Slogan :\n"+ApplicationManager.getCurrentUser().getSlogan());
        info.getChildren().add(CurrentUsername);
        info.getChildren().add(CurrentNickname);
        info.getChildren().add(CurrnetEmail);
        info.getChildren().add(CurrentSlogan);*/
        info.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        pane.getChildren().add(info);
        VBox ChangeInfo=new VBox();
        ChangeInfo.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        ChangeInfo.setLayoutY(0);
        ChangeInfo.setLayoutX(202);
        ChangeInfo.setMinWidth(880);
        ChangeInfo.setMinHeight(400);
        ChangeInfo.setAlignment(Pos.TOP_CENTER);
        HBox changeUsername=new HBox();
        changeUsername.setAlignment(Pos.TOP_CENTER);
        Label changeUser=new Label("new Username :");
        TextField changeNameField=new TextField("");
        Label UsernameCheck=new Label("");
        changeUsername.getChildren().addAll(changeUser,changeNameField,UsernameCheck);
        ChangeInfo.getChildren().add(changeUsername);
        pane.getChildren().add(ChangeInfo);
        stage.setScene(new Scene(pane));
        stage.show();

    }
}
