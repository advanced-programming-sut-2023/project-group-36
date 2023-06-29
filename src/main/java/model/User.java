package model;

import Chat.ChatMenu;
import Chat.Conversation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class User {
    private Socket chatSocket;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;

    private int questionNumber;

    private String questionAnswer;
    private Image avatarImage;

    public Image getAvatarImage() {
        return avatarImage;
    }
    public StringProperty usernameProperty(){
        return new SimpleStringProperty(username);
    }

    public void setAvatarAddress(Image image) {
        avatarImage = image;
    }

    private Game game;
    private int score;
    private int highScore;
    private ChatMenu chatMenu;

    public User(String username, String password, String nickname, String email, String slogan, String questionAnswer, int questionNumber) throws IOException {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.questionAnswer = questionAnswer;
        this.questionNumber = questionNumber;
        score=0;
        chatSocket=new Socket("localhost",666);
        chatMenu=new ChatMenu(this);
    }

    public Socket getChatSocket() {
        return chatSocket;
    }

    public ChatMenu getChatMenu() {
        return chatMenu;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return questionNumber == user.questionNumber && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(slogan, user.slogan) && Objects.equals(questionAnswer, user.questionAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, nickname, email, slogan, questionNumber, questionAnswer);
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }
}
