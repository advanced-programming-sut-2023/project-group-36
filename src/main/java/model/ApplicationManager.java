package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class ApplicationManager {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();
    private static ArrayList<Map> maps = new ArrayList<>();
    private static Game currentGame;
    private static User currentUser;
    private static boolean stayLoggedIn;


    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    static MediaPlayer mediaPlayer=new MediaPlayer(new Media(ApplicationManager.class.getResource("/music/mokhtarnameh-barkhiz.mp3").toString()));
    public static void run(){
        mediaPlayer.setVolume(50);
        mediaPlayer.play();
    }

    public static Game getCurrentGame() {
        return getCurrentGame();
    }
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
        // save();
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
        //save();
        System.exit(1);
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }
    public static void save(){

    }

}
