package project.view;

import project.controller.Commands;
import project.controller.MapMenuController;
import project.model.ApplicationManager;
import project.model.Tools;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static int y=-1,x=-1;
    public static void run() throws NoSuchAlgorithmException, InterruptedException {

        String command,output ;
        Matcher matcher=null,matcher2;

        while (true){
            System.out.println("Map menu");
            command= scanner.nextLine();
            if (Tools.inputCheckFormat(command)!=null){
                command = Tools.inputCheckFormat(command);
            }

            if((matcher=Menu.getMatcher(command, Commands.SHOW_MAP.getRegex())) != null){
                 x=Integer.parseInt(matcher.group("x"));
                 y=Integer.parseInt(matcher.group("y"));
                 System.out.println(MapMenuController.showMap(x,y));
            }
            else if((matcher=Menu.getMatcher(command,Commands.SHOW_DETAILS.getRegex())) != null){
                 x=Integer.parseInt(matcher.group("x"));
                 y=Integer.parseInt(matcher.group("y"));
                 System.out.println(MapMenuController.showDetails(x,y));
            }
            else if((matcher=Menu.getMatcher(command,Commands.MAP_TRANSFORMATION.getRegex())) != null){
                    if(x==-1)
                        System.out.println("you haven't selected a cordinates before!");
                    else{
                        System.out.println(MapMenuController.newCordinates(matcher));
                    }
            }
            else if(command.equals("exit")){
                x=-1;
                y=-1;
                GameMenu.run(ApplicationManager.getCurrentGame());
            }
            else if (command.matches(Commands.QUITGAME.getRegex())) {
                ApplicationManager.exit();
            }
            else{
                System.out.println("Invalid command!");
            }
        }
    }

}
