package model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;

    private int questionNumber;

    private String questionAnswer;
    private Game game;
    private int score;

    User(String username, String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){
        score=0;
    }
    public void changeProfile(String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){

    }

    public void startNewGame(Game game){
        this.game = game;
    }

    public void removeGame(Game game){
        this.game = null;
    }

    public Game getGame(){
        return game;
    }

    public int getScore(){
        return score;
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

    public void addScore(int amount){
        score+=amount;
    }

}
