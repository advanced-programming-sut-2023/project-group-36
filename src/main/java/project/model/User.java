package project.model;

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
    private int highScore;

    public User(String username, String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.questionAnswer = questionAnswer;
        this.questionNumber = questionNumber;
        score=0;
    }
    public void changeProfile(String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber){

    }

    public int getQuestionNumber() {
        return questionNumber;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHighScore() { return highScore; }

    public void addScore(int amount){
        score+=amount;
    }
}
