package Chat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    Pane pane=new Pane();
    private static final VBox messageArea = new VBox();

    public ChatClient(User user) {
        this.user = user;
    }

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
        inputBox.setMaxWidth(500);

        inputBox.setMinWidth(480);
        inputBox.setLayoutY(40);
        ScrollPane scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.getChildren().add(inputBox);
        scrollPane.setMinWidth(520);
        scrollPane.setLayoutY(75);
        scrollPane.setMinHeight(500);
        pane.getChildren().add(scrollPane);
        pane.setPadding(new Insets(10,15,10,15));
        pane.setMinWidth(700);
        pane.setMinHeight(520);
        VBox chatsList=new VBox();
        ScrollPane chats=new ScrollPane(chatsList);
        chats.setLayoutX(540);
        chats.setMinHeight(500);
        chats.setMinWidth(200);
        chats.setLayoutY(25);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.getChildren().add(chats);
        MenuBar menuBar=new MenuBar();
        Menu menu= new Menu("Menu");
        MenuItem create=new MenuItem("create new chat");
        create.setOnAction(actionEvent -> {
            try {
                new CreateNewConversation(this).start(new Stage());
            } catch (Exception e) {
                System.out.println("error in loading");
            }
        });
        menu.getItems().add(create);
        menuBar.getMenus().add(menu);
        menuBar.setLayoutX(0);
        menuBar.setMinWidth(700);
        pane.getChildren().add(menuBar);
        inputBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = (user==null ? "jeff":user.getUsername() )+ ":\n" + inputBox.getText()+"\n"; // message to send
                messageArea.getChildren().add(new HBox(new Label("YOU:\n" + inputBox.getText() + "\n")));
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
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
