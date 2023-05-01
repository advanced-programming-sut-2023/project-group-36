package project.view;

import project.controller.Commands;
import project.controller.MapMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private final static Scanner scanner = Menu.getScanner();
    public static int y,x;
    public static void run(){

        String command,output ;
        Matcher matcher=null,matcher2;
        while (true){
            command= scanner.nextLine();
            if((matcher=Menu.getMatcher(command, Commands.SHOW_MAP.getRegex())) != null){
                 x=Integer.parseInt(matcher.group("x"))-1;
                 y=Integer.parseInt(matcher.group("y"))-1;
                    System.out.println(MapMenuController.showMap(x,y));
            }
            else if((matcher=Menu.getMatcher(command,Commands.SHOW_DETAILS.getRegex())) != null){
                 x=Integer.parseInt(matcher.group("x"))-1;
                 y=Integer.parseInt(matcher.group("y"))-1;
                    System.out.println(MapMenuController.showDetails(x,y));
            }
            else if((matcher2=Menu.getMatcher(command,Commands.MAP_TRANSFORMATION.getRegex())) != null){
                    if(matcher==null)
                        System.out.println("you haven't selected a cordinates before!");
                    else{
                        System.out.println(MapMenuController.newCordinates(matcher2));
                    }
            }
            else if(command.equals("exit")){

            }
            else{
                System.out.println("Invalid command!");
            }
        }
    }

}
