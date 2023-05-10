package project.controller;

import project.model.ApplicationManager;
import project.model.User;

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
    public static String changePassword_1(Matcher matcher) {
        String oldPassword = matcher.group("old-password");
        String newPassword = matcher.group("new-password");
        String userPassword = ApplicationManager.getCurrentUser().getPassword();

        if (oldPassword.equals(""))
            return "The old password is empty!";
        if (newPassword.equals(""))
            return "The new password is empty!";

        if (!userPassword.equals(oldPassword))
            return "Current password is incorrect!";

        if (userPassword.equals(newPassword))
            return "Please enter a new password!";

        if (newPassword.length() < 6)
            return "Password must have at least 6 characters";
        if (!newPassword.matches(".*[a-z].*"))
            return "Password must have at least one lower case letter";
        if (!newPassword.matches(".*[A-Z].*"))
            return "Password must have at least one capital letter";
        if (!newPassword.matches(".*[\\d].*"))
            return "Password must have at least one number";
        if (!newPassword.matches(".*[\\W].*")) // is it correct?
            return "Password must have at least one character except numbers and upper and lower case letters";

        // fill out the capcha
        //...

        // repeat the password
        return "Please enter your new password again";
    }

    public static String changePassword_2(Matcher matcher, String newPassword) throws NoSuchAlgorithmException {
        String repeatingPassword = matcher.group("new-password");

        if (newPassword.equals(""))
            return "The repeating password is empty!";

        if (!repeatingPassword.equals(newPassword))
            return "Repeating the password is wrong!";
        newPassword=SHA_256Format.sha256(newPassword)       ;
    ApplicationManager.getCurrentUser().setPassword(newPassword);
        return "Password changed successfully";
    }

    public static String changeEmail(Matcher matcher, Matcher matcher1) {
        String email = matcher.group("email");

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

        if (slogan.equals("")) // always slogan is "" at first, when make a user without slogan !!!!!!!!!!!!!!!!!!!!!!!!!!!! sign up menu
            return "Slogan is empty!";

        return slogan;
    }

    public static String display() {
        String result;
        result = "high score = " + displayHighScore() + "\n";
        result += "rank: " + displayRank() + "\n";
        result += "slogan: ";

        if (!displaySlogan().equals("Slogan is empty!"))
            result += displaySlogan();

        return result;
    }
}
