package server;

import model.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private Game game;
    private List<ObjectOutputStream> outputStreams;

   /* public Server() {
        game = new Game();
        outputStreams = new ArrayList<>();
    }*/

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started.");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected: " + socket);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStreams.add(outputStream);

            Thread thread = new Thread(new ClientHandler(socket, outputStream));
            thread.start();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket socket, ObjectOutputStream outputStream) {
            this.socket = socket;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    Game receivedGame = (Game) inputStream.readObject();
                    System.out.println("Received game from client: " + socket);

                    synchronized (game) {
                        game = receivedGame;
                    }

                    for (ObjectOutputStream outputStream : outputStreams) {
                        outputStream.writeObject(game);
                        outputStream.reset();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error handling client: " + socket);
            } finally {
                try {
                    outputStreams.remove(outputStream);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
