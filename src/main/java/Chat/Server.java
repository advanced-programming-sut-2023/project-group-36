package Chat;

import model.ApplicationManager;
import model.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server extends Thread{
    private boolean ServerIsRunning=false;
    private ArrayList<Conversation> conversations=new ArrayList<>() ;
    ArrayList<Socket> users= new ArrayList<>();
    private ServerSocket serverSocket=new ServerSocket();
    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystem","root","Group36@");
    Statement statement=connection.createStatement();
    public Server() throws IOException, SQLException {
        serverSocket=new ServerSocket(1);
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
    public void addChat(Conversation conversation) throws IOException {
        conversations.add(conversation);
        for(Socket user:conversation.getUsers()){
            ObjectOutputStream outputStream=new ObjectOutputStream(user.getOutputStream());
            outputStream.writeObject(conversation);
            outputStream.flush();
        }
    }

}
