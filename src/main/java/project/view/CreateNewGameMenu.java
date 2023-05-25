package project.view;

import project.controller.Commands;
import project.controller.CreateNewGameController;
import project.model.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewGameMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static ArrayList<Government> governments = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static Map map;
    public static boolean mapPreparation = false;
    public static int capacity;

    public static void run(int count) throws NoSuchAlgorithmException, InterruptedException {
        System.out.println("**<< CreateNewGame Menu >>**");
        String input;
        String regex;
        String result;
        boolean inThisMenu = true;
        capacity = count;
        CreateNewGameController.setController(map,capacity,users);
        while (inThisMenu) {
            input = scanner.nextLine();
            if (Tools.inputCheckFormat(input)!=null){
                input = Tools.inputCheckFormat(input);
            }
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
                if (map!=null){
                    inThisMenu = false;
                    EditMapMenu.run(capacity,map,users);
                }
                else if (governments.size()!=capacity) {
                    System.out.println("Error: You still haven't decided the headquarters of all the governments!");
                }
                else {
                    System.out.println("Error: The map is null!");
                }
            }
            else if (input.matches("start game")){
                result = CreateNewGameController.startGame();
                if (result==null){
                    inThisMenu = false;
                    Game game = new Game(map, governments);
                    ApplicationManager.addGame(game);
                    ApplicationManager.setCurrentGame(game);
                    GameMenu.run(game);

                }
                else {
                    System.out.println(result);
                }
            }
            else if (input.equals("cancel")){
                inThisMenu = false;
                MainMenu.run();
            }
            else if (input.matches(Commands.QUIT_GAME.getRegex())){
                ApplicationManager.exit();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
