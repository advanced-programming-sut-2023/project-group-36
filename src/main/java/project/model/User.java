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

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
