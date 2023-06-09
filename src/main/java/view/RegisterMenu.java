package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ApplicationManager;
import model.User;

import java.util.Random;

public class RegisterMenu extends Application {
    private boolean truePass,trueUsername,trueEmail,trueNick,trueSeq;
    private CaptchaMenu captchaMenu=new CaptchaMenu();
    private int secIn=1;
    public static Timeline timeline;
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
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/wallpaper-mania.com_High_resolution_wallpaper_background_ID_77701506456.jpg").openStream(),1080,750,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        Button RegisterButton=new Button("Register");
        RegisterButton.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
//        RegisterButton.setBackground(Background.fill(Color.TRANSPARENT));
        RegisterButton.setFont(Font.font("Ariel", FontWeight.BOLD, 18));
        RegisterButton.setStyle("-fx-text-fill: white;");
        VBox Register=new VBox();
        Register.setPadding(new Insets(5,12,12,12));
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
        passwordField.setOnMouseClicked(mouseEvent -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your password:");
            alert.setContentText(passwordField.getText());
            alert.show();
        });
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
        SplitMenuButton securityQ=new SplitMenuButton(new MenuItem("1. what is your father's name?"),new MenuItem("2. who was your first class teacher?")
        ,new MenuItem("3. what was your most hated course in second term?"));
        securityQ.setText("security question");
        securityQ.setMinWidth(150);
        securityQ.setMaxWidth(150);
        passwordrecovery.setPromptText("Security Question Answer");
        passwordrecovery.setMaxWidth(250);
        passwordrecovery.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        passwordrecovery.setMinHeight(25);
        CheckBox slogan=new CheckBox();
        slogan.setText("I want to have slogan");
        slogan.setTextFill(Color.WHITE);
        SplitMenuButton preSloagans=new SplitMenuButton(new MenuItem("Duty before death"),new MenuItem("Strike first"),new MenuItem("I Will Not Return Unavenged")
                ,new MenuItem("In this sign, you will conquer"));
        TextField sloganText=new TextField();
        Button randSlogan=new Button("random");
        Button randPass=new Button("Random\nPass");
        HBox items=new HBox(preSloagans,randPass,randSlogan);
        items.setAlignment(Pos.TOP_CENTER);
        items.setSpacing(25);
        Register.getChildren().addAll(username,passwordField,email,nickname,securityQ,passwordrecovery,slogan,sloganText,items,RegisterButton);
        sloganText.setVisible(false);
        preSloagans.setVisible(false);
        randSlogan.setVisible(false);
        sloganText.setMaxWidth(200);
        sloganText.setMaxHeight(200);
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
            else if(username.getText().matches(".*[@#%&*\\^$!]+.*") ){
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
            else if(!passwordField.getText().matches(".*[@#%&*\\-$!]+.*") || passwordField.getText().matches("\\d+.*") || !passwordField.getText().matches(".*[A-Z]+.*")){
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
                preSloagans.setVisible(false);
                randSlogan.setVisible(false);
            }
            else{
                sloganText.setVisible(true);
                preSloagans.setVisible(true);
                randSlogan.setVisible(true);
            }
        });
        for(MenuItem menuItem:preSloagans.getItems()){
            menuItem.setOnAction(actionEvent -> {
                sloganText.setText(menuItem.getText());
            });
        }
        for(MenuItem menuItem:securityQ.getItems()){
            menuItem.setOnAction(actionEvent -> {
                securityQ.setText(menuItem.getText());
                if(menuItem.getText().contains("teacher"))
                    secIn=2;
                else if (menuItem.getText().contains("course"))
                    secIn=3;
                else
                    secIn=1;
            });
        }
        randSlogan.setOnMouseClicked(mouseEvent -> {
            sloganText.setText(preSloagans.getItems().get((int) ((Math.random() * 4))).getText());
        });
        backIcon.setOnMouseClicked(mouseEvent -> {
            try {
                new LoginMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        randPass.setOnMouseClicked(mouseEvent -> {
            passwordField.setText(randomPass((int) (Math.random() *12 + 10)));
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("your random password:");
            alert.setContentText(passwordField.getText());
            alert.show();

        });

    RegisterButton.setOnMouseClicked(mouseEvent -> {
        if(trueNick && truePass && trueEmail && trueUsername && trueSeq){
            try {
                captchaMenu.start(new Stage());
            } catch (Exception e) {
                System.out.println("error in loading register captcha");
                throw new RuntimeException(e);
            }
        }
        else{
            RegisterButton.setText("Error");
            new Timeline(new KeyFrame(Duration.seconds(1),actionEvent -> {})).play();
            RegisterButton.setText("Register");
        }
    });

        timeline=new Timeline(new KeyFrame(Duration.millis(16),actionEvent -> {
            if(username.getText().length()==0)
                usernameCheck.setText("");
            if(passwordField.getText().length()==0)
                passwordCheck.setText("");
            if(email.getText().length()==0)
                emailCheck.setText("");
            if(nickname.getText().length()==0)
                nickCheck.setText("");
            if(!securityQ.getText().contains("question") && passwordrecovery.getText().length()>3) {
                trueSeq = true;
            }
            if(captchaMenu.getCanPass()) {
                try {
                    captchaMenu.setCanPass(false);
                    stage.close();
                    ApplicationManager.setCurrentUser(new User(username.getText(),passwordField.getText(),nickname.getText(),email.getText(),sloganText.getText(),passwordrecovery.getText(),secIn));
                    ApplicationManager.getUsers().add(ApplicationManager.getCurrentUser());
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Register successful!");
                    alert.setContentText(ApplicationManager.getCurrentUser().getUsername()+"\n"+ApplicationManager.getCurrentUser().getPassword());
                    new MainMenu().start(stage);
                } catch (Exception e) {
                    System.out.println("error in loading main menu");
                    e.printStackTrace();
                }

            }

        }));
        timeline.setCycleCount(-1);
        timeline.play();
        pane.getChildren().add(Register);
        stage.show();
    }
    public String randomPass(int length){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }

}
