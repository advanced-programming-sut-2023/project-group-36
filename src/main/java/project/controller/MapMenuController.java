package project.controller;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Game;
import project.model.Map;
import project.view.GameMenu;

import java.util.regex.Matcher;

public class MapMenuController {
    public static String showMap(Matcher matcher){
        String res="";
        int x=Integer.parseInt(matcher.group("x"))-1;
        int y=Integer.parseInt(matcher.group("y"))-1;
        if(x<0 || y<0){
            return "Invalid cordinates!";
        }
        Block block;
        Map CurrentMap=ApplicationManager.getCurrentGame().getMap();
        for(int i=Math.max(0,x-2);i<x+3;i++){
            for(int j=Math.max(0,y-2);j<y+3;j++){
                block=CurrentMap.getBlockByPosition(i,j);
                res+="---------------------";
                res+="| Type: "+block.getType();

            }
            res+="\n";
        }


        return res;
    }
}
