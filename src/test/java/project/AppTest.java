package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.controller.*;
import project.model.ApplicationManager;
import project.model.User;
import project.view.Menu;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class AppTest {
    @Test
    public void RegisterTest() {

        User user1 = new User("1", "1", "1", "1", "1", "1", 1);
        User user2 = new User("1", "1", "1", "1", "1", "1", 1);
        Assertions.assertEquals(user1, user2);
    }

    @Test
    public void PasswordTest() throws Exception {
        User user1 = new User("1", "1", "1", "1", "1", "1", 1);
        Assertions.assertEquals(SHA_256Format.sha256("1"), SHA_256Format.sha256(user1.getPassword()));

    }

    @Test
    public void UserInitializeTest() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("1", "1", "1", "1", "1", "1", 1));
        users.add(new User("2", "2", "2", "2", "2", "2", 2));
        users.add(new User("3", "3", "3", "3", "3", "3", 3));
        users.add(new User("4", "4", "4", "4", "4", "4", 4));
        SaveAndLoad.saveUsers(users);
        SaveAndLoad.gameInitialization();
        ArrayList<User> usersToTest = ApplicationManager.getUsers();
        Assertions.assertEquals(usersToTest, users);
    }

    @Test
    public void TestWithMock() throws NoSuchAlgorithmException {
        Matcher matcher = Menu.getMatcher("user login -u ahmad -p 123", Commands.LOGIN.getRegex());
        String out = LoginMenuController.Login(matcher);
        Assertions.assertEquals("User logged in Successfully!", out);

    }


    // change username

    @Test
    public void changeUsernameMachTest() {
        String testCommand = "profil change -u amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_USERNAME.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void emptyUsernameTest() {
        String testCommand = "profile change -u ";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_USERNAME.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeUsername(matcher);


        Assertions.assertEquals(result, "The username is empty!");
    }
    @Test
    public void invalidUsernameFormatTest() {
        String testCommand = "profile change -u @amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_USERNAME.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeUsername(matcher);


        Assertions.assertEquals(result, "The username format is invalid");
    }
    @Test
    public void changeUsernameTest() {
        User user = new User("1", "1", "1", "1", "1", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -u amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_USERNAME.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeUsername(matcher);


        Assertions.assertEquals(result, "Username changed successfully");
        Assertions.assertEquals(user.getUsername(), "amir");
    }

    // change nickname

    @Test
    public void changeNicknameMachTest() {
        String testCommand = "profile chage -n amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_NICKNAME.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void emptyNicknameTest() {
        String testCommand = "profile change -n ";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_NICKNAME.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeNickname(matcher);


        Assertions.assertEquals(result, "The nickname is empty!");
    }
    @Test
    public void invalidNicknameFormatTest() {
        String testCommand = "profile change -n #amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_NICKNAME.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeNickname(matcher);


        Assertions.assertEquals(result, "The nickname format is invalid");
    }
    @Test
    public void changeNicknameTest() {
        User user = new User("1", "1", "1", "1", "1", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -n amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_NICKNAME.getRegex());
        String result = null;

        if (matcher != null)
            result= ProfileMenuController.changeNickname(matcher);


        Assertions.assertEquals(result, "Nickname changed successfully");
        Assertions.assertEquals(user.getNickname(), "amir");
    }

    // change email

    @Test
    public void changeEmailMachTest() {
        String testCommand = "profile change -n amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void emptyEmailTest() {
        String testCommand = "profile change -e ";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changeEmail(matcher, null);
        }


        Assertions.assertEquals(result, "The email is empty!");
    }
    @Test
    public void emptyPart1Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e @gmail.com";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The first part of the email is empty!");
    }
    @Test
    public void emptyPart2Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e amir@.com";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The second part of the email is empty!");
    }
    @Test
    public void emptyPart3Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e amir@gmail.";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The third part of the email is empty!");
    }
    @Test
    public void invalidPart1Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e am#ir@gmail.com";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The format of the first part of the email is invalid!");
    }
    @Test
    public void invalidPart2Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e amir@gma%il.com";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The format of the second part of the email is invalid!");
    }
    @Test
    public void invalidPart3Test() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e amir@gmail.c!om";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "The format of the third part of the email is invalid!");
    }
    @Test
    public void changeEmailTest() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -e amir@gmail.com";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_EMAIL.getRegex());
        Matcher matcher1;
        String result = null;

        if (matcher != null) {
            matcher1 = Menu.getMatcher(matcher.group("email"), Commands.EMAIL.getRegex());
            assert matcher1 != null;
            result = ProfileMenuController.changeEmail(matcher, matcher1);
        }


        Assertions.assertEquals(result, "Email changed successfully");
        Assertions.assertEquals(user.getEmail(), "amir@gmail.com");
    }

    // change slogan

    @Test
    public void changeSloganMachTest() {
        String testCommand = "profile change s amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_SLOGAN.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void emptySloganTestInChangeSlogan() {
        String testCommand = "profile change slogan -s ";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_SLOGAN.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeSlogan(matcher);


        Assertions.assertEquals(result, "The slogan is empty!");
    }

    @Test
    public void changeSloganTest() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change slogan -s amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_SLOGAN.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.changeSlogan(matcher);


        Assertions.assertEquals(result, "Slogan changed successfully");
        Assertions.assertEquals(user.getSlogan(), "amir");
    }

    // remove slogan

    @Test
    public void removeSloganMachTest() {
        String testCommand = "profile remove Slogan";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.REMOVE_SLOGAN.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void removeSloganTest() {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile remove slogan";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.REMOVE_SLOGAN.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.removeSlogan();


        Assertions.assertEquals(result, "Slogan removed successfully");
        Assertions.assertEquals(user.getSlogan(), "");
    }

    // display high score

    @Test
    public void displayHighScoreMachTest() {
        String testCommand = "profile display high score";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY_HIGH_SCORE.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void displayHighScoreTest() {
        User user = new User("1", "1", "1", "1", "1", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile display highScore";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY_HIGH_SCORE.getRegex());
        int result = -1;

        if (matcher != null)
            result = ProfileMenuController.displayHighScore();

        Assertions.assertEquals(result, 0);
    }

    //display rank


    // display slogan

    @Test
    public void displaySloganMachTest() {
        String testCommand = "profile display Slogan";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY_SLOGAN.getRegex());


        Assertions.assertNull(matcher);
    }
    @Test
    public void emptySloganTestInDisplay() {
        User user = new User("1", "1", "1", "1", "", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile display slogan";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY_SLOGAN.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.displaySlogan();


        Assertions.assertEquals(result, "The slogan is empty!");
    }
    @Test
    public void displaySloganTest() {
        User user = new User("1", "1", "1", "1", "1", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile display slogan";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY_SLOGAN.getRegex());
        int result = -1;

        if (matcher != null)
            result = ProfileMenuController.displayHighScore();

        Assertions.assertEquals(result, 0);
    }


}

