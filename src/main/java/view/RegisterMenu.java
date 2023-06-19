package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.ApplicationManager;

public class RegisterMenu extends Application {
    private boolean truePass,trueUsername,trueEmail,trueNick;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setMinWidth(1080);
        pane.setMinHeight(720);
        Scene scene=new Scene(pane);
        ImageView backIcon=new ImageView(new Image(RegisterMenu.class.getResource("/images/icons/Ionic-Ionicons-Caret-back-circle-sharp.512.png").toString()));
        backIcon.setFitWidth(80);
        backIcon.setFitHeight(80);
        backIcon.setX(0);
        backIcon.setY(0);
        pane.getChildren().add(backIcon);
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
        CheckBox slogan=new CheckBox();
        slogan.setText("I want to a slogan");
        slogan.setTextFill(Color.WHITE);
        TextField sloganText=new TextField();
        Register.getChildren().addAll(username,passwordField,email,nickname,slogan);
        Register.getChildren().add(sloganText);
        sloganText.setVisible(false);
        Label usernameCheck=new Label("Short Username");
        Label passwordCheck=new Label("Short Password");
        Label emailCheck=new Label("Invalid Email");
        Label nickCheck =new Label("Invalid Nickname");
        pane.getChildren().add(usernameCheck);
        usernameCheck.setLayoutX(470);
        usernameCheck.setLayoutY(146);
        pane.getChildren().add(passwordCheck);
        passwordCheck.setLayoutX(470);
        passwordCheck.setLayoutY(216);
        pane.getChildren().add(emailCheck);
        emailCheck.setLayoutX(470);
        emailCheck.setLayoutY(286);
        pane.getChildren().add(nickCheck);
        nickCheck.setLayoutX(470);
        nickCheck.setLayoutY(356);
        usernameCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        passwordCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        emailCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        nickCheck.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        usernameCheck.setTextFill(Color.RED);
        passwordCheck.setTextFill(Color.RED);
        emailCheck.setTextFill(Color.RED);
        nickCheck.setTextFill(Color.RED);
        username.setOnKeyReleased(keyEvent -> {
            if(username.getText().length()>=0 && username.getText().length()<4){
                usernameCheck.setTextFill(Color.RED);
                usernameCheck.setText("Short Username");
                trueUsername=false;

            }
            else if(username.getText().matches(".*[@#%&*\\^$!].*")){
                usernameCheck.setTextFill(Color.YELLOW);
                usernameCheck.setText("Invalid format");
                trueUsername=false;
            }
            else if(ApplicationManager.getUserByUsername(username.getText()) != null){
                usernameCheck.setTextFill(Color.RED);
                usernameCheck.setText("Username already exists!");
                trueUsername=false;
            }
            else if(ApplicationManager.getUserByUsername(username.getText()) == null){
                usernameCheck.setTextFill(Color.GREEN);
                usernameCheck.setText("Okay");
                trueUsername=true;
            }


        });
        passwordField.setOnKeyReleased(keyEvent -> {
            if(passwordField.getText().length()>=0 && passwordField.getText().length()<4){
                passwordCheck.setTextFill(Color.RED);
                passwordCheck.setText("Short Password");
                truePass=false;

            }
            else if(!passwordField.getText().matches(".*[@#%&*-\\^$!].*")){
                passwordCheck.setTextFill(Color.YELLOW);
                passwordCheck.setText("Weak format");
                truePass=false;
            }
            else{
                passwordCheck.setTextFill(Color.GREEN);
                passwordCheck.setText("Okay");
                truePass=true;
            }


        });
        email.setOnKeyTyped(keyEvent -> {
            if(email.getText().matches(".+@.+\\..+")) {
                emailCheck.setTextFill(Color.GREEN);
                emailCheck.setText("Okay");
                trueEmail=true;
            }
            else{
                emailCheck.setTextFill(Color.RED);
                emailCheck.setText("Invalid Email");
                trueEmail=false;
            }
        });
        nickname.setOnKeyTyped(keyEvent -> {
            if(nickname.getText().length()>6){
                trueNick=true;
                nickCheck.setTextFill(Color.GREEN);
                nickCheck.setText("Okay");
            }
            else{
                trueNick=false;
                nickCheck.setTextFill(Color.RED);
                nickCheck.setText("Invalid Nickname");
            }
        });
        slogan.setOnMouseClicked(mouseEvent -> {
            if(!slogan.isSelected()){
                sloganText.setVisible(false);
            }
            else{
                sloganText.setVisible(true);
            }
        });
        backIcon.setOnMouseClicked(mouseEvent -> {
            try {
                new LoginMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });








        pane.getChildren().add(Register);
        stage.show();
    }
}
