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

    public String getUsername() {
        return username;
    }
}
