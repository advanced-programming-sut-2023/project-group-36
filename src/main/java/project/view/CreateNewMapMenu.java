package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;
import project.controller.CreateNewMapController;
import project.model.ApplicationManager;
import project.model.Map;

import java.util.Scanner;

public class CreateNewMapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static Map map;

    public static void run(){
        System.out.println("**<< CreateNewMap Menu >>**");
        System.out.println("Choose map size:(enter 0 for cancel)\n1. 200x200\n2. 400x400");
        int size = Menu.getScanner().nextInt();
        while (size!=1 && size!=2){
            if (size==0){
                MainMenu.run();
                break;
            }
            size = Menu.getScanner().nextInt();
        }
        System.out.println("You have successfully determined the dimensions of the map.");
        size*=200;
        map=new Map(size,null);
        String input;
        String regex;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(regex = Commands.SET_MAP_NAME.getRegex())){
                System.out.println(CreateNewMapController.setMapName(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.DROP_ROCK.getRegex())){
                System.out.println(CreateNewMapController.dropRock(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.DROP_TREE.getRegex())){
                System.out.println(CreateNewMapController.dropTree(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE.getRegex())){
                System.out.println(CreateNewMapController.setTexture(Menu.getMatcher(input,regex)));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE_RECTANGLE.getRegex())){
                System.out.println(CreateNewMapController.setTextureRectangle(Menu.getMatcher(input,regex)));
            }

            else if (input.matches("save map")){
                if (map.getName()==null){
                    System.out.println("You cannot save the map because you have not chosen a name for it yet!");
                }
                else {
                    System.out.println("The map was successfully created.");
                    inThisMenu = false;
                    ApplicationManager.addMap(map);
                    MainMenu.run();
                }
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
