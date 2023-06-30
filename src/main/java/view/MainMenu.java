package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ApplicationManager;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        pane.setMinWidth(800);
        pane.setMinHeight(600);
        pane.setStyle("-fx-font-family: Arial; -fx-font-size: 18px; -fx-text-fill: #ffffff");
        BackgroundImage myBI1= new BackgroundImage(new Image(LoginMenu.class.getResource("/images/34192.jpg").toString(),800,600,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI1));
        VBox vBox=new VBox();
        Button createGame=new Button("Create New Game");
        Button GamesList=new Button("Games List");
        Button profile=new Button("Profile");
        Button logout=new Button("Logout");
        Button exit=new Button("Exit");
        vBox.getChildren().addAll(createGame,GamesList,profile,logout,exit);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(5,10,5,10));
        vBox.setSpacing(25);
        vBox.setLayoutX(100);
        vBox.setLayoutY(50);
        for(Node node:vBox.getChildren()){
            if(node instanceof Button){
                ((Button) node).setTextFill(Color.WHITE);
                ((Button) node).setBackground(Background.fill(Color.TRANSPARENT));
                ((Button) node).setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(3))));
                ((Button) node).setMinWidth(200);
                ((Button) node).setMinHeight(25);
            }
        }
        pane.getChildren().add(vBox);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        createGame.setOnMouseClicked(mouseEvent -> {
            try {
                new CreateNewGameMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error in loading create new game menu");
            }
        });
        GamesList.setOnMouseClicked(mouseEvent -> {
            try {
                new GameMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error in loading game menu");
            }
        });
        profile.setOnMouseClicked(mouseEvent -> {
            try {
                new ProfileMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error in loading profile menu");
            }
        });
        logout.setOnMouseClicked(mouseEvent -> {
            ApplicationManager.setCurrentUser(null);
            try {
                new LoginMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error in loading loginMenu");
            }
        });
        exit.setOnMouseClicked(mouseEvent ->{
            ApplicationManager.exit();
        });

    }
}
