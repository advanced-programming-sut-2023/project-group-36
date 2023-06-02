package controller;
import model.ApplicationManager;
import model.User;
import java.util.ArrayList;

public class CreateNewGame {
    public static ArrayList<User> users = new ArrayList<>();


    public static String addUser(String username) {
        if (ApplicationManager.getUserByUsername(username)==null){
            return "Error: User not found!";
        }
        if (getUserByUsername(username)!=null){
            return "Error: User already has been added!";
        }
        users.add(ApplicationManager.getUserByUsername(username));
        return null;
    }


    private static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
