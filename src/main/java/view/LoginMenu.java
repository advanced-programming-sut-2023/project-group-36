package view;

import model.ApplicationManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ApplicationManager.run();
        Pane pane=new Pane();
        pane.setMinWidth(1080);
        pane.setMinHeight(720);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/wallpaper-mania.com_High_resolution_wallpaper_background_ID_77701506533.jpg").openStream(),1080,720,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        Pane login=new Pane();
        login.setMinHeight(320);
        login.setMinWidth(230);
        login.setLayoutY(100);
        login.setLayoutX(100);
        VBox LoginFields=new VBox();
        LoginFields.setAlignment(Pos.TOP_CENTER);
        LoginFields.setSpacing(25);
        Label l1=new Label("Login");
        l1.setFont(Font.font("Ariel", FontWeight.BOLD, 22));
        l1.setTextFill(Color.WHITE);
        LoginFields.getChildren().add(l1);
        LoginFields.setMinHeight(320);
        LoginFields.setMinWidth(230);
        l1.setAlignment(Pos.CENTER);
        login.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        LoginFields.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        pane.getChildren().add(login);
        TextField usernameField=new TextField();
        usernameField.setText("username");
        usernameField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        usernameField.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        usernameField.setMaxWidth(200);
        LoginFields.getChildren().add(usernameField);
        PasswordField passwordField=new PasswordField();
        passwordField.setMaxWidth(200);
        passwordField.setText("Password");
        passwordField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        passwordField.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));

        LoginFields.getChildren().add(passwordField);
        Button LoginButton = new Button();
        LoginButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        LoginButton.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        LoginButton.setText("Login");
        LoginButton.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        LoginButton.setTextFill(Color.WHITE);
        CheckBox loggedIn=new CheckBox();
        loggedIn.setText("Stay logged in");
        loggedIn.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
        loggedIn.setTextFill(Color.WHITE);
        Button RegisterButton=new Button();
        RegisterButton.setText("Register");
        RegisterButton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        RegisterButton.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        RegisterButton.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        RegisterButton.setTextFill(Color.WHITE);
        HBox buttons=new HBox(LoginButton,RegisterButton);
        buttons.setSpacing(25);
        buttons.setAlignment(Pos.CENTER);
        LoginFields.getChildren().add(buttons);
        LoginFields.getChildren().add(loggedIn);
        login.getChildren().add(LoginFields);
        stage.show();
        //Actions
        LoginButton.setOnMouseClicked(mouseEvent -> {

        });
        RegisterButton.setOnMouseClicked(mouseEvent -> {
            try {
                new RegisterMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
