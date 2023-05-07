package project;

import project.model.ApplicationManager;
import project.view.LoginMenu;
import project.view.RegisterMenu;

import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        ApplicationManager.start();

        //User user1 = new User("ali","#Group36","hashem","ali.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);
        //User user2 = new User("mohammad","#Group36","mozar","mohammad.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);
        //User user3 = new User("amir-mohammad","#Group36","rashid","amir.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);

        LoginMenu.run();

        ApplicationManager.exit();
    }
}