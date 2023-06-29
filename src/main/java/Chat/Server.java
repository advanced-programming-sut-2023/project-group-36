package Chat;

import model.ApplicationManager;
import model.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server extends Thread{
    private boolean ServerIsRunning=false;
    private ArrayList<Conversation> conversations=new ArrayList<>() ;
    ArrayList<User> users= ApplicationManager.getUsers();
    private ServerSocket serverSocket=new ServerSocket();
    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystem","root","Group36@");
    Statement statement=connection.createStatement();
    public Server() throws IOException, SQLException {
        serverSocket=new ServerSocket(666);
    }
    @Override
    public void run(){
        ServerIsRunning=true;
        try {
            while (!serverSocket.isClosed()) {
                Socket socket=serverSocket.accept();
            }
        }catch (Exception e){

        }
    }
    public void addChat(Conversation conversation){
        conversations.add(conversation);
        for(User user:conversation.getUsers()){
            user.getChatMenu().getThisUserConversations().add(0,conversation);
        }
    }
    public void sendMessage(Message message) throws IOException {
        Conversation conversation=message.getConversation();
        for(User user:conversation.getUsers()){
            ObjectOutputStream out =new ObjectOutputStream(user.getChatSocket().getOutputStream());
            out.writeObject(message);
        }
    }
}
