package controller;
import model.ApplicationManager;
import model.Government;
import model.User;
import java.util.ArrayList;

public class CreateNewGame {
    public static ArrayList<Government> governments = new ArrayList<>();


    public static String addUser(String username, String color) {
        if (ApplicationManager.getUserByUsername(username)==null){
            return "Error: User not found!";
        }
        if (getUserByUsername(username)!=null){
            return "Error: User already has been added!";
        }
        Government government = new Government(ApplicationManager.getUserByUsername(username),color);
        governments.add(government);
        if (governments.size()==1){
            GameController.setCurrentGovernment(government);
        }
        return null;
    }


    private static User getUserByUsername(String username) {
        for (Government government : governments) {
            if (government.getOwner().getUsername().equals(username)) {
                return government.getOwner();
            }
        }
        return null;
    }

}
