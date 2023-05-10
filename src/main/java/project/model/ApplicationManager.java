package project.model;

import project.controller.SaveAndLoad;

import java.util.ArrayList;

public class ApplicationManager {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();

    private static ArrayList<Map> maps = new ArrayList<>();

    private static Game currentGame;
    private static User currentUser;
    private static boolean stayLoggedIn;

    private final int[] pricesOfSalable = {100, 250, 400, 1000};
/*
    engineer guild 100 انواع و اجزای قلعه ها
    Inn 100 فرآوریی غذا
    Church 250 سازه های شهری
    Cathedral 1000 سازه های شهری
    armourer 100 سازه...
    blacksmith 100 سلاح
    Fletcher 100 سلاح
    Poleturner 100 سلاح
    oil smelter 100 سلاح
    stable 400 انواع و اجزای قلعه
*/

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
        for (Map map : maps) {
            if (map.getName().equals(name)) {
                return map;
            }
        }
        return null;
    }

    public static void addUser(User user){
        users.add(user);
        save();
    }

    public static void addMap(Map map) {
        maps.add(map);
        save();
    }

    public static void addGame(Game game){
        games.add(game);
        save();
    }

    public static void setCurrentGame(Game currentGame) {
        ApplicationManager.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static int getRank(User user) {
        if (users.size() == 0)
            return 0;

        sortUsers();
        return users.indexOf(user) + 1;

    }

    private static void sortUsers() {
        User user;
        User user1;
        User user2;
        for (int i = 0;  i < users.size(); i++) {
            for (int j = i; j < users.size() - 1; j++) {
                user = users.get(j);
                user1 = users.get(j + 1);
                if (user.getScore() < user1.getScore()) {
                    user2 = user;
                    user = user1;
                    user1 = user2;
                }
            }
        }
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
    public static void setGamesList(ArrayList<Game> games) {
        ApplicationManager.games = games;
    }

    public static void setMapsList(ArrayList<Map> maps) {
        ApplicationManager.maps = maps;
    }

    public static void setUsersList(ArrayList<User> users) {
        ApplicationManager.users = users;
    }
    public static void exit(){
        if (!stayLoggedIn){
            currentUser = null;
        }
        save();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }


    public static void start() {
        Types.addPeopleTypes();
        Types.addBuildingsTypes();
        SaveAndLoad.gameInitialization();
    }

    public static void save(){
        SaveAndLoad.save(users,maps,games);
    }

    public static void setStayLoggedIn(boolean b) {
        stayLoggedIn = true;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
