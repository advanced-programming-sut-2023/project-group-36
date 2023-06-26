package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
        ImageView avatarView=new ImageView(avatar);

        avatarView.setFitHeight(100);
        avatarView.setFitWidth(100);
        info.getChildren().add(avatarView);
        info.setLayoutX(0);
        info.setLayoutY(0);
        info.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        pane.getChildren().add(info);
        stage.setScene(new Scene(pane));
        stage.show();

    }
}
