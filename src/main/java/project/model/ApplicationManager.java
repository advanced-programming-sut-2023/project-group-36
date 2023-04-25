package project.model;
import project.controller.SaveAndLoad;

import java.util.ArrayList;

public class ApplicationManager {

    private static ArrayList<User> users = new ArrayList<>();

    private static ArrayList<Game> games = new ArrayList<>();

    private static Game currentGame;
    private static User currentUser;
    private static boolean stayLoggedIn;

    private static ArrayList<Map> maps = new ArrayList<>();
    public static User getUserByUsername(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserByEmail(String email){
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public static Map getMapByName(String name){
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).getName().equals(name)){
                return maps.get(i);
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

    public static void addMap(Map map) {
        maps.add(map);
    }

    public static void start() {
        SaveAndLoad.GameInitialization();
    }

    public static void setMapsList(ArrayList<Map> raw) {
        maps = raw;
    }

    public static void setGamesList(ArrayList<Game> raw) {
        games = raw;
    }

    public static void addGame(Game game) {
        games.add(game);
    }


    public void sortUsers(){
        return;
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void login(User user){
        currentUser = user;
    }

    public static void logout(){
        currentUser = null;
        stayLoggedIn = false;
    }

    public static void exit(){
        if (!stayLoggedIn){
            currentUser = null;
        }
        SaveAndLoad.saveUsers(users);
        SaveAndLoad.saveGames(games);
        SaveAndLoad.saveMaps(maps);
        // && save date ...
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }
    public static void setUsersList(ArrayList<User> raw){
       users = raw;
    }



}
