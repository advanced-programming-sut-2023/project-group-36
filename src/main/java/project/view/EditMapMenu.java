package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;
import project.controller.CreateNewMapController;
import project.model.Map;

import java.util.Scanner;

public class EditMapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static int size;
    public static Map map;
    public static int capacity;

    public static void run(int size, int capacity, Map map){
        System.out.println("**<< EditMap Menu >>**");
        EditMapMenu.size = size;
        EditMapMenu.capacity = capacity;
        EditMapMenu.map = map;
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.SET_MAP_NAME.getRegex())){

            }
            else if (input.matches(regex = Commands.DROP_ROCK.getRegex())){


            }
            else if (input.matches(regex = Commands.DROP_TREE.getRegex())){

            }

            else if (input.matches(regex = Commands.DROP_UNIT.getRegex())){

            }

            else if (input.matches(regex = Commands.DROP_BUILDING.getRegex())){

            }

            else if (input.matches("save map")){
                System.out.println("The map was successfully created.");
                inThisMenu = false;
                MainMenu.run();
            }
            else if (input.equals("cancel")){
                System.out.println("Map creation was canceled.");
                inThisMenu = false;
                MainMenu.run();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
