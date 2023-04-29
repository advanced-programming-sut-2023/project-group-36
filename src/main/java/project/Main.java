package project;

import project.model.ApplicationManager;
import project.model.User;
import project.view.LoginMenu;


public class Main {
    public static void main(String[] args) throws InterruptedException {

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
        LoginMenu.run();
        User user1 = new User("ahmad","123","mosa","123$33","1ww","1",1);
        User user2 = new User("gholi","123","mosa","123$332","1ww","1",1);
        ApplicationManager.addUser(user1);
        ApplicationManager.addUser(user2);
        ApplicationManager.exit();

    }
}