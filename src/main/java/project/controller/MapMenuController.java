package project.controller;

import project.model.ApplicationManager;
import project.model.Block;
import project.model.Game;
import project.model.Map;
import project.model.Peoples.Militia;
import project.model.Peoples.People;
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
                res+="----------------------\n";
                res+="| Type: "+block.getType()+"\n";
                res+="| Biulding: "+block.getThisBlockStructure().name;
                res+="| Soldiers: \n";
                for(People selected:block.getPeoples()){
                    if(selected instanceof Militia){
                       res+= "| "+selected.getPeopleType().type+" "+selected.isInMove()+"\n";
                       res+= "| "+selected.getGovernment().getOwner().getNickname()+"\n";

                    }

                }
                if(block.getTree() != null)
                    res+="|   There is a Tree\n";

            }
            res+="\n";
        }


        return res;
    }
}
