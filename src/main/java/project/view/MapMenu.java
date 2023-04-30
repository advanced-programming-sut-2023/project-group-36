package project.view;

import project.controller.Commands;
import project.controller.MapMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(){

        String command,output ;
        Matcher matcher;
        while (true){
            command= scanner.nextLine();
            if((matcher=Menu.getMatcher(command, Commands.SHOW_MAP.getRegex())) != null){
                    System.out.println(MapMenuController.showMap(matcher));
            }
            else if((matcher=Menu.getMatcher(command,Commands.SHOW_DETAILS.getRegex())) != null){

            }
            else if((matcher=Menu.getMatcher(command,Commands.MAP_TRANSFORMATION.getRegex())) != null){

            }
            else if(command.equals("exit")){

            }
            else{
                System.out.println("Invalid command!");
            }
        }
    }

}
