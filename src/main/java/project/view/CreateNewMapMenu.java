package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;
import project.controller.CreateNewMapController;
import project.model.Map;

import java.util.Scanner;

public class CreateNewMapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static int size;
    public static Map map;
    public static int capacity;

    public static int number;

    public static void run(int size, int capacity){
        System.out.println("**<< CreateNewMap Menu >>**");
        CreateNewMapMenu.number = 1;
        CreateNewMapMenu.size = size;
        CreateNewMapMenu.capacity = capacity;
        map = new Map();
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.SET_GOVERNMENT.getRegex())){
                System.out.println(CreateNewMapController.setGovernment(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.REMOVE_USER.getRegex())){
            }
            else if (input.matches(regex = Commands.CHOOSE_MAP.getRegex())){
            }
            else if (input.matches("create map")){
            }
            else if (input.matches("start game")){
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
