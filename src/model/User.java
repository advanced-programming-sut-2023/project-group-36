package model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int questionNumber;
    private String questionAnswer;
    private ArrayList<Game> games;

    User(String username, String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){

    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public void changeProfile(String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){

    }

    public void startNewGame(Game game){
        games.add(game);
    }

    public void removeGame(Game game){
        games.remove(game);
    }


}
