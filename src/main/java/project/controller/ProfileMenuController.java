package project.controller;

import project.model.ApplicationManager;
import project.model.Tools;
import project.model.User;
import project.view.Menu;
import project.view.ShopMenu;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

public class ProfileMenuController {
    public static String changeUsername(Matcher matcher) {
        String username = matcher.group("username");
        if (username.equals("")) {
            return "The username is empty!";
        }

        if (!username.matches("[A-Za-z_]+"))
            return "The username format is invalid";
        if(ApplicationManager.getUserByUsername(username) != null)
            return "This username has already been taken!";
        ApplicationManager.getCurrentUser().setUsername(username);
        return "Username changed successfully";
    }

    public static String changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");

        if (nickname.equals("")) 
            return "The nickname is empty!";

        if (!nickname.matches("[A-Za-z_]+"))
            return "The nickname format is invalid";

        ApplicationManager.getCurrentUser().setNickname(nickname);
        return "Nickname changed successfully";
    }
    public static String changePassword_1(Matcher matcher) throws NoSuchAlgorithmException {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        if (oldPassword.equals("")) {
            return "The old password is empty!";
        }
        if (newPassword.equals("")) {
            return "The new password is empty!";
        }

        String userPassword = ApplicationManager.getCurrentUser().getPassword();

        if (!userPassword.equals(SHA_256Format.sha256(oldPassword)))
            return "The current password is incorrect!";

        if (newPassword.equals(oldPassword))
            return "Please enter a new password!";

        if (!Tools.passwordWeakCheck(newPassword).equals("Good"))
            return Tools.passwordWeakCheck(newPassword);

        String captchaNumbers = Tools.captcha();
        while (!Menu.getScanner().nextLine().equals(captchaNumbers)){
            System.out.println("Error: You entered the CAPTCHA code incorrectly!");
            captchaNumbers = Tools.captcha();
        }

        // repeat the password
        return "Please enter your new password again";
    }

    public static String changePassword_2(Matcher matcher, String newPassword) throws NoSuchAlgorithmException {
        String repeatingPassword = matcher.group("newPassword");

        if (repeatingPassword.equals(""))
            return "The repeating password is empty!";

        if (!repeatingPassword.equals(newPassword))
            return "Repeating the password is wrong!";
        newPassword=SHA_256Format.sha256(newPassword);
        ApplicationManager.getCurrentUser().setPassword(newPassword);
        return "Password changed successfully";
    }

    public static String changeEmail(Matcher matcher, Matcher matcher1) {
        String email = matcher.group("email");
        if (!email.matches(Commands.EMAIL.getRegex())){
            return "Invalid email format!";
        }

        if (email.equals("")) {
            return "The email is empty!";
        }

        String part1 = matcher1.group("part1");
        String part2 = matcher1.group("part2");
        String part3 = matcher1.group("part3");

        if (part1.equals("")) {
            return "The first part of the email is empty!";
        }
        if (part2.equals("")) {
            return "The second part of the email is empty!";
        }
        if (part3.equals("")) {
            return "The third part of the email is empty!";
        }

        if (!part1.matches("[A-Za-z_]+"))
            return "The format of the first part of the email is invalid!";
        if (!part2.matches("[A-Za-z_]+"))
            return "The format of the second part of the email is invalid!";
        if (!part3.matches("[A-Za-z_]+"))
            return "The format of the third part of the email is invalid!";

        ApplicationManager.getCurrentUser().setEmail(email);
        return "Email changed successfully";
    }

    public static String changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");

        if (slogan.equals(""))
            return "The slogan is empty!";

        ApplicationManager.getCurrentUser().setSlogan(slogan);
        return "Slogan changed successfully";
    }
    public static String removeSlogan() {
        ApplicationManager.getCurrentUser().setSlogan("");
        return "Slogan removed successfully";
    }

    public static int displayHighScore() {
        return ApplicationManager.getCurrentUser().getHighScore();
    }

    public static int displayRank() {
    return ApplicationManager.getRank(ApplicationManager.getCurrentUser());
    }

    public static String displaySlogan() {
        User user = ApplicationManager.getCurrentUser(); //this error for why???
        String slogan = user.getSlogan();

        if (slogan.equals(null)) // always slogan is "" at first, when make a user without slogan !!!!!!!!!!!!!!!!!!!!!!!!!!!! sign up menu
            return "The slogan is empty!";

        return slogan;
    }

    public static String display() {
        String result;
        result = "high score = " + displayHighScore() + "\n";
        result += "rank: " + displayRank() + "\n";
        result += "slogan: ";

        if (!displaySlogan().equals("The slogan is empty!"))
            result += displaySlogan();

        return result;
    }
}
