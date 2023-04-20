package project.controller;

import project.model.Map;
import project.view.CreateNewMapMenu;
import project.view.Menu;

import java.util.regex.Matcher;

public class CreateNewMapController {
    public static String setGovernment(Matcher matcher) {
        int number = CreateNewMapMenu.number;
        int capacity = CreateNewMapMenu.capacity;
        int size = CreateNewMapMenu.size;
        Map map = CreateNewMapMenu.map;
        int x=Integer.parseInt(matcher.group("x"));
        int y=Integer.parseInt(matcher.group("y"));
        if (number>capacity){
            return "The position of all governments has been set.";
        }
        if (x>size || y>size || x<1 || y<1){
            return "Error: Invalid position!";
        }
        if (map.getBlockByPosition(x,y).getGovernmentId()!=0){
            return "Error: This position has already been selected for another government!";
        }
        CreateNewMapMenu.number +=1;
        map.getBlockByPosition(x,y).setGovernmentId(number);
        map.addGovernmentBlock(map.getBlockByPosition(x,y));
        return "Government No. "+number+" position successfully set.";
    }
}
