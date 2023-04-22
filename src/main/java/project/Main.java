package project;
import project.model.*;
import project.view.LoginMenu;
import project.view.RegisterMenu;
import project.view.TradeMenu;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        /*
        Map map = new Map();
        User user1 = new User("ali","123","mosa","123$33","1ww","1",1);
        User user2 = new User("hasan","123","mosa","123$332","1ww","1",1);
        ApplicationManager.addUser(user1);
        ApplicationManager.addUser(user2);
        Government government1 = new Government(user1);
        Government government2 = new Government(user2);
        ArrayList<Government> governments = new ArrayList<>();
        governments.add(government1);
        governments.add(government2);
        Game game = new Game(governments,map);
        ApplicationManager.setCurrentGame(game);
        ApplicationManager.setCurrentUser(user2);
        TradeMenu.run();
        RegisterMenu.run();
         */
        LoginMenu.run();
        ApplicationManager.exit();
        //trade -u ali -t type1 -a 12 -p 15 -m salam
    }
}