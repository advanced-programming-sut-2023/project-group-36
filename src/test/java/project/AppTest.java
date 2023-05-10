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
    public void RegisterTest () {

        User user1 =new User("1","1","1","1","1","1",1);
        User user2=new User("1","1","1","1","1","1",1);
        Assertions.assertEquals(user1,user2);
    }
    @Test
    public void PasswordTest()throws Exception{
        User user1 =new User("1","1","1","1","1","1",1);
        Assertions.assertEquals(SHA_256Format.sha256("1"),SHA_256Format.sha256(user1.getPassword()));

    }
    @Test
    public void UserInitilazeTest(){
        ArrayList<User> users=new ArrayList<>();
        users.add(new User("1","1","1","1","1","1",1));
        users.add(new User("2","2","2","2","2","2",2));
        users.add(new User("3","3","3","3","3","3",3));
        users.add(new User("4","4","4","4","4","4",4));
        SaveAndLoad.saveUsers(users);
        SaveAndLoad.gameInitialization();
        ArrayList<User> usersToTest= ApplicationManager.getUsers();
        Assertions.assertEquals(usersToTest,users);
    }
    @Test
    public void TestWithMock() throws NoSuchAlgorithmException {
    Matcher matcher= Menu.getMatcher("user login -u ahmad -p 123", Commands.LOGIN.getRegex());
        String out=LoginMenuController.Login(matcher);
    Assertions.assertEquals("User logged in Successfully!",out);

    }

    @Test
    public void changeUsername(){
        User user = new User("1","1","1","1","1","1",1);
        ApplicationManager.setCurrentUser(user);
        String testCommand = "profile change -u amir";
        Matcher matcher = Menu.getMatcher(testCommand, Commands.CHANGE_USERNAME.getRegex());
        String result = ProfileMenuController.changeUsername(matcher);

        Assertions.assertEquals(result, "Username changed successfully");
        Assertions.assertEquals(user.getUsername(), "amir");
    }


}
