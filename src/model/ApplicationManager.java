package model;

import java.util.ArrayList;

public class ApplicationManager {

    private static ArrayList<User> users;
    private static ArrayList<User> sortedUsers;
    private static ArrayList<Game> games;
    private static Game currentGame;

    public static User getUserByUsername(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static void setCurrentGame(Game currentGame) {
        ApplicationManager.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }
}
