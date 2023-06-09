package project;

import controller.*;
import model.ApplicationManager;
import model.Game;
import model.User;
import view.Menu;

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
        ApplicationManager.getUsers().add(new User("ahmad",SHA_256Format.sha256("123"),"bsgh","fghshgh","gfjsj","cgfajs",1));
        Matcher matcher = Menu.getMatcher("user login -u ahmad -p 123", Commands.LOGIN.getRegex());
        assert matcher != null;
        String out = LoginMenuController.Login(matcher);
        Assertions.assertEquals("User logged in Successfully!", out);

    }



    //profile menu
//    @Test
//    public void profileMenuTest() {
//        Scanner scanner = Menu.getScanner();
//        String in = scanner.nextLine();
//        Assertions.assertEquals(in, "in");
//    }

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

    //change password

    @Test
    public void changePasswordMachTest() {
        String testCommand = "profile change password -p qwe -n were";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());

        Assertions.assertNull(matcher);
    }
    @Test
    public void emptyPasswordTest() throws NoSuchAlgorithmException {
        // old password

        String testCommand = "profile change password -o  -n ";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }


        Assertions.assertEquals(result, "The old password is empty!");

        //new password

        testCommand = "profile change password -o amir -n ";
        matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }


        Assertions.assertEquals(result, "The new password is empty!");
    }
    @Test
    public void oldPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 2 -n amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "The current password is incorrect!");
    }
    @Test
    public void differenceBetweenOldPasswordAndNewPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n 1";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "Please enter a new password!");
    }
    @Test
    public void invalidLengthOfPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        System.out.println(result);
        Assertions.assertEquals(result, "The password is weak: The length of the password must be greater than 6!");
    }
    @Test
    public void anyCapitalLetterInPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n mohammad";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "The password is weak: at least one capital letter is required!");
    }
    @Test
    public void anySmallLetterInPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n MOHAMMAD";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "The password is weak: at least one small letter is required!");
    }
    @Test
    public void anyNumberInPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n Mohammad";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "The password is weak: at least one number is required!");
    }
    @Test
    public void anySpecialCharacterInPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1",SHA_256Format.sha256("1"),"1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change password -o 1 -n Mohammad1";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_PASSWORD.getRegex());
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_1(matcher);
        }

        Assertions.assertEquals(result, "The password is weak: at least one special character is required!");
    }
    @Test
    public void emptyRepeatingPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "";
        String newPassword = "@Mohammad1";
        Matcher matcher = Menu.getMatcher(testCommand, "(?<newPassword>[^\n]*)");
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_2(matcher, newPassword);
        }

        Assertions.assertEquals(result, "The repeating password is empty!");
    }
    @Test
    public void incorrectRepeatingPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "vsbrdtnfymgu";
        String newPassword = "@Mohammad1";
        Matcher matcher = Menu.getMatcher(testCommand, "(?<newPassword>[^\n]*)");
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_2(matcher, newPassword);
        }

        Assertions.assertEquals(result, "Repeating the password is wrong!");
    }
    @Test
    public void correctRepeatingPasswordTest() throws NoSuchAlgorithmException {
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "@Mohammad1";
        String newPassword = "@Mohammad1";
        Matcher matcher = Menu.getMatcher(testCommand, "(?<newPassword>[^\n]*)");
        String result = null;

        if (matcher != null) {
            result = ProfileMenuController.changePassword_2(matcher, newPassword);
        }

        Assertions.assertEquals(result, "Password changed successfully");
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
        User user = new User("1", "1", "1", "1", null, "1", 1);
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

    //display
    @Test
    public void displayWithEmptySloganTest() {
        User user = new User("1", "1", "1", "1", "", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile display";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.display();

        Assertions.assertEquals(result, "high score = 0\nrank: 0\nslogan: ");
    }
    @Test
    public void displayTest() {
        User user = new User("1", "1", "1", "1", "1", "1", 1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile display";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY.getRegex());
        String result = null;

        if (matcher != null)
            result = ProfileMenuController.display();

        System.out.println(result);
        Assertions.assertEquals(result, "high score = 0\nrank: 1\nslogan: 1");
    }

    // show map

    @Test
    public void showPriceListTest() {
        String testCommand = "profile display";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.DISPLAY.getRegex());
        String result = null;

        if (matcher != null)
            result = ShopMenuController.showPriceList();


        Assertions.assertEquals(result, "Foods:" + "\n" +
                "bread = 40\n"      +
                "rice = 160\n"      +
                "apple = 40\n"      +
                "meat = 40\n"       +
                "Primary sources:" + "\n" +
                "rock = 20\n"                    +
                "wood = 70\n"                    +
                "iron = 225\n"                   +
                "Weapon:" + "\n" +
                "blacksmith 100\n"     +
                "Fletcher 100\n"       +
                "Poleturner 100\n"     +
                "oil smelter 100");
    }

    @Test
    public void buyAndSellTest_1() {
        String testCommand = "buy -i bread -a -1";

        Matcher matcher = Menu.getMatcher(testCommand, Commands.BUY.getRegex());
        String result = null;

        if (matcher != null)
            result = ShopMenuController.buyAndSell(matcher, "buy");


        Assertions.assertEquals(result, "Invalid amount!");
    }

    @Test
    public void buyAndSellTest_2() {
        String testCommand = "sell -i bread -a -1";

        Matcher matcher = Menu.getMatcher(testCommand, Commands.SELL.getRegex());
        String result = null;

        if (matcher != null)
            result = ShopMenuController.buyAndSell(matcher, "sell");


        Assertions.assertEquals(result, "Invalid amount!");
    }

    @Test
    public void InvalidItemNameTest_buy() {
        User user = new User("1", "1", "1", "1", "", "1", 1);
        ArrayList<Government> governments = new ArrayList<>();
        governments.add(new Government(user, "blue", null));
        ApplicationManager.setCurrentGame(new Game(null, governments));

        String testCommand = "buy -i breed -a 1";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.BUY.getRegex());
        String result = null;

        if (matcher != null)
            result = ShopMenuController.buyAndSell(matcher, "buy");


        Assertions.assertEquals(result, "Invalid item's name!");
    }

    @Test
    public void InvalidItemNameTest_sell() {
        User user = new User("1", "1", "1", "1", "", "1", 1);
        ArrayList<Government> governments = new ArrayList<>();
        governments.add(new Government(user, "blue", null));
        ApplicationManager.setCurrentGame(new Game(null, governments));

        String testCommand = "sell -i breed -a 1";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.SELL.getRegex());
        String result = null;

        if (matcher != null)
            result = ShopMenuController.buyAndSell(matcher, "sell");


        Assertions.assertEquals(result, "Invalid item's name!");
    }
}