package project.view;

import project.controller.Commands;
import project.controller.CreateNewMapController;
import project.controller.EditMapController;
import project.model.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class EditMapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static Map map;
    public static int capacity;

    public static ArrayList<String> colors = new ArrayList<>();
    public static ArrayList<Government> governments = new ArrayList<>();
    public static Government government;
    public static int number;

    public static void run(int capacity, Map map, ArrayList<User> users) throws NoSuchAlgorithmException, InterruptedException {
        System.out.println("**<< EditMap Menu >>**");
        EditMapMenu.capacity = capacity;
        EditMapMenu.map = map;
        EditMapMenu.number = 0;
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (Tools.inputCheckFormat(input)!=null){
                input = Tools.inputCheckFormat(input);
            }
            if (input.matches(regex = Commands.DROP_ROCK.getRegex())){
                System.out.println(CreateNewMapController.dropRock(Menu.getMatcher(input,regex),map));
            }
            else if (input.matches(regex = Commands.DROP_TREE.getRegex())){
                System.out.println(CreateNewMapController.dropTree(Menu.getMatcher(input,regex),map));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE.getRegex())){
                System.out.println(CreateNewMapController.setTexture(Menu.getMatcher(input,regex),map));
            }
            else if (input.matches(regex = Commands.SET_TEXTURE_RECTANGLE.getRegex())){
                System.out.println(CreateNewMapController.setTextureRectangle(Menu.getMatcher(input,regex),map));
            }
            else if (input.matches(regex = Commands.SET_GOVERNMENT.getRegex())) {
                System.out.println(EditMapController.setGovernment(Menu.getMatcher(input,regex),map,users));
            }
            else if (input.matches(regex = Commands.DROP_UNIT.getRegex())){
                System.out.println(EditMapController.dropUnit(Menu.getMatcher(input,regex),government,map));
            }
            else if (input.matches(regex = Commands.DROP_BUILDING.getRegex())){
                System.out.println(EditMapController.dropBuilding(Menu.getMatcher(input,regex),government,map));
            }
            else if (input.matches("save map")){
                if((result = EditMapController.checkMapPreparation())!=null){
                    System.out.println(result);
                }
                else {
                    System.out.println("Map preparation done successfully.");
                    inThisMenu = false;
                    CreateNewGameMenu.mapPreparation = true;
                    CreateNewGameMenu.governments = governments;
                    CreateNewGameMenu.run(number);
                }
            }
            else if (input.equals("cancel")){
                System.out.println("Map preparation was canceled.");
                inThisMenu = false;
                CreateNewGameMenu.run(CreateNewGameMenu.capacity);
            }
            else if (input.matches(Commands.QUITGAME.getRegex())) {
                ApplicationManager.exit();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }

}
