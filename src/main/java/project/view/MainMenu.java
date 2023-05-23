package project.view;

import project.controller.Commands;
import project.model.ApplicationManager;
import project.model.Tools;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run() throws NoSuchAlgorithmException, InterruptedException {
        Matcher matcher;
        boolean inThisMenu=true;
        System.out.println("**<< MainMenu >>**");
        while(inThisMenu) {
            String command = scanner.nextLine();
            if (Tools.inputCheckFormat(command)!=null){
                command = Tools.inputCheckFormat(command);
            }
            if (command.matches(Commands.START_GAME.getRegex())) {
                matcher=Menu.getMatcher(command,Commands.START_GAME.getRegex());
                int count = Integer.parseInt(matcher.group("usersNumber"));
                if (count<2 || count>8){
                    System.out.println("Error: Invalid number!");
                    continue;
                }
                CreateNewGameMenu.run(count);
            }
            else if(command.matches(Commands.OPEN_GAME.getRegex())) {
                if(ApplicationManager.getCurrentUser().getGame() == null){
                    System.out.println("you haven't selected any game yet!");
                    continue;
                }
                inThisMenu=false;
                GameMenu.run(ApplicationManager.getCurrentUser().getGame());
            }
            else if(command.matches(Commands.PROFILE_MENU.getRegex())){
                inThisMenu=false;
                ProfileMenu.run();
            }
            else if(command.matches(Commands.CREATE_MAP.getRegex())){
                inThisMenu=false;
                CreateNewMapMenu.run();
            }
            else if(command.matches(Commands.LOGOUT.getRegex())){
                inThisMenu=false;
                ApplicationManager.setCurrentUser(null);
                LoginMenu.run();
            }
            else if (command.matches(Commands.QUITGAME.getRegex())) {
                ApplicationManager.exit();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

}
