package Chat;

import model.ApplicationManager;
import model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Server{
    private boolean ServerIsRunning=false;
    private ArrayList<Conversation> conversations=new ArrayList<>() ;
    ArrayList<ClientHandler> users= new ArrayList<>();
    private ServerSocket serverSocket;
    Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystem","root","Group36@");
    Statement statement=connection.createStatement();
    public Server() throws IOException, SQLException {
        serverSocket=new ServerSocket(2);
    }

    public static void main(String[] args) throws SQLException, IOException {
        new Server().run();
    }

    public void run(){
        System.out.println("server is running");
        ServerIsRunning=true;
        try {
            while (!serverSocket.isClosed()) {
                Socket socket=serverSocket.accept();
                ClientHandler clientHandler=new ClientHandler(socket);
                users.add(clientHandler);
                System.out.println(socket.getPort() + " connected");
                clientHandler.run();
            }
        }catch (Exception e){
            e.printStackTrace();
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
    private class ClientHandler extends Thread{
        Socket socket;
        ObjectOutputStream ThisobjectOutputStream;
        ObjectInputStream ThisobjectInputStream;

        public ClientHandler(Socket socket) throws IOException {

            this.socket = socket;
            this.ThisobjectInputStream=new ObjectInputStream(socket.getInputStream());
            this.ThisobjectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        }

        public ObjectOutputStream getThisobjectOutputStream() {
            return ThisobjectOutputStream;
        }

        public ObjectInputStream getThisobjectInputStream() {
            return ThisobjectInputStream;
        }

        @Override
        public void run() {

                try {

                    while (socket.isConnected()){
                        Message message=(Message) getThisobjectInputStream().readObject();
                        System.out.println(message.getContent());
                        try {
                            for (ClientHandler clientHandler : users) {
                                clientHandler.ThisobjectOutputStream.writeObject(new Message(message.getContent(), "%%"));
                            }
                        }catch (Exception s){
                            System.out.println("something went wrong");
                            s.printStackTrace();
                        }
                    }
                    System.out.println("socket disconneted3");

                } catch (IOException e) {
                    System.out.println(socket.getPort() + " disconnected1");
                    //e.printStackTrace();

        } catch (ClassNotFoundException e) {
                    System.out.println(socket.getPort() + " disconnected2");
                }
        }

}
}
