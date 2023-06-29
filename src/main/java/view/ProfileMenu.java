package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ApplicationManager;
import model.User;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class ProfileMenu extends Application {
    private Timeline timeline;
    private boolean nameChane=true;
    private boolean passChange=true;
    private boolean nickChange=true;
    private boolean emailCheck=true;
    private  int counter=0;
    private TableView<User> scoreboard=new TableView<User>();
    CaptchaMenu captchaMenu=new CaptchaMenu();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setStyle("-fx-font-family: Candara; -fx-font-size: 20px;-fx-text-fill: black");
        pane.setMinHeight(720);
        pane.setMinWidth(1080);
        VBox info=new VBox();
        info.setAlignment(Pos.TOP_CENTER);
        info.setMinWidth(200);
        info.setMinHeight(700);
        info.setSpacing(25);
        Image avatar=new Image(getClass().getResource("/images/Avatars/1.png").openStream());
        ImageView avatarView=new ImageView(new Image(getClass().getResource("/images/Avatars/1.png").openStream()));
        avatarView.setFitHeight(100);
        avatarView.setFitWidth(100);
        avatarView.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Avatar Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                avatarView.setImage(imageView.getImage());
            }
        });
        info.getChildren().add(avatarView);
        HBox v1=new HBox();
        v1.setAlignment(Pos.TOP_CENTER);
        v1.setSpacing(15);
        HBox v2=new HBox();
        v2.setAlignment(Pos.TOP_CENTER);
        v2.setSpacing(15);
        info.getChildren().addAll(v1,v2);
        for(int i=1;i<9;i++){
            if(i<5)
                v1.getChildren().add(new ImageView(new Image(getClass().getResource("/images/Avatars/"+i+".png").toString())));
            else
                v2.getChildren().add(new ImageView(new Image(getClass().getResource("/images/Avatars/"+i+".png").toString())));
        }
        for(Node node:v1.getChildren()){
            if(node instanceof ImageView){
                if(!node.equals(avatarView)){
                    ((ImageView) node).setFitHeight(30);
                    ((ImageView) node).setFitWidth(30);
                    try {
                        node.setOnMousePressed(mouseEvent -> avatarView.setImage(((ImageView) node).getImage()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        for(Node node:v2.getChildren()){
            if(node instanceof ImageView){
                if(!node.equals(avatarView)){
                    ((ImageView) node).setFitHeight(30);
                    ((ImageView) node).setFitWidth(30);
                    try {
                        node.setOnMousePressed(mouseEvent -> avatarView.setImage(((ImageView) node).getImage()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

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
        Button mainMenuBak=new Button();
        ImageView backButt=new ImageView(getClass().getResource("/images/icons/Ionic-Ionicons-Caret-back-circle-sharp.512.png").toString());
        backButt.setFitWidth(30);
        backButt.setFitHeight(30);
        mainMenuBak.setGraphic(backButt);
        mainMenuBak.setAlignment(Pos.BASELINE_CENTER);
        mainMenuBak.setOnMouseClicked(mouseEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                System.out.println("error in loading main menu form profile menu");
                throw new RuntimeException(e);
            }
        });
        info.getChildren().add(mainMenuBak);
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
        sloganBox.setAlignment(Pos.TOP_CENTER);
        sloganBox.setSpacing(25);
        sloganBox.setBorder(new Border(new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
        Label sloganLabel=new Label("slogan :");
        TextField newSlogan=new TextField();
        Label sloganCheck=new Label("");
        newSlogan.setPromptText("new slogan");
        newSlogan.setMinWidth(200);
        sloganBox.getChildren().addAll(sloganLabel,newSlogan,sloganCheck);
        ChangeInfo.getChildren().addAll(sloganBox);
        Button submit=new Button("save changes");
        ChangeInfo.getChildren().add(submit);
        //logic
        timeline=new Timeline(new KeyFrame(Duration.millis(16),actionEvent -> {
            if(changeNameField.getText().length()==0) {
                UsernameCheck.setText("");
                nameChane=true;
            }
            else if(changeNameField.getText().length()<4 || !changeNameField.getText().matches("[a-zA-Z0-9]+")){
                UsernameCheck.setText("Invalid format!");
                UsernameCheck.setTextFill(Color.YELLOW);
                nameChane=false;
            }
            else if(ApplicationManager.getUserByUsername(changeNameField.getText()) != null){
                UsernameCheck.setText("Username already exists!");
                UsernameCheck.setTextFill(Color.RED);
                nameChane=false;
            }
            else {
                UsernameCheck.setText("OK!");
                UsernameCheck.setTextFill(Color.GREEN);
                nameChane=true;
            }
            if(currentPassword.getText().length()==0 && newPassword.getText().length()==0){
                currPassCheck.setText("");
                newPassCheck.setText("");
                passChange=true;
            }
            /*else if(!currentPassword.getText().equals(ApplicationManager.getCurrentUser().getPassword())){
                currPassCheck.setText("Wrong Pass!");
                currPassCheck.setTextFill(Color.RED);
                passChange=false;
            }*/
            else if(!(newPassword.getText().matches(".*[@#%&*$!]+.*") && newPassword.getText().matches(".*[0-9]+.*") && newPassword.getText().matches(".*[a-zA-Z]+.*")) || newPassword.getText().length()<5){
                newPassCheck.setText("Weak format");
                newPassCheck.setTextFill(Color.YELLOW);
                passChange=false;
            }
            else{
                passChange=true;
                currPassCheck.setText("OK!");
                newPassCheck.setText("OK!");
                currPassCheck.setTextFill(Color.GREEN);
                newPassCheck.setTextFill(Color.GREEN);
            }if(newNickname.getText().length()==0){
                NicknameCheck.setText("");
                nickChange=true;
                NicknameCheck.setTextFill(Color.GREEN);
            }
            else if(newNickname.getText().length()<5){
                NicknameCheck.setText("Too Short");
                nickChange=false;
                NicknameCheck.setTextFill(Color.RED);
            }
            else{
                NicknameCheck.setText("OK");
                nickChange=true;
                NicknameCheck.setTextFill(Color.GREEN);
            }
            if(newemail.getText().length()==0){
                EmailCheck.setText("");
                emailCheck=true;
            }
            else if(!newemail.getText().matches(".+@.+\\..+")){
                EmailCheck.setTextFill(Color.RED);
                EmailCheck.setText("Invalide format");
                emailCheck=false;
            }
            else {
                EmailCheck.setTextFill(Color.GREEN);
                EmailCheck.setText("OK             ");
                emailCheck=true;
            }
            if(newSlogan.getText().length()==0){
                sloganCheck.setText("  ");
            }
            else{
                sloganCheck.setText("OK");
                sloganCheck.setTextFill(Color.GREEN);
            }


        }));
        timeline.setCycleCount(-1);
        timeline.play();
        submit.setOnMouseClicked(mouseEvent -> {
            if(nameChane && passChange && nickChange && emailCheck){
                try {
                    captchaMenu.start(new Stage());
                } catch (Exception e) {
                    System.out.println("errro in loading captch");
                }
                ApplicationManager.getCurrentUser().setAvatarAddress(avatarView.getImage());
                if(captchaMenu.getCanPass()){
                    if (changeNameField.getText().length() > 0)
                        ApplicationManager.getCurrentUser().setUsername(changeNameField.getText());
                    if(newPassword.getText().length()>0)
                        ApplicationManager.getCurrentUser().setPassword(newPassword.getText());
                    if(newNickname.getText().length()>0)
                        ApplicationManager.getCurrentUser().setNickname(newNickname.getText());
                    if(newemail.getText().length()>0)
                        ApplicationManager.getCurrentUser().setEmail(newemail.getText());
                    if(newSlogan.getText().length()>0)
                        ApplicationManager.getCurrentUser().setSlogan(newSlogan.getText());
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Success in changing information!\nyour changes will be shown in next time you run the profile menu");
                    alert.show();
                }
            }
        });
        //scoreboard
        Button back=new Button("<");
        Button next=new Button(">");
        VBox control=new VBox(back,next);
        control.setSpacing(25);
        control.setLayoutY(568);
        control.setLayoutX(1030);
        pane.getChildren().add(control);
        scoreboard.setMaxHeight(200);
        scoreboard.setMinWidth(820);
        scoreboard.setLayoutX(202);
        scoreboard.setLayoutY(528);
        Collections.sort(ApplicationManager.getUsers(), Comparator.comparingInt(User::getScore));
        final ObservableList<User> userObservableList= FXCollections.observableList(ApplicationManager.getUsers());
        TableColumn<User,Image> Avatars=new TableColumn("Avatar");
        Avatars.setCellValueFactory(cellData -> new SimpleObjectProperty<Image>(cellData.getValue().getAvatarImage()));
        Avatars.setMinWidth(100);
        Avatars.setMaxWidth(100);
        TableColumn<User,String> Usernames=new TableColumn("Username");
        Usernames.setCellValueFactory(cellData ->cellData.getValue().usernameProperty());
        Usernames.setMinWidth(150);
        Usernames.setMaxWidth(150);
        TableColumn<User,String>Slogans=new TableColumn("Slogan");
        Slogans.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSlogan()));
        Slogans.setMaxWidth(200);
        Slogans.setMinWidth(200);
        scoreboard.setItems(userObservableList);
        scoreboard.getColumns().addAll(Avatars,Usernames,Slogans);
        scoreboard.scrollTo(0);
        back.setOnMouseClicked(mouseEvent -> {
            counter-=5;
            if(counter<0)
                counter=0;
            scoreboard.scrollTo(counter);
        });
        next.setOnMouseClicked(mouseEvent -> {
            if(counter+5<scoreboard.getItems().size())
                counter+=5;
            scoreboard.scrollTo(counter);
        });
        pane.getChildren().add(scoreboard);
        pane.getChildren().add(ChangeInfo);
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
