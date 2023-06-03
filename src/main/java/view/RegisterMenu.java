package view;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ApplicationManager;

public class RegisterMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setMinWidth(1080);
        pane.setMinHeight(720);
        Scene scene=new Scene(pane);
        pane.getChildren().add(new Rectangle(0,0,50,50));
        stage.setScene(scene);
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/wallpaper-mania.com_High_resolution_wallpaper_background_ID_77701506456.jpg").openStream(),1080,720,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        VBox Register=new VBox();
        Register.setAlignment(Pos.TOP_CENTER);
        Register.setLayoutX(100);
        Register.setLayoutY(80);
        Register.setMinWidth(360);
        Register.setMinHeight(620);
        Register.setSpacing(25);
        Register.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        Label RegisterLAbel=new Label("Register");
        RegisterLAbel.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        RegisterLAbel.setTextFill(Color.WHITE);
        Register.getChildren().add(RegisterLAbel);
        TextField username = new TextField(),email=new TextField(),nickname=new TextField(),passwordrecovery=new TextField();
        PasswordField passwordField=new PasswordField();
        username.setPromptText("Username");
        username.setMaxWidth(250);
        username.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        username.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        username.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);
        passwordField.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        passwordField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        passwordField.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        email.setPromptText("Email");
        email.setMaxWidth(250);
        email.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        email.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        email.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        nickname.setPromptText("Nickname");
        nickname.setMaxWidth(250);
        nickname.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        nickname.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        nickname.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        passwordrecovery.setPromptText("Security Question");
        passwordrecovery.setMaxWidth(250);
        passwordrecovery.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        passwordrecovery.setMinHeight(200);
        Register.getChildren().addAll(username,passwordField,email,nickname);
        Label usernameCheck=new Label("");
        pane.getChildren().add(usernameCheck);
        usernameCheck.setLayoutX(470);
        usernameCheck.setLayoutY(146);
        usernameCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        username.setOnKeyTyped(keyEvent -> {
            if(username.getText().length()>0 && username.getText().length()<4){
                usernameCheck.setTextFill(Color.RED);
                usernameCheck.setText("Short Username");

            }
            else if(ApplicationManager.getUserByUsername(username.getText()) != null){
                usernameCheck.setTextFill(Color.RED);
                usernameCheck.setText("Username already exists!");
            }
            else if(ApplicationManager.getUserByUsername(username.getText()) == null){
                usernameCheck.setTextFill(Color.GREEN);
                usernameCheck.setText("Okay");
            }
            else if(username.getText()==null){
                usernameCheck.setText("");
            }


        });








        pane.getChildren().add(Register);
        stage.show();
    }
}
