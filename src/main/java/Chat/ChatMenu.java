package Chat;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ChatMenu  {
    private User loggedUser;
    private Socket socket;
    Graphic graphic;
    private ObjectOutputStream objectoutputStream;
    private ArrayList<Conversation> thisUserConversations;
    private VBox messages;
    TextField entry;
    Handler handler;
    private ScrollPane scrollPane;
    Conversation currnetConversation;
    LinkedList<Message> qeuedMessages;
    public ArrayList<Conversation> getThisUserConversations() {
        return thisUserConversations;
    }

    public ChatMenu(User loggedUser) throws IOException {
        socket=new Socket("localhost",2);
        objectoutputStream=new ObjectOutputStream(socket.getOutputStream());
        this.loggedUser = loggedUser;
        thisUserConversations=new ArrayList<>();

    }


    public ChatMenu() throws Exception {
        graphic=new Graphic();
        thisUserConversations=new ArrayList<>();
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 2);
                System.out.println("connected to server ? "+socket.isConnected());
                objectoutputStream=new ObjectOutputStream(socket.getOutputStream());
                System.out.println("stage 1");
                handler=new Handler(socket);
                System.out.println("stage2");
                handler.start();
                System.out.println("stage3");
            } catch (IOException e) {
                System.out.println("server went to chokh");
            }
        }).start();
        graphic.start(new Stage());

    }

    public static void main(String[] args) {

    }



    public void chatManager() throws IOException {
        //input
        new Thread(() -> {

        }).start();
    }

    public  void sendMessage(String in) throws IOException {
        if(in.length()==0)
            return;
        entry.setText("");
        Message message=new Message(in,"watch");
        objectoutputStream.writeObject(message);
        objectoutputStream.flush();
    }
    private class Handler extends Thread{
        private ObjectInputStream objectInputStream;
        public Handler(Socket socket) throws IOException {
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            System.out.println("success in sub_constructor");
        }
        @Override
        public void run(){
            try {
                objectInputStream=new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println("error in input");
            }
            System.out.println("input okay");
            while (socket.isConnected()){
                try {
                    Object obj=objectInputStream.readObject();
                    if(obj instanceof Message){
                        Message message=(Message) obj;
                        System.out.println(message.getContent()+"\n"+message.getStatus());

                    }
                    else{
                        System.out.println("nothing");
                    }
                } catch (IOException e) {
                    System.out.println("error in reading class");
                } catch (ClassNotFoundException e) {
                    System.out.println("error in reading data from server");
                }
            }
        }

    }
    public class Graphic extends Application{
        @Override
        public void start(Stage stage) throws Exception {
            Pane gridPane=new Pane();
            gridPane.setMinHeight(600);
            gridPane.setMaxHeight(600);
            gridPane.setMinWidth(800);
            gridPane.setMaxWidth(800);
            VBox chatsToShow=new VBox();
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
            entry =new TextField();
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
            imageView.setOnMouseClicked(mouseEvent -> {
                try {
                    sendMessage(entry.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            gridPane.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    try {
                        sendMessage(entry.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            stage.setScene(new Scene(gridPane));
            stage.show();
        }


    }
}
