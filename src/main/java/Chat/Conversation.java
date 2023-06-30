package Chat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.User;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Conversation  {
    private ObservableList<Message> messages;
    private ArrayList <Socket> users;
    private boolean Visible;
    private String type;

    public Conversation(ArrayList<Socket> users,boolean Visible,String type) {
        this.users = users;
        this.Visible=Visible;
        this.type=type;
        messages= FXCollections.observableArrayList();
    }

    public ObservableList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Socket> getUsers() {
        return users;
    }

    public String getType() {
        return type;
    }

    public boolean isVisible() {
        return Visible;
    }




}
