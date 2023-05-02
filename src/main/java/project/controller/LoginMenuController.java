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
        String password=matcher.group("password");
        password=SHA_256Format.sha256(password);
        if(!LoggedUser.getPassword().equals(password))
            return "Error: password doesn't match!";
        ApplicationManager.login(LoggedUser);
        return "User logged in Succesfully!";
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
    public static String passwordWeakCheck(String password) throws NoSuchAlgorithmException {
        if (password.length()<6){
            return "The password is weak: password length is short!";
        }
        if (!password.matches(".*[A-Z].*")){
            return "The password is weak: at least one capital letter is required!";
        }
        if (!password.matches(".*[a-z].*")){
            return "The password is weak: at least one small letter is required!";
        }
        if (!password.matches(".*[0-9].*")){
            return "The password is weak: at least one number is required!";
        }
        if (!password.matches(".*[#*\\-+&^%$@!.(){}].*")){
            return "The password is weak: at least one special character is required!";
        }
        LoggedUser.setPassword(SHA_256Format.sha256(password));
        return "The password is strong";
    }


}
