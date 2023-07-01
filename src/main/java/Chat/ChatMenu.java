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
    private MainG mainG;
    private Socket socket;
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
        thisUserConversations=new ArrayList<>();
        mainG=new MainG(this);
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
        mainG.start(new Stage());
    }
    public static void main(String[] args) {
        Application.launch(MainG.class, args);
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
}
