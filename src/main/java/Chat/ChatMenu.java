package Chat;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;

public class ChatMenu extends Application implements Runnable {
    private User loggedUser;
    private ArrayList<Conversation> thisUserConversations;
    private VBox messages;
    private ScrollPane scrollPane;
    Conversation currnetConversation;
    public ArrayList<Conversation> getThisUserConversations() {
        return thisUserConversations;
    }

    public ChatMenu(User loggedUser) {
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
        entry.setMinWidth(600);
        entry.setMinHeight(50);
        entry.setLayoutX(200);
        entry.setLayoutY(550);
        gridPane.getChildren().add(entry);
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
