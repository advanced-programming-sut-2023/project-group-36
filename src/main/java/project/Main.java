package project;

import project.model.ApplicationManager;
import project.view.CreateNewGameMenu;
import project.view.LoginMenu;

import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        /*
        System.out.println(ApplicationManager.getUsers());
        Map map = new Map(200,"map1");
        ApplicationManager.addMap(map);
        User user1 = new User("ahmad","123","mosa","123$33","1ww","1",1);
        User user2 = new User("gholi","123","mosa","123$332","1ww","1",1);
        ApplicationManager.addUser(user1);
        ApplicationManager.addUser(user2);
        Government government1 = new Government(user1,"red");
        Government government2 = new Government(user2,"blue");
        ArrayList<Government> governments = new ArrayList<>();
        governments.add(government1);
        governments.add(government2);
        Game game = new Game(map,governments);
        map.createBlacks();
        ApplicationManager.setCurrentGame(game);
        ApplicationManager.setCurrentUser(user2);
        ApplicationManager.addGame(game);
        System.out.println(ApplicationManager.getUsers());
        */

        ApplicationManager.start();

        //User user1 = new User("ali","#Group36","hashem","ali.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);
        //User user2 = new User("mohammad","#Group36","mozar","mohammad.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);
        //User user3 = new User("amir-mohammad","#Group36","rashid","amir.group36@sharif.ir","Fight for what's right, not what's easy","hichi",1);
        //ApplicationManager.addUser(user1);
        //ApplicationManager.addUser(user2);
        //ApplicationManager.addUser(user3);
        RegisterMenu.run();
        LoginMenu.run();
        //CreateNewMapMenu.run();
        //CreateNewGameMenu.run(3);
        ApplicationManager.exit();
        //user create -u ali -p ggf -s fd -n salam -c "dfgf irsgk" -email sdf
    }
}