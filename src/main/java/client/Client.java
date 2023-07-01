import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client() {
        outputStream = null;
        inputStream = null;
    }

    public void connect(String host, int port) throws UnknownHostException, IOException {
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server: " + socket);

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

        Thread thread = new Thread(new ServerHandler());
        thread.start();
    }

    public void sendGame(Game game) throws IOException {
        outputStream.writeObject(game);
        outputStream.reset();
    }

    private class ServerHandler implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Game game = (Game) inputStream.readObject();
                    System.out.println("Received game from server: " + game);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error handling server response.");
            }
        }
    }
}