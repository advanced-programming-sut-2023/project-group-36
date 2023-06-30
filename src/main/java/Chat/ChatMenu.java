package Chat;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatMenu extends Application implements Runnable {
    private User loggedUser;
    private Socket socket;
    private ArrayList<Conversation> thisUserConversations;
    private VBox messages;
    private ScrollPane scrollPane;
    Conversation currnetConversation;
    public ArrayList<Conversation> getThisUserConversations() {
        return thisUserConversations;
    }

    public ChatMenu(User loggedUser) throws IOException {
        socket=new Socket("localhost",1);
        this.loggedUser = loggedUser;
        thisUserConversations=new ArrayList<>();
        messages=new VBox();
    }

    public ChatMenu() {
        thisUserConversations=new ArrayList<>();
        messages=new VBox();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane gridPane=new Pane();
        gridPane.setMinHeight(600);
        gridPane.setMaxHeight(600);
        gridPane.setMinWidth(800);
        gridPane.setMaxWidth(800);
        VBox chatsToShow=chatListShow();
        chatsToShow.setMinWidth(200);
        chatsToShow.setMinHeight(600);
        chatsToShow.setLayoutX(0);
        chatsToShow.setLayoutY(0);
        chatsToShow.setBackground(Background.fill(Color.BLANCHEDALMOND));
        //chatsToShow=chatListShow();
        for(Node node:chatsToShow.getChildren()){
            if(node instanceof Label){
                ((Label) node).setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID,new CornerRadii(3),new BorderWidths(6))));
                node.setOnMousePressed(mouseEvent -> {

                });
            }
        }
        gridPane.getChildren().add(chatsToShow);
        scrollPane=new ScrollPane();
        scrollPane.setLayoutX(200);
        scrollPane.setLayoutY(0);
        scrollPane.setMinHeight(600);
        scrollPane.setMinWidth(600);
        scrollPane.setHmin(2000);
        scrollPane.setBackground(Background.fill(Color.BLUEVIOLET));
        gridPane.getChildren().add(scrollPane);
        TextField entry =new TextField();
        entry.setMinWidth(520);
        entry.setMinHeight(30);
        entry.setLayoutX(200);
        entry.setLayoutY(550);
        ImageView imageView=new ImageView(new Image(getClass().getResource("/images/icons/send-4008.png").toString()));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 10px;");
        HBox enetries=new HBox(entry,imageView);
        enetries.setAlignment(Pos.TOP_CENTER);
        enetries.setSpacing(15);
        gridPane.getChildren().add(enetries);
        enetries.setLayoutX(201);
        enetries.setLayoutY(540);
        enetries.setMaxWidth(610);
        enetries.setPadding(new Insets(10,10,5,10));
        enetries.setBorder(new Border(new BorderStroke(Color.SILVER, BorderStrokeStyle.SOLID,new CornerRadii(3),new BorderWidths(6))));

        messages.heightProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);
            }
        });
        stage.setScene(new Scene(gridPane));
        stage.show();
    }
    @Override
    public void run() {
        try {
            start(new Stage());
        } catch (Exception e) {
            System.out.println("error in loading chat");
            throw new RuntimeException(e);
        }
    }
    public VBox chatListShow(){
        if(thisUserConversations.size()==0)
            return new VBox();
        VBox res=new VBox();
        res.setAlignment(Pos.TOP_CENTER);
        res.setSpacing(25);
        res.setMinHeight(800);
        for(int i=0;i<thisUserConversations.size();i++){
            res.getChildren().add(new Label(thisUserConversations.get(i).getMessages().get(0).getContent()));
        }
        return res;
    }
}
