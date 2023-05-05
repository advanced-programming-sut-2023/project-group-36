package project.controller;
import project.model.ApplicationManager;
import project.model.User;
import project.view.Menu;
import sun.security.provider.SHA;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;

public class LoginMenuController {
   private static User LoggedUser;
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
    public static String ForgetPassword(Matcher matcher){
        String username=matcher.group("username");
        LoggedUser=ApplicationManager.getUserByUsername(username);
        if(LoggedUser==null)
            return "Error: username doesn't exist!";
        String answer= Menu.getScanner().next();
        if(!answer.equals(LoggedUser.getQuestionNumber()))
            return "Error: invalid security question answer!";
        return null;
    }

}
