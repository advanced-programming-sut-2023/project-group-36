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

    public static void run(int size){
        System.out.println("**<< CreateNewMap Menu >>**");
        CreateNewMapMenu.size = size;
        map=new Map(size,null);
        String input;
        String regex;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.SET_MAP_NAME.getRegex())){
                CreateNewMapController.setMapName(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.DROP_ROCK.getRegex())){
                CreateNewMapController.dropRock(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.DROP_TREE.getRegex())){
                CreateNewMapController.dropTree(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE.getRegex())){
                CreateNewMapController.setTexture(Menu.getMatcher(input,regex));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE_RECTANGLE.getRegex())){
                CreateNewMapController.setTextureRectangle(Menu.getMatcher(input,regex));
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
