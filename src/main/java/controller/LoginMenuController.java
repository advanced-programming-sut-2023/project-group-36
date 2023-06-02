package controller;

import model.ApplicationManager;
import model.User;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

public class LoginMenuController {
   public static User LoggedUser;
    public static String Login(Matcher matcher) throws NoSuchAlgorithmException {
        String username=matcher.group("username");
        LoggedUser=ApplicationManager.getUserByUsername(username);
        if(LoggedUser==null)
            return "Error: username doesn't exist!";
        String password=SHA_256Format.sha256(matcher.group("password"));
        if(!LoggedUser.getPassword().equals(password))
            return "Error: password doesn't match!";
        ApplicationManager.login(LoggedUser);
        return "User logged in Successfully!";
    }
    public static String ForgetPassword(String answer){
        if(!answer.equals(LoggedUser.getQuestionAnswer()))
            return "Error: invalid security question answer!";
        return null;
    }

}
