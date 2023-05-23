package project.controller;

import project.model.ApplicationManager;
import project.model.Map;
import project.model.User;
import project.view.CreateNewGameMenu;
import project.view.EditMapMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CreateNewGameController {
    private static Map map;
    private static ArrayList<User> users;
    private static int capacity;
    public static void setController(Map map, int capacity, ArrayList<User> users){
        CreateNewGameController.map = map;
        CreateNewGameController.capacity = capacity;
        CreateNewGameController.users = users;
    }
    public static String addUser(Matcher matcher){
        String username = matcher.group("username");
        if (ApplicationManager.getUserByUsername(username)==null){
            return "Error: User not found!";
        }
        if (users.size()==capacity){
            System.out.println(capacity);
            System.out.println(users.size());
            return "Error: The capacity is full!";
        }
        if (getUserByUsername(username)!=null){
            return "Error: User already has been added!";
        }
        users.add(ApplicationManager.getUserByUsername(username));
        return "User added successfully.";
    }

    public static String removeUser(Matcher matcher){
        String username = matcher.group("username");
        if (getUserByUsername(username)==null){
            return "Error: User not found!";
        }
        users.remove(getUserByUsername(username));
        return "User removed successfully.";
    }

    public static String chooseMap(Matcher matcher){
        String name = matcher.group("mapName");
        if (ApplicationManager.getMapByName(name)==null){
            return "Error: Map not found!";
        }
        map = ApplicationManager.getMapByName(name).clone();
        CreateNewGameMenu.map = map;

        return "Map chosen successfully.";
    }


    private static User getUserByUsername(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static String startGame() {
        if (map==null){
            return "Error: To start the game, you need to choose a map for it!";
        }
        if (users.size()!=EditMapMenu.number){
            return "Error: The number of users selected by you is" + users.size()+
                    ",but the map you have selected is suitable for "+ EditMapMenu.number +" players.";
        }

        return null;
    }

}
