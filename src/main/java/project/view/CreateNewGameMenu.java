package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;

import java.util.Scanner;

public class CreateNewGameMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(){
        System.out.println("**<< CreateNewGame Menu >>**");
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.ADD_USER.getRegex())){
                System.out.println(CreateNewGameController.addUser(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.REMOVE_USER.getRegex())){
                System.out.println(CreateNewGameController.removeUser(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.CHOOSE_MAP.getRegex())){
                System.out.println(CreateNewGameController.chooseMap(Menu.getMatcher(input,regex)));
            }
            else if (input.matches("create map")){
                inThisMenu = false;
                //...
            }
            else if (input.matches("start game")){
                result = CreateNewGameController.startGame();
                if (result==null){
                    inThisMenu = false;
                    GameMenu.run();
                }
                else {
                    System.out.println(result);
                }
            }
            else if (input.equals("cancel")){
                inThisMenu = false;
                MainMenu.run();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
