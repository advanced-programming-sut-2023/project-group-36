package project.view;

import project.controller.Commands;
import project.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(){
        Matcher matcher;
        boolean inThisMenu=true;
        System.out.println("**<< MainMenu >>**");
        while(inThisMenu) {
            String command = scanner.nextLine();
            String output;
            if (command.matches(Commands.START_GAME.getRegex())) {
                matcher=Menu.getMatcher(command,Commands.START_GAME.getRegex());
                System.out.println();
            }
            else if(command.matches(Commands.OPEN_GAME.getRegex())) {
                matcher = Menu.getMatcher(command, Commands.OPEN_GAME.getRegex());
                inThisMenu=false;
                System.out.println();
                //GameMenu.run();
            }
            else if(command.matches(Commands.PROFILE_MENU.getRegex())){
                inThisMenu=false;
                //ProfileMenu.run();
            }
            else if(command.matches(Commands.CREATE_MAP.getRegex())){
                inThisMenu=false;
                //createMap.run();
            }
            else if(command.matches(Commands.LOGOUT.getRegex())){
                inThisMenu=false;
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

}
