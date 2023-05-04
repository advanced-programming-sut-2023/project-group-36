package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;
import project.model.Game;
import project.model.Government;
import project.model.Map;
import project.model.User;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewGameMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static ArrayList<Government> governments = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static Map map = null;
    //public static Game game = new Game(map,governments);
    public static boolean mapPreparation = false;
    public static int capacity;

    public static void run(int count){
        System.out.println("**<< CreateNewGame Menu >>**");
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        capacity = count;
        CreateNewGameController.setController(map,capacity,users);
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
            else if (input.matches(Commands.MAP_PREPARATION.getRegex())){
                inThisMenu = false;
                EditMapMenu.run(capacity,map,users);
            }

            else if (input.matches("start game")){
                result = CreateNewGameController.startGame();
                if (result==null){
                    inThisMenu = false;
                    //GameMenu.run();
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
