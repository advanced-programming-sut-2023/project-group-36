package Chat;

import model.ApplicationManager;
import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Server {
    private boolean ServerIsRunning=false;
    private ArrayList<ChatMenu> chats=new ArrayList<>() ;
    ArrayList<User> users= ApplicationManager.getUsers();
    private ServerSocket serverSocket=new ServerSocket();
    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystem","root","Group36@");
    Statement statement=connection.createStatement();
    public Server() throws IOException, SQLException {

    }

    public void startServer(){
        ServerIsRunning=true;
        try {
            while (!serverSocket.isClosed()) {
                Socket socket=serverSocket.accept();
            }
        }catch (Exception e){

        }
    }
    public void addChat(ChatMenu chatMenu){
        chats.add(chatMenu);

    }
}
