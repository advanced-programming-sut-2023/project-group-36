package view;

import javafx.geometry.Insets;
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
import model.User;

public class LoginMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setMinWidth(1080);
        pane.setMinHeight(720);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/wallpaper-mania.com_High_resolution_wallpaper_background_ID_77701506533.jpg").toString(),1080,720,false,true),
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
        usernameField.setPromptText("Username");
        usernameField.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        usernameField.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID,new CornerRadii(15),new BorderWidths(3))));
        usernameField.setMaxWidth(200);
        LoginFields.getChildren().add(usernameField);
        PasswordField passwordField=new PasswordField();
        passwordField.setMaxWidth(200);
        passwordField.setPromptText("Password");
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
        Label forgetPass=new Label("forgot my password");
        forgetPass.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
        forgetPass.setTextFill(Color.CRIMSON);
        LoginFields.getChildren().add(forgetPass);
        login.getChildren().add(LoginFields);
        stage.show();
        //Actions
        LoginButton.setOnMouseClicked(mouseEvent -> {
                String username=usernameField.getText();
                String password=passwordField.getText();
                if(username.length()==0 || password.length()==0){
                    User use=ApplicationManager.getUserByUsername(username);
                    if(use==null){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("No User with this Username");
                        alert.show();
                    }
                    else if(!use.getPassword().equals(password)){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Wrong Password");
                        alert.show();
                    }
                    else{
                        ApplicationManager.setCurrentUser(use);
                        try {
                            new MainMenu().start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("error in loading main menu");
                        }
                    }
                }
        });
        RegisterButton.setOnMouseClicked(mouseEvent -> {
            try {
                new RegisterMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        forgetPass.setOnMouseClicked(mouseEvent -> {
            Stage s1=new Stage();
            s1.setHeight(500);
            s1.setWidth(500);
            s1.setX(550);
            s1.setY(200);
            VBox pane1=new VBox();
            pane1.setAlignment(Pos.TOP_CENTER);
            pane1.setSpacing(25);
            pane1.setPadding(new Insets(15,25,15,25));
            pane1.getChildren().add(new Label("Enter your username:"));
            TextField usernamef=new TextField();
            usernamef.setMaxWidth(200);
            Button ok=new Button("Check");
            Label secQ=new Label();
            TextField seqA=new TextField();
            seqA.setMaxWidth(250);
            Button secondOk=new Button("Check answer");
            pane1.getChildren().addAll(usernamef,ok,secQ,seqA,secondOk);
            secQ.setVisible(false);
            seqA.setVisible(false);
            secondOk.setVisible(false);
            ok.setOnMouseClicked(mouseEvent1 -> {
                User use=ApplicationManager.getUserByUsername(usernamef.getText());
                if(use==null){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No User with this Username");
                    alert.show();
                }
                else{
                    secQ.setVisible(true);
                    seqA.setVisible(true);
                    secondOk.setVisible(true);
                    switch (use.getQuestionNumber()) {
                        case 1 -> secQ.setText("what is your father's name?");
                        case 2 -> secQ.setText("who was your first class teacher?");
                        case 3 -> secQ.setText("what was your most hated course in second term?");
                    }
                    secondOk.setOnMouseClicked(mouseEvent2 -> {
                        String text=seqA.getText();
                        if(use.getQuestionAnswer().equalsIgnoreCase(text)){
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("your password:");
                            alert.setContentText(use.getPassword());
                            alert.show();
                            alert.setOnCloseRequest(dialogEvent -> {
                                s1.close();
                            });
                        }
                    });
                }
            });

            Scene fscene=new Scene(pane1);
            s1.setScene(fscene);
            s1.show();
        });
    }
}
