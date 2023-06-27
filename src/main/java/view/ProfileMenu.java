package view;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
import model.User;

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
        //changing info
        info.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        pane.getChildren().add(info);
        VBox ChangeInfo=new VBox();
        ChangeInfo.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        ChangeInfo.setLayoutY(0);
        ChangeInfo.setLayoutX(202);
        ChangeInfo.setMinWidth(880);
        ChangeInfo.setMinHeight(400);
        ChangeInfo.setSpacing(25);
        ChangeInfo.setAlignment(Pos.TOP_CENTER);
        ChangeInfo.getChildren().add(new Label("Change Info\n\n"));
        //username
        HBox changeUsername=new HBox();
        changeUsername.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        changeUsername.setAlignment(Pos.TOP_CENTER);
        changeUsername.setMinHeight(40);
        Label changeUser=new Label("new Username :");
        TextField changeNameField=new TextField("");
        changeNameField.setMinWidth(200);
        Label UsernameCheck=new Label("");
        changeUsername.setSpacing(25);
        changeUsername.getChildren().addAll(changeUser,changeNameField,UsernameCheck);
        ChangeInfo.getChildren().add(changeUsername);
        //password
        HBox passwordBox=new HBox();
        passwordBox.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        passwordBox.setAlignment(Pos.TOP_CENTER);
        passwordBox.setSpacing(25);
        Label passwordLabel =new Label("Password :");
        Label currPassCheck=new Label("");
        Label newPassCheck=new Label("");
        TextField currentPassword=new TextField();
        currentPassword.setPromptText("current password");
        currentPassword.setMinWidth(200);
        TextField newPassword=new TextField();
        newPassword.setPromptText("new password");
        newPassword.setMinWidth(200);
        VBox passwords=new VBox(new HBox(currentPassword,currPassCheck),new HBox(newPassword,newPassCheck));
        passwords.setSpacing(25);
        passwordBox.getChildren().addAll(passwordLabel,passwords);
        ChangeInfo.getChildren().add(passwordBox);
        //nickname
        HBox nicknameBox=new HBox();
        nicknameBox.setSpacing(25);
        nicknameBox.setAlignment(Pos.TOP_CENTER);
        nicknameBox.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(12),new BorderWidths(1))));
        Label nicknameLabel=new Label("nickname : ");
        TextField newNickname=new TextField();
        Label NicknameCheck=new Label("");
        newNickname.setPromptText("new nickname");
        newNickname.setMinWidth(200);
        nicknameBox.getChildren().addAll(nicknameLabel,newNickname,NicknameCheck);
        ChangeInfo.getChildren().add(nicknameBox);
        //email
        HBox emailBox=new HBox();
        emailBox.setSpacing(37);
        emailBox.setAlignment(Pos.TOP_CENTER);
        emailBox.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(12),new BorderWidths(1))));
        Label emailLabel=new Label("email :   ");
        TextField newemail=new TextField();
        Label EmailCheck=new Label("");
        newemail.setPromptText("new email");
        newemail.setMinWidth(200);
        emailBox.getChildren().addAll(emailLabel,newemail,EmailCheck);
        ChangeInfo.getChildren().add(emailBox);
        //slogan
        HBox sloganBox=new HBox();


        //scoreboard
        TableView<User> scoreboard=new TableView<User>();

        pane.getChildren().add(ChangeInfo);
        stage.setScene(new Scene(pane));
        stage.show();

    }
}
