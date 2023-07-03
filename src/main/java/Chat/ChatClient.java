package Chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.*;

public class ChatClient extends Application {

    private static final DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(); // init to any available port
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private static final InetAddress address;

    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private User user;

    private static final int SERVER_PORT = 8000; // send to server

    private static final TextArea messageArea = new TextArea();

    private static final TextField inputBox = new TextField();


    public static void main(String[] args) throws IOException {

        // thread for receiving messages
        ClientThread clientThread = new ClientThread(socket, messageArea);
        clientThread.start();
        // send initialization message to the server
        byte[] uuid = ("init;" + address).getBytes();
        DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
        socket.send(initialize);

        launch(); // launch GUI

    }

    @Override
    public void start(Stage primaryStage) {
        messageArea.setStyle("-fx-font-family: Candara; -fx-font-size: 20px;-fx-text-fill: black");
        messageArea.setMaxWidth(500);
        messageArea.setEditable(false);
        inputBox.setMaxWidth(500);
        inputBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = (user==null ? "jeff":user.getUsername() )+ ":\n" + inputBox.getText()+"\n"; // message to send
                messageArea.setText("YOU:\n"+messageArea.getText() + inputBox.getText() + "\n");
               byte[] msg = temp.getBytes(); // convert to bytes
                inputBox.setText(""); // remove text from input box

                // create a packet & send
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
                try {
                    socket.send(send);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // put everything on screen
        Scene scene = new Scene(new VBox(35, messageArea, inputBox), 550, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
